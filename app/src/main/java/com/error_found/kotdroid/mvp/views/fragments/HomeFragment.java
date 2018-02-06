package com.error_found.kotdroid.mvp.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.error_found.kotdroid.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user12 on 6/2/18.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.tv_name)
    TextView tvName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        String name=bundle.getString("name");
        tvName.setText("Wlcome to CityGreen " +name);
    }




}
