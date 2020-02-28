package com.example.simpleparadox.listycity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityList {
    private List<City> cities = new ArrayList<>();

    /**
     * This adds a city to the list if the city does not exist
     * @param city
     * This is a candidate city to add
     */

    public void add (City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     * @return
     * Return the sorted list
     */
    public List<City> getCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }

    /**
     * This checks if a city already exists in the list
     * @param city
     * @return
     * Return true if city exists in the list
     */
    public boolean hasCity(City city) {
        return this.cities.contains(city);
    }

    /**
     * This deletes a city from the list if the city exists
     * @param city
     * @throws Exception if city does not exist in list
     */
    public void delete(City city) throws Exception {
        if (hasCity(city)) {
            this.cities.remove(city);
        } else {
            throw new Exception("City does not exist in cityList!");
        }
    }

    /**
     * This returns the number of cities in the list
     * @return
     * Return the integer count of cities in the list
     */
    public int countCities() {
        return this.cities.size();
    }
}
