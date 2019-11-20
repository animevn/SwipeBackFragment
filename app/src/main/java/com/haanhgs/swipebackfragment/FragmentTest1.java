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

public class FragmentTest1 extends SwipeBackFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FragmentTest1", "on Create");
    }

    private void openFragmentTest(){
        if (getFragmentManager() != null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            FragmentTest2 fragment = (FragmentTest2)getFragmentManager().findFragmentByTag("test2");
            if (fragment == null){
                FragmentTest2 fragmentTest = new FragmentTest2();
                ft.add(R.id.flHome, fragmentTest, "test2");
                ft.addToBackStack("test2");
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
        View view = inflater.inflate(R.layout.frament_test1, container, false);
        TextView tvTest = view.findViewById(R.id.tvTest);
        Button bnTest = view.findViewById(R.id.bnTest);
        bnTest.setOnClickListener(new View.OnClickListener() {
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
        Log.d("FragmentTest1", "on Resume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("FragmentTest1", "on DestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("FragmentTest1", "on Destroy");
    }
}
