package com.example.simpleparadox.listycity;

public class City {
    private String city;
    private String province;

    City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    String getCityName(){
        return this.city;
    }

    String getProvinceName() {
        return this.province;
    }

    public void setCityName(String name) {
        this.city = name;
    }

    public void setProvinceName(String name){
        this.province = name;
    }

    public void setCity(String cityName, String provinceName) {
        setCityName(cityName);
        setProvinceName(provinceName);
    }
}
