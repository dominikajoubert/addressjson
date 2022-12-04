package com.investec.addressjson.rules;

import org.springframework.stereotype.Component;

import com.investec.addressjson.pojo.Address;

@Component
public class CountryRule implements AddressRule {

	@Override
	public boolean isValid(Address address) {
		return address.getCountry() != null;
	}

	@Override
	public String reasonInvalid() {
		return "No Country Details available";
	}
}
