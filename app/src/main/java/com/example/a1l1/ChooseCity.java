package com.example.a1l1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseCity extends AppCompatActivity {

    public static String key = "loc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_city);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(v -> {
            finish();
        });

        ImageView add = findViewById(R.id.addIcon);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseCity.this, AddCity.class);
            startActivity(intent);
        });

        View line1 = findViewById(R.id.line1);
        line1.setOnClickListener(v -> {
            chooseCity(1);
        });

        View line2 = findViewById(R.id.line2);
        line2.setOnClickListener(v -> {
            chooseCity(2);
        });

        if (MainActivity.degrees == "°F") {
            TextView temp1 = findViewById(R.id.t1);
            TextView temp2 = findViewById(R.id.t2);
            int t1 = Integer.parseInt(temp1.getText().toString());
            int t2 = Integer.parseInt(temp2.getText().toString());
            temp1.setText(String.valueOf(t1 * 9 / 5 + 32));
            temp2.setText(String.valueOf(t2 * 9 / 5 + 32));
        }
    }

    private void chooseCity(int i) {
        TextView city = null;
        TextView t = null;
        if (i == 1) {
            city = findViewById(R.id.city1);
            t = findViewById(R.id.t1);
        } else if (i == 2) {
            city = findViewById(R.id.city2);
            t = findViewById(R.id.t2);
        }
        Location location = new Location();
        location.city = city.getText().toString();
        location.temperature = t.getText().toString();

        Intent intent = new Intent(ChooseCity.this, MainActivity.class);
        intent.putExtra(key, location);
        setResult(RESULT_OK, intent);
        finish();
    }
}
