package com.example.bean;


public class Car {
	private int id;
	private int user_id;
	private String type;
	private String license;

	public Car() {
	}

	public Car(int user_id, String type, String license) {
		this.user_id = user_id;
		this.type = type;
		this.license = license;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

}
