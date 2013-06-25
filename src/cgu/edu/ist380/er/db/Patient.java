package cgu.edu.ist380.er.db;

import java.util.Date;

public class Patient {
	
	private int id;
	private Date dob;
	private String gender;
	private String firstName;
	private String lastName;
	private int phone;
	private String address;
	private String city;
	private int zib;
	
	public Patient() {
		super();
		this.dob = null;
		this.gender = " ";
		this.firstName = " ";
		this.lastName = " ";
		this.phone = 0000000000;
		this.address = " ";
		this.city = " ";
		this.zib = 00000;
		this.id=0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZib() {
		return zib;
	}
	public void setZib(int zib) {
		this.zib = zib;
	}

}
