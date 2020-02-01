package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> cityDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver"};
        String []provinces = {"AB", "BC"};

        cityDataList = new ArrayList<>();

        for (int i = 0; i < cities.length; i++) {
            cityDataList.add((new City(cities[i], provinces[i])));
        }

        cityAdapter = new CustomList(this, cityDataList);

        cityList.setAdapter(cityAdapter);

        final FloatingActionButton addCityButton = findViewById(R.id.add_city_button);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddCityFragment().show(getSupportFragmentManager(), "ADD_CITY");
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "EDIT_CITY");
                bundle.putInt("position", position);

                DialogFragment fragment = new AddCityFragment();
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "EDIT_CITY");
            }
        });
    }

    @Override
    public void onOkPressed(String city, String province, int position) {
        if (position < 0) {
            cityAdapter.add(new City(city, province));
            return;
        }
        cityDataList.get(position).setCity(city, province);
    }



}
