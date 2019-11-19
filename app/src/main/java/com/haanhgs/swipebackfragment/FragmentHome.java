package com.haanhgs.swipebackfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void handleTextView() {
        super.handleTextView();
        super.tvMain.setText(R.string.home);
    }

    @Override
    protected void handleButton() {
        super.handleButton();
        super.bnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragmentTest();
            }
        });
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
