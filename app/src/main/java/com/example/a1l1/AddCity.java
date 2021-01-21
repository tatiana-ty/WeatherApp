package com.example.a1l1;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddCity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(v -> {
            finish();
        });
    }
}
