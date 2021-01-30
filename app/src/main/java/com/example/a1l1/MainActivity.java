package com.example.a1l1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String temperature = "10";
    static String degrees = "°C";
    private final int chooseCityCode = 123;
    TextView temperatureView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperatureView = findViewById(R.id.currentWeather);

        ImageView backgroundImage = findViewById(R.id.background);
        int currentMonth =  Calendar.getInstance().get(Calendar.MONTH);
        if (currentMonth >= 2 && currentMonth <= 4) {
            backgroundImage.setImageResource(R.drawable.spring);
        } else if (currentMonth >= 5 && currentMonth <= 7) {
            backgroundImage.setImageResource(R.drawable.summer);
        } else if (currentMonth >= 8 && currentMonth <= 10) {
            backgroundImage.setImageResource(R.drawable.autumn);
        } else {
            backgroundImage.setImageResource(R.drawable.winter);
        }

        TextView changeMetric = findViewById(R.id.currentWeather);
        TextView changeMetric1 = findViewById(R.id.degrees);
//        changeMetric.setOnClickListener(v -> changeMetric(changeMetric));
//        changeMetric1.setOnClickListener(v -> changeMetric(changeMetric1));

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

        Request.request();
        System.out.println(Request.temperature);
        temperatureView.setText(Request.temperature);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == chooseCityCode && resultCode == RESULT_OK) {
            TextView city = findViewById(R.id.location);
            TextView weather = findViewById(R.id.currentWeather);
            assert data != null;
            Location l = (Location) data.getSerializableExtra(ChooseCity.key);
            assert l != null;
            city.setText(l.city);
            weather.setText(l.temperature);
        }
    }

//    public void changeMetric(View v)
//    {
//        TextView degrees = findViewById(R.id.degrees);
//        int t = Integer.parseInt(temperatureView.getText().toString());
//
//        if (degrees.getText().equals("°C")) {
//            degrees.setText("°F");
//            String newT = String.valueOf(t * 9/5 + 32);
//            temperatureView.setText(newT);
//            MainActivity.degrees = "°F";
//            this.temperature = newT;
//        }
//        else {
//            degrees.setText("°C");
//            String newT = String.valueOf((t - 32) * 5/9);
//            temperatureView.setText(newT);
//            MainActivity.degrees = "°C";
//            this.temperature = newT;
//        }
//    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putString("Temperature", temperature);
        saveInstanceState.putString("Degrees", degrees);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        temperature = saveInstanceState.getString("Temperature");
        degrees = saveInstanceState.getString("Degrees");
        TextView d = findViewById(R.id.degrees);
        d.setText(degrees);
        temperatureView.setText(temperature);
    }

}