package com.investec.addressjson.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investec.addressjson.pojo.Address;
import com.investec.addressjson.util.AddressUtil;

@Component
public class AddressLineRule implements AddressRule {

	@Autowired
	AddressUtil addressUtil;

	@Override
	public boolean isValid(Address address) {
		if (address.getAddressLineDetail() == null) {
			return false;
		} else {
			if (!addressUtil.isAddressDataValueValid(address.getAddressLineDetail().getLine1())
					&& !addressUtil.isAddressDataValueValid(address.getAddressLineDetail().getLine2())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String reasonInvalid() {
		return "No Address Line Details available";
	}

}
