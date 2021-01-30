package com.example.a1l1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCity extends AppCompatActivity {

    public static String key = "city";
    static ArrayList<String> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (cities == null) {
            cities = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cities)));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(v -> finish());

        Button newYork = findViewById(R.id.newYork);
        newYork.setOnClickListener(v -> addCity(newYork.getText().toString()));

        Button paris = findViewById(R.id.paris);
        paris.setOnClickListener(v -> addCity(paris.getText().toString()));

        Button london = findViewById(R.id.london);
        london.setOnClickListener(v -> addCity(london.getText().toString()));

        Button tokyo = findViewById(R.id.tokyo);
        tokyo.setOnClickListener(v -> addCity(tokyo.getText().toString()));

        Button rome = findViewById(R.id.rome);
        rome.setOnClickListener(v -> addCity(rome.getText().toString()));

        Button dubai = findViewById(R.id.dubai);
        dubai.setOnClickListener(v -> addCity(dubai.getText().toString()));

        Button moscow = findViewById(R.id.moscow);
        moscow.setOnClickListener(v -> addCity(moscow.getText().toString()));

        Button hongKong = findViewById(R.id.hongKong);
        hongKong.setOnClickListener(v -> addCity(hongKong.getText().toString()));

        Button sydney = findViewById(R.id.sydney);
        sydney.setOnClickListener(v -> addCity(sydney.getText().toString()));

        Button buttonOk = findViewById(R.id.ok);
        buttonOk.setOnClickListener(v -> searchCity());
    }

    private void searchCity() {
        TextInputEditText search = findViewById(R.id.search_city);
        addCity(search.getText().toString());
    }

    private void addCity (String city) {
        //String city = button.getText().toString();
        if (cities.contains(city)) {
            finish();
        } else {
            cities.add(city);
            Intent intent = new Intent(AddCity.this, ChooseCity.class);
            Location location = new Location();
            location.city = city;
            location.temperature = String.valueOf((int) (Math.random() * 20));
            intent.putExtra(key, location);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
