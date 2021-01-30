package com.example.a1l1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseCity extends AppCompatActivity {

    public static String key = "loc";
    public static int ADD_CITY_CODE = 1234;
    String[] citiesArray;
    String[] temperaturesArray;
    ArrayList<String> cities;
    ArrayList <String> temperatures;
    static SocnetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        citiesArray = getResources().getStringArray(R.array.cities);
        temperaturesArray = getResources().getStringArray(R.array.temperatures);
        cities = new ArrayList<>(Arrays.asList(citiesArray));
        temperatures = new ArrayList<>(Arrays.asList(temperaturesArray));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_city);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(v -> finish());

        ImageView add = findViewById(R.id.addIcon);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseCity.this, AddCity.class);
            startActivityForResult(intent, ADD_CITY_CODE);
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridManager = new GridLayoutManager(getBaseContext(), 5);
        gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position % 2) {
                    case 0:
                        return 4;
                    default:
                        return 1 ;
                }
            }
        });
        recyclerView.setLayoutManager(gridManager);

        adapter = new SocnetAdapter(cities, temperatures);
        recyclerView.setAdapter(adapter);
    }

    public void chooseCity(View view) {
        TextView tv = (TextView) view;
        Intent intent = new Intent(ChooseCity.this, MainActivity.class);
        Location location = new Location();
        for (int i = 0; i < cities.size(); i++) {
            if (tv.getText().toString().equals(cities.get(i)) || tv.getText().toString().equals(temperatures.get(i))) {
                location.city = cities.get(i);
                location.temperature = temperatures.get(i);
            }
        }
        intent.putExtra(key, location);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CITY_CODE && resultCode == RESULT_OK && data != null) {
            Location l = (Location) data.getSerializableExtra(AddCity.key);
            adapter.add(l.city, l.temperature);
        }
    }
}
