package com.investec.addressjson.util;

import org.springframework.stereotype.Component;

@Component
public class AddressUtil {

	public boolean isAddressDataValueValid(String val) {
		return (val != null && !val.isBlank());
	}

	public String returnEmptyString(String val) {
		return (isAddressDataValueValid(val) ? val : "");
	}
	public boolean isValidAddressType(String addressType) {
		switch (addressType.toUpperCase()) {
		case AddressConstants.POSTAL:
		case AddressConstants.BUSINESS:
		case AddressConstants.PHYSICAL:
			return true;
		default:
			return false;
		}
	}
}