package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // variables for list view implementation
    private ListView cityList;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;

    // variables for addition and deletion of items in list view
    private Button addCity, deleteCity, addCityConfirm;
    private EditText addCityText;
    private TableRow addCityTableRow;

    // city selected by user
    private int selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city_button);
        deleteCity = findViewById(R.id.del_city_button);
        addCityTableRow = findViewById(R.id.tableRow);
        addCityText = findViewById(R.id.add_city_text);
        addCityConfirm = findViewById(R.id.add_city_confirm);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        cityList.setAdapter(cityAdapter);

        // determine selected city from list view
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = position;
            }
        });


        // when add city button is pressed
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCityTableRow.setVisibility(View.VISIBLE);
                // wait for "Confirm" button to be pressed
                addCityConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String city = addCityText.getText().toString();
                        dataList.add(city);
                        cityAdapter.notifyDataSetChanged();
                        addCityTableRow.setVisibility(View.GONE);
                    }
                });
            }
        });

        // when delete city button is pressed
        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
            }
        });
    }
}
