package com.haanhgs.swipebackfragment.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.haanhgs.swipebackfragment.R;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import androidx.fragment.app.Fragment;

public class SwipeBackLayout extends FrameLayout {

    public static final int EDGE = ViewDragHelper.EDGE_LEFT;
    public static final int STATE_IDLE = ViewDragHelper.STATE_IDLE;
    public static final int STATE_DRAGGING = ViewDragHelper.STATE_DRAGGING;
    public static final int STATE_SETTLING = ViewDragHelper.STATE_SETTLING;

    private static final int BACKGROUND = 0x52000000;
    private static final int ALPHA = 180;
    private static final float THRESHOLD = 0.5f;
    private static final int OVERSCROLL = 10;

    private ViewDragHelper helper;
    private float scrollPercent;

    private View contentView;
    private SwipeBackFragment fragment;
    private Fragment preFragment;
    private Drawable shadow;
    private final Rect tempRect = new Rect();
    private boolean enable = true;

    private void initViewDragHelper() {
        helper = ViewDragHelper.create(this, new ViewDragCallback());
        helper.setEdgeTrackingEnabled(EDGE);
        shadow = getResources().getDrawable(R.drawable.shadow);
        invalidate();
    }

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewDragHelper();
    }

    public void setEnableGesture(boolean enable) {
        this.enable = enable;
    }

    private void drawShadow(Canvas canvas, View child) {
        final Rect childRect = tempRect;
        child.getHitRect(childRect);
        shadow.setBounds(childRect.left - shadow.getIntrinsicWidth(),
                childRect.top, childRect.left, childRect.bottom);
        shadow.setAlpha(180);
        shadow.draw(canvas);
    }

    private void drawBackground(Canvas canvas, View child) {
        canvas.clipRect(0, 0, child.getLeft(), getHeight());
        canvas.drawColor(BACKGROUND);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean isDrawView = child == contentView;
        boolean drawChild = super.drawChild(canvas, child, drawingTime);
        if (isDrawView &&  helper.getViewDragState() != ViewDragHelper.STATE_IDLE) {
            drawShadow(canvas, child);
            drawBackground(canvas, child);
        }
        return drawChild;
    }

    @Override
    public void computeScroll() {
        if (helper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setFragment(SwipeBackFragment fragment, View view) {
        this.fragment = fragment;
        contentView = view;
    }

    public void hideFragment() {
        if (preFragment != null && preFragment.getView() != null) {
            preFragment.getView().setVisibility(GONE);
        }
    }

    public void attachToFragment(SwipeBackFragment swipeBackFragment, View view) {
        addView(view);
        setFragment(swipeBackFragment, view);
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            boolean dragEnable = helper.isEdgeTouched(EDGE, pointerId);
            if (dragEnable) {
                if (preFragment == null) {
                    if (fragment.getFragmentManager() != null) {
                        List<Fragment> fragmentList = fragment.getFragmentManager().getFragments();
                        if (fragmentList.size() > 1) {
                            int index = fragmentList.indexOf(fragment);
                            for (int i = index - 1; i >= 0; i--) {
                                Fragment fragment = fragmentList.get(i);
                                if (fragment != null && fragment.getView() != null) {
                                    fragment.getView().setVisibility(VISIBLE);
                                    preFragment = fragment;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    View preView = preFragment.getView();
                    if (preView != null && preView.getVisibility() != VISIBLE) {
                        preView.setVisibility(VISIBLE);
                    }
                }
            }
            return dragEnable;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return Math.min(child.getWidth(), Math.max(left, 0));
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView,
                                          int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            scrollPercent = Math
                    .abs((float) left/(getWidth() + shadow.getIntrinsicWidth()));
            invalidate();

            if (scrollPercent > 1) {
                if (fragment.getFragmentManager() != null) {
                    if (!fragment.isDetached()) {
                        fragment.getFragmentManager().popBackStackImmediate();
                    }
                }
            }
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            if (fragment != null) {
                return 1;
            }
            return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            final int childWidth = releasedChild.getWidth();
            int left, top = 0;
            left = xvel > 0 || xvel == 0 && scrollPercent > THRESHOLD ? (childWidth
                    + shadow.getIntrinsicWidth() + OVERSCROLL) : 0;
            helper.settleCapturedViewAt(left, top);
            invalidate();
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!enable) return super.onInterceptTouchEvent(ev);
        return helper.shouldInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!enable) return super.onTouchEvent(event);
        helper.processTouchEvent(event);
        return true;
    }
}
