package com.haanhgs.swipebackfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.haanhgs.swipebackfragment.lib.SwipeBackFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentTest2 extends SwipeBackFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FragmentTest2", "on Create");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_test2, container, false);
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
