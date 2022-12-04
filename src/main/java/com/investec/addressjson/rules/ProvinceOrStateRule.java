package com.investec.addressjson.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investec.addressjson.pojo.Address;
import com.investec.addressjson.util.AddressUtil;

@Component
public class ProvinceOrStateRule implements AddressRule {

	@Autowired
	AddressUtil addressUtil;

	@Override
	public boolean isValid(Address address) {
		if (address.getCountry() != null) {
			if (addressUtil.isAddressDataValueValid(address.getCountry().getName())
					&& address.getCountry().getCode().toUpperCase().equals("ZA")) {
				if (address.getProvinceOrState() == null) {
					return false;
				} 
			}
		}
		return true;
	}

	@Override
	public String reasonInvalid() {
		return "No Province/State Details available when country is ZA";
	}
}
