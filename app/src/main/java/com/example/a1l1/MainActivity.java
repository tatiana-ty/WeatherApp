package com.example.a1l1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String temperature = "10";
    static String degrees = "°C";
    private final int chooseCityCode = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView changeMetric = findViewById(R.id.currentWeather);
        TextView changeMetric1 = findViewById(R.id.degrees);
        changeMetric.setOnClickListener(v -> changeMetric(changeMetric));
        changeMetric1.setOnClickListener(v -> changeMetric(changeMetric1));

        ImageView chooseCity = findViewById(R.id.cityIcon);
        chooseCity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChooseCity.class);
            startActivityForResult(intent, chooseCityCode);
        });

        ImageView settings = findViewById(R.id.settingsIcon);
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        });

        ImageView info = findViewById(R.id.infoIcon);
        TextView city = findViewById(R.id.location);
        info.setOnClickListener(v -> {
            String baseUrl = "https://wikipedia.org/wiki/";
            String url = baseUrl + city.getText().toString();
            Uri uri = Uri.parse(url);
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });

//        Location location = (Location) getIntent().getExtras().getSerializable("1234");
//        TextView uLocation = findViewById(R.id.location);
//        TextView temperature = findViewById(R.id.degrees);
//        uLocation.setText(location.city);
//        temperature.setText(location.temperature);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == chooseCityCode && resultCode == RESULT_OK) {
            TextView city = findViewById(R.id.location);
            TextView weather = findViewById(R.id.currentWeather);
            Location l = (Location) data.getSerializableExtra(ChooseCity.key);
            city.setText(l.city);
            weather.setText(l.temperature);
        }
    }

    public void changeMetric(View v)
    {
        TextView degrees = findViewById(R.id.degrees);
        TextView temperature = findViewById(R.id.currentWeather);
        int t = Integer.parseInt(temperature.getText().toString());

        if (degrees.getText().equals("°C")) {
            degrees.setText("°F");
            String newT = String.valueOf(t * 9/5 + 32);
            temperature.setText(newT);
            this.degrees = "°F";
            this.temperature = newT;
        }
        else {
            degrees.setText("°C");
            String newT = String.valueOf((t - 32) * 5/9);
            temperature.setText(newT);
            this.degrees = "°C";
            this.temperature = newT;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putString("Temperature", temperature);
        saveInstanceState.putString("Degrees", degrees);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        temperature = saveInstanceState.getString("Temperature");
        degrees = saveInstanceState.getString("Degrees");
        TextView d = findViewById(R.id.degrees);
        TextView t = findViewById(R.id.currentWeather);
        d.setText(degrees);
        t.setText(temperature);
    }

}