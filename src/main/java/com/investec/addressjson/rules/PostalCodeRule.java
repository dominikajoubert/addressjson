package com.investec.addressjson.rules;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import com.investec.addressjson.pojo.Address;

@Component
public class PostalCodeRule implements AddressRule {

	@Override
	public boolean isValid(Address address) {
		return NumberUtils.isParsable(address.getPostalCode());
	}

	@Override
	public String reasonInvalid() {
		return "No Postal Code available";
	}
}
