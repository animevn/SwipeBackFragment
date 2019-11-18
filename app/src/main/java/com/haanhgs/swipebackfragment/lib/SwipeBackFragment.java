package com.haanhgs.swipebackfragment.lib;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SwipeBackFragment extends Fragment {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    private SwipeBackLayout swipeBackLayout;
    private Activity activity;

    public void setSwipeBackEnable(boolean enable) {
        swipeBackLayout.setEnableGesture(enable);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    private void loadSavedFragment(Bundle bundle){
        if (bundle != null && getFragmentManager() != null) {
            boolean isFragmentHidden = bundle.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isFragmentHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    private void initSwipeBackLayout() {
        swipeBackLayout = new SwipeBackLayout(activity);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        swipeBackLayout.setLayoutParams(params);
        swipeBackLayout.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipeBackLayout();
        loadSavedFragment(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    protected View attachToSwipeBack(View view) {
        swipeBackLayout.attachToFragment(this, view);
        return swipeBackLayout;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden && swipeBackLayout != null) {
            swipeBackLayout.hideFragment();
        }
    }

    private int getWindowBackground() {
        TypedArray a = activity.getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.windowBackground});
        int background = a.getResourceId(0, 0);
        a.recycle();
        return background;
    }

    private void setBackground(View view) {
        if (view != null && view.getBackground() == null) {
            int background = getWindowBackground();
            view.setBackgroundResource(background);
        }
    }

    private void initFragmentBackground(View view) {
        if (view instanceof SwipeBackLayout) {
            View childView = ((SwipeBackLayout) view).getChildAt(0);
            setBackground(childView);
        } else {
            setBackground(view);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        initFragmentBackground(view);
        if (view != null) {
            view.setClickable(true);
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackLayout;
    }
}
