package com.haanhgs.swipebackfragment;

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
import androidx.fragment.app.FragmentTransaction;

public class FragmentTest2 extends SwipeBackFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FragmentTest2", "on Create");
    }

    private void openFragmentTest(){
        if (getFragmentManager() != null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            FragmentTest3 fragment = (FragmentTest3)getFragmentManager().findFragmentByTag("test3");
            if (fragment == null){
                FragmentTest3 fragmentTest = new FragmentTest3();
                ft.add(R.id.flHome, fragmentTest, "test3");
                ft.addToBackStack("test3");
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
        View view = inflater.inflate(R.layout.frament_test2, container, false);
        Button bnTest2 = view.findViewById(R.id.bnTest2);
        bnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragmentTest();
            }
        });
        return attachToSwipeBack(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentTest2", "on Resume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FragmentTest2", "on DestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("FragmentTest2", "on Destroy");
    }
}
