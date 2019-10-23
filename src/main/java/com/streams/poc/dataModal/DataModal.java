package com.streams.poc.dataModal;

public class DataModal {

	String firstName;
	
	String lastName;
	
	String companyName;
	
	String address;
	
	String city;
	
	String state;
	
	String post;
	
	String phone1;
	
	String phone2;
	
	String email;
	
	String web;

	public DataModal(String line) {
		String[] attributes = line.substring(1, line.length() - 1).split("(\",)|(,\")");
		this.firstName = attributes[0].replace("\"", "");
		this.lastName = attributes[1].replace("\"", "");
		this.companyName = attributes[2].replace("\"", "");
		this.address = attributes[3].replace("\"", "");
		this.city = attributes[4].replace("\"", "");
		this.state = attributes[5].replace("\"", "");
		this.post = attributes[6].replace("\"", "");
		this.phone1 = attributes[7].replace("\"", "");
		this.phone2 = attributes[8].replace("\"", "");
		this.email = attributes[9].replace("\"", "");
		this.web = attributes[10].replace("\"", "");
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}
	
	@Override
	public String toString() {
		return "Object create of Name : " + this.firstName + " " + this.lastName;
	}

}
