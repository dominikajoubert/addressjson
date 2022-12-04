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
	
	public String toPrettyPrintString() {
		String addressLines = (addressLineDetail!=null? (addressLineDetail.getLine1() + " " +addressLineDetail.getLine2()):" n/a");
		String provinceOrStateStr = (provinceOrState!=null? provinceOrState.getName():"n/a");
		String countryStr = (country!=null? country.getName():"n/a");		
		return String.format("%s: %s - %s - %s - %s - %s", type.getName(), addressLines , cityOrTown, provinceOrStateStr, postalCode, countryStr );		
	}
	
	public String toValidatedString() {
		String addressLines = (addressLineDetail!=null? (addressLineDetail.getLine1() + " " +addressLineDetail.getLine2()):"n/a");
		String provinceOrStateStr = (provinceOrState!=null? provinceOrState.getName():"n/a");
		String countryStr = (country!=null? country.getName():"n/a");
		
		String invalidMessage = "";
		for (String invalidString : invalidAddressList) {
			invalidMessage += String.format("%s \n",invalidString);
		}
	
		String validationStr = (isAddressValid ? "Address is Valid" : "Address is invalid because: \n"+invalidMessage);		
		String format = String.format("\n%s: \n %s \n %s \n %s \n %s \n %s \n%s", type.getName(), addressLines , cityOrTown, provinceOrStateStr, postalCode, countryStr,validationStr );		
		return format;
	}
	
	

}