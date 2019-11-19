package com.haanhgs.swipebackfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.haanhgs.swipebackfragment.lib.SwipeBackFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public class BaseFragment extends SwipeBackFragment {

    private static final String IS_HIDDEN = "IS_HIDDEN";
    protected TextView tvMain;
    protected Button bnMain;

    private void initViews(View view){
        tvMain = view.findViewById(R.id.tvMain);
        bnMain = view.findViewById(R.id.bnMain);
    }

    private void loadFragmentState(Bundle bundle){
        if (bundle != null && getFragmentManager() != null) {
            boolean isFragmentHidden = bundle.getBoolean(IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isFragmentHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragmentState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_HIDDEN, isHidden());
    }

    protected void handleTextView(){
    }

    protected void handleButton(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        handleTextView();
        handleButton();
        return view;
    }
}
