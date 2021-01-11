package com.example.a1l1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String temperature = "10";
    private String degrees = "°C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void change_metric(View v)
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

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d("Log", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d("Log", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d("Log", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d("Log", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d("Log", "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d("Log", "onDestroy()");
    }

}