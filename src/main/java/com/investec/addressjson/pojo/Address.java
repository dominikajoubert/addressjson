package com.investec.addressjson.pojo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Component
public class Address {

	private String id;
	private AddressType type;
	private AddressLineDetails addressLineDetail;
	private ProvinceOrStateDetails provinceOrState;
	private String cityOrTown;
	private CountryDetails country;
	private String postalCode;
	private String suburbOrDistrict;
	private String lastUpdated;
	@JsonIgnore
	private Boolean isAddressValid = true;
	@JsonIgnore
	private List<String> invalidAddressList;

	@Data
	public class AddressType {
		public String code;
		public String name;
	}

	@Data
	public class CountryDetails {
		public String code;
		public String name;
	}

	@Data
	public class AddressLineDetails {
		public String line1;
		public String line2;
	}

	@Data
	public class ProvinceOrStateDetails {
		public String code;
		public String name;
	}

}