package com.haanhgs.swipebackfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHome extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FragmentHome", "on Create");
    }

    private void openFragmentTest(){
        if (getFragmentManager() != null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            FragmentTest fragment = (FragmentTest)getFragmentManager().findFragmentByTag("test");
            if (fragment == null){
                FragmentTest fragmentTest = new FragmentTest();
                ft.add(R.id.flHome, fragmentTest, "test");
                ft.hide(FragmentHome.this);
                ft.addToBackStack("test");
                ft.commit();
            }else {
                ft.attach(fragment);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button bnMain = view.findViewById(R.id.bnMain);
        bnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragmentTest();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentHome", "on Resume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FragmentHome", "on DestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("FragmentHome", "on Destroy");
    }
}
