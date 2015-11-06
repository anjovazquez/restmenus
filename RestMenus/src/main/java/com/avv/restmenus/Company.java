package com.avv.restmenus;

public class Company {

	private Number idCompany;
	private String companyName;
	private String companyLogoUrl;
	public Number getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(Number idCompany) {
		this.idCompany = idCompany;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLogoUrl() {
		return companyLogoUrl;
	}
	public void setCompanyLogoUrl(String companyLogoUrl) {
		this.companyLogoUrl = companyLogoUrl;
	}
	@Override
	public String toString() {
		return "Company [idCompany=" + idCompany + ", companyName="
				+ companyName + ", companyLogoUrl=" + companyLogoUrl + "]";
	}
	
	
}
