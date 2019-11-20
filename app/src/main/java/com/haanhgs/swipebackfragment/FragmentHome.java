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
            FragmentTest1 fragment = (FragmentTest1)getFragmentManager().findFragmentByTag("test1");
            if (fragment == null){
                FragmentTest1 fragmentTest = new FragmentTest1();
                ft.add(R.id.flHome, fragmentTest, "test1");
                ft.hide(FragmentHome.this);
                ft.addToBackStack("test1");
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
