package com.investec.addressjson.rules;

import com.investec.addressjson.pojo.Address;

public interface AddressRule {

	boolean isValid(Address address);

	String reasonInvalid();

}
