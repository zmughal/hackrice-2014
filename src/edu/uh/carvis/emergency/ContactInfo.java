package edu.uh.carvis.emergency;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.Field;

public class ContactInfo implements Serializable {

	public String fullName;
	public String address1;
	public String address2;
	public String city;
	public String state;
	public String zip;
	public String phone;
	public String email;
	public String vehicleMake;
	public String vehicleModel;
	public String vehicleYear;
	public String licensePlate;
	public String insuranceCarrier;
	public String policyNumber;
	public String insurancePhoneNumber;

	public ContactInfo() {

	}

	public ContactInfo(byte[] bytes) {
		this(new Gson().fromJson(getString(bytes), ContactInfo.class));
	}

	public static String getString(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	public ContactInfo(ContactInfo other) {
		for (Field f : ContactInfo.class.getFields()) {
			try {
				f.set(this, f.get(other));
			} catch (Exception e) {
			}
		}
	}

	public byte[] serialize() {
		try {
			return new Gson().toJson(this).getBytes("UTF-8");
		} catch (Exception e) {
			return new byte[0];
		}
	}
}
