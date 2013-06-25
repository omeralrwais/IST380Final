package cgu.edu.ist380.er.db;

public class Hospital {

	private int id;
	private String name;
	private int waitTime;
	private int drivingTime;
	private double lat;
	private double lng;

	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String phoneNumber;
	private String distance;
	
	
	 
	public Hospital(int id, String name, int waitTime, int drivingTime) {
		super();
		this.id = id;
		this.name = name;
		this.waitTime = waitTime;
		this.drivingTime = drivingTime;
	}
	public Hospital() {
 	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public int getDrivingTime() {
		return drivingTime;
	}
	public void setDrivingTime(int drivingTime) {
		this.drivingTime = drivingTime;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double d) {
		this.lat = d;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	@Override
	public boolean equals(Object o) {
		 
		return this.name.equals(((Hospital) o).getName());
	}
}
