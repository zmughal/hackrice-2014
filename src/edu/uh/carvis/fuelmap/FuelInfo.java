package edu.uh.carvis.fuelmap;

/**
 * Created by zaki on 1/26/14.
 */
public class FuelInfo {
    private String name;
    private double lat;
    private double lon;

    FuelInfo(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String toString() {
        String s = "[ ";
        s += "Name: " + this.name + ", ";
        s += "Lat: " + this.lat + ", ";
        s += "Lon: " + this.lon;
        s += " ]";
        return s;
    }
}
