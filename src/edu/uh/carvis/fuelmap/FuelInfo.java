package edu.uh.carvis.fuelmap;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by zaki on 1/26/14.
 */
public class FuelInfo {
	private String name;
	private double lat;
	private double lon;
	private String price; /* TODO currency */

	String getName() {
		return name;
	}

	String getPrice() {
		return price;
	}

	FuelInfo(String name, double lat, double lon, String price) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.price = price;
	}

	public String toString() {
		String s = "[ ";
		s += "Name: " + this.name + ", ";
		s += "Lat: " + this.lat + ", ";
		s += "Lon: " + this.lon + ", ";
		s += "Price: " + this.price;
		s += " ]";
		return s;
	}

	LatLng getLatLng() {
		return new LatLng(this.lat, this.lon);
	}
}
