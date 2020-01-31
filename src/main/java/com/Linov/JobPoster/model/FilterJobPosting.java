package com.Linov.JobPoster.model;

public class FilterJobPosting {

	public FilterJobPosting(String title, String provinsi, Double minSalary, Double maxSalary, String kota) {
		super();
		this.title = title;
		this.provinsi = provinsi;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.kota = kota;
	}
	
	private String title;
	private String provinsi;
	private Double minSalary;
	private  Double maxSalary;
	private String kota;
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
	public String getProvinsi() {
		return provinsi;
	}
	public void setProvinsi(String provinsi) {
		this.provinsi = provinsi;
	}
	public String getKota() {
		return kota;
	}
	public void setKota(String kota) {
		this.kota = kota;
	}
	
	
	
}
