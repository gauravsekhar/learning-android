package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class showActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView textView = findViewById(R.id.city_name);
        Button button = findViewById(R.id.go_back);

        // get selected city name
        Intent intent = getIntent();
        String city = intent.getStringExtra("selectedCity");

        // set text in activity to city name obtained from MainActivity
        textView.setText(city);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
