package com.haanhgs.swipebackfragment;

import android.os.Bundle;
import com.haanhgs.swipebackfragment.lib.SwipeBackFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public class BaseFragment extends SwipeBackFragment {

    private static final String IS_HIDDEN = "IS_HIDDEN";

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

}
