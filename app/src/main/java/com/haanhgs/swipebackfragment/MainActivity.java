package com.haanhgs.swipebackfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String IS_HIDDEN = "IS_HIDDEN";

    private void openFragmentHome(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentHome fragment = (FragmentHome)getSupportFragmentManager().findFragmentByTag("main");
        if (fragment == null){
            FragmentHome fragmentHome = new FragmentHome();
            ft.replace(R.id.flHome, fragmentHome, "main");
            ft.commit();
        }else {
            ft.attach(fragment);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragmentHome();
    }
}
