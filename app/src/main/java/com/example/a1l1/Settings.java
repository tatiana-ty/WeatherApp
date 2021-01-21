package com.example.a1l1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Locale;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(v -> {
            finish();
        });

        Switch theme = findViewById(R.id.darkTheme);
        theme.setOnClickListener(v -> {
            if (theme.isChecked()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

//        Spinner language = findViewById(R.id.language);
//
//
//        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String lang = parent.getItemAtPosition(position).toString();
//                String languageToLoad = null;
//
//                if (lang.equals("English") || lang.equals("Английский")) {
//                    languageToLoad = "en";
//                } else if (lang.equals("Russian") || lang.equals("Русский")) {
//                    languageToLoad = "ru";
//                }
//
//                if (languageToLoad != null) {
//                    Locale locale = new Locale(languageToLoad);
//                    Locale.setDefault(locale);
//                    Configuration config = getBaseContext().getResources().getConfiguration();
//                    config.locale = locale;
//                    getBaseContext().getResources().updateConfiguration(config,
//                            getBaseContext().getResources().getDisplayMetrics());
//
//                    SharedPreferences languagepref = getSharedPreferences("language",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = languagepref.edit();
//                    editor.putString("languageToLoad",languageToLoad );
//                    editor.commit();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }
}
