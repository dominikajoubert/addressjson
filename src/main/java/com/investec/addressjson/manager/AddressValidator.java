package com.investec.addressjson.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investec.addressjson.pojo.Address;
import com.investec.addressjson.rules.AddressLineRule;
import com.investec.addressjson.rules.AddressRule;
import com.investec.addressjson.rules.CountryRule;
import com.investec.addressjson.rules.PostalCodeRule;
import com.investec.addressjson.rules.ProvinceOrStateRule;

@Component
public class AddressValidator {

	@Autowired
	private AddressLineRule addressLineRule;
	@Autowired
	private CountryRule countryRule;
	@Autowired
	private PostalCodeRule postalCodeRule;
	@Autowired
	private ProvinceOrStateRule provinceOrStateRule;

	protected void validateAddressList(List<Address> addressList) {

		List<AddressRule> addressRules = new ArrayList<>();
		addressRules.add(addressLineRule);
		addressRules.add(countryRule);
		addressRules.add(postalCodeRule);
		addressRules.add(provinceOrStateRule);

		for (Address address : addressList) {
			validateAndSetMessageOnAddress(address, addressRules);
		}
	}

	/**
	 * Method that validates that an Address is a valid address based on specific
	 * criteria. The method sets the indicator to true/false based on if the address
	 * is valid, and updates the inValidMessage list with details why an address is
	 * invalid
	 * 
	 * @param address Address POJO
	 * @return Boolean indicating if an address details are invalid
	 */

	private void validateAndSetMessageOnAddress(Address address, List<AddressRule> addressRules) {

		List<String> invalidMessageList = new ArrayList<String>();

		for (AddressRule addressRule : addressRules) {
			boolean valid = addressRule.isValid(address);
			if (!valid) {
				address.setIsAddressValid(false);
				invalidMessageList.add(addressRule.reasonInvalid());
			}
		}
		address.setInvalidAddressList(invalidMessageList);
	}

}
