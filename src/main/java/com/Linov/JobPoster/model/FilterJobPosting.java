package com.Linov.JobPoster.model;

public class FilterJobPosting {

	public FilterJobPosting(String title, String province, Double minSalary, Double maxSalary, String city) {
		super();
		this.title = title;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}
	private String title;
	private ProvinceModel province;
	private Double minSalary;
	private  Double maxSalary;
	private CityModel city;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}
	public Double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public ProvinceModel getProvince() {
		return province;
	}
	public void setProvince(ProvinceModel province) {
		this.province = province;
	}
	public CityModel getCity() {
		return city;
	}
	public void setCity(CityModel city) {
		this.city = city;
	}
	
	
	
}
