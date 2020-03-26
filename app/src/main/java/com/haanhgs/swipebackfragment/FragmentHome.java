package com.haanhgs.swipebackfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.haanhgs.swipebackfragment.lib.SwipeBackFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentHome extends SwipeBackFragment {

    @BindView(R.id.tvMain)
    TextView tvMain;
    @BindView(R.id.bnMain)
    Button bnMain;

    private static final String COUNT = "count";
    private static final String TAG = "D.FragmentHome";
    private int count = 0;
    private FragmentManager manager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        manager = getFragmentManager();
    }

    public static FragmentHome instance(int count){
        FragmentHome fragmentHome = new FragmentHome();
        Bundle bundle = new Bundle();
        bundle.putInt(COUNT, count);
        fragmentHome.setArguments(bundle);
        return fragmentHome;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null){
            count = getArguments().getInt(COUNT);
            String stringText = "This is test " + count;
            tvMain.setText(stringText);
            String stringButton = "Open test " + String.valueOf(count + 1);
            bnMain.setText(stringButton);
            Log.d(TAG, "" + count);
            return attachToSwipeBack(view);
        }else {
            tvMain.setText(R.string.home);
            bnMain.setText(String.format("%s", "Open test 1"));
            return view;
        }
    }

    private void handleButton(){
        Fragment fragment = instance(count + 1);
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.flHome, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }


    @OnClick(R.id.bnMain)
    public void onViewClicked() {
        handleButton();
    }
}
