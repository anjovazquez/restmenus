package com.avv.restmenus.mapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.avv.restmenus.Company;
import com.ontimize.db.EntityResult;

public class CompanyMapper {
	
	public List<Company> transform(EntityResult result) {
		ArrayList<Company> companies = new ArrayList<Company>();

		for (int i = 0; i < result.calculateRecordNumber(); i++) {
			Hashtable values = result.getRecordValues(i);
			companies.add(transform(values));
		}

		return companies;
	}

	public Company transform(Hashtable values) {
		Company company = new Company();
		company.setIdCompany((Number) values.get("idCompany"));
		company.setCompanyName((String) values.get("companyName"));
		company.setCompanyLogoUrl((String) values.get("companyLogoUrl"));
		return company;
	}
}
