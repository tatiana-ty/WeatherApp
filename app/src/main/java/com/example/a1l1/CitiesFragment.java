package com.example.a1l1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CitiesFragment extends Fragment {

    int currentPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", currentPosition);
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("ResourceType")
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout)view;
        String[] cities = getResources().getStringArray(R.array.cities);
        String[] temperatures = getResources().getStringArray(R.array.temperatures);

        for(int i = 0; i < cities.length; i++){
            String city = cities[i];
            TextView cityView = new TextView(getContext());
            cityView.setText(city);
            cityView.setTextSize(30);
            cityView.setTextColor(getResources().getColor(R.color.text));
            layoutView.addView(cityView);
            final int fi = i;
            cityView.setOnClickListener(v -> ((ChooseCity) getActivity()).chooseCity(cityView.getText().toString(), temperatures[fi]));
        }
    }

}

