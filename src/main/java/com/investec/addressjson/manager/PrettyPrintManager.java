package com.investec.addressjson.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.investec.addressjson.pojo.Address;
import com.investec.addressjson.pojo.Address.AddressLineDetails;
import com.investec.addressjson.pojo.Address.CountryDetails;
import com.investec.addressjson.pojo.Address.ProvinceOrStateDetails;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PrettyPrintManager {

	/**
	 * Method that runs through all Addresses and prints out the address
	 * 
	 * @param addressList list of all the Address POJOs
	 */

	protected void prettyPrintAllAddress(List<Address> addressList) {
		log.info(" -----------------Pretty Print All ADDRESS DETAILS BELOW----------------------------");

		for (Address address : addressList) {
			log.info(getPrettyPrintAddressString(address));
		}
		log.info("");
	}

	/**
	 * Method that runs through all Addresses and prints out the address for a
	 * specific address type
	 * 
	 * @param addressType addresstype specified by user
	 * @param addressList list of all the Address POJOs
	 */
	protected void prettyPrintAddressOfType(String addressType, List<Address> addressList) {

		log.info(" -----------------" + addressType.toUpperCase()
				+ " ADDRESS DETAILS BELOW -------------------------------------");

		for (Address address : addressList) {
			if (address.getType().getName().toUpperCase().contains(addressType.toUpperCase())) {
				log.info(getPrettyPrintAddressString(address));
			}
		}
		log.info("");
	}

	/**
	 * Method that builds up string of Address in Pretty print
	 * 
	 * @param address Address POJO
	 * @return String string used to display the address details in a pretty format
	 */

	private String getPrettyPrintAddressString(Address address) {
		AddressLineDetails addressLineDetail = address.getAddressLineDetail();
		ProvinceOrStateDetails provinceOrState = address.getProvinceOrState();
		CountryDetails country = address.getCountry();
		String addressLines = (addressLineDetail != null
				? (addressLineDetail.getLine1() + " " + addressLineDetail.getLine2())
				: " n/a");
		String provinceOrStateStr = (provinceOrState != null ? provinceOrState.getName() : "n/a");
		String countryStr = (country != null ? country.getName() : "n/a");
		return String.format("%s: %s - %s - %s - %s - %s", address.getType().getName(), addressLines,
				address.getCityOrTown(), provinceOrStateStr, address.getPostalCode(), countryStr);
	}

	/**
	 * Method that runs through all Addresses and prints out the address include
	 * valid/invalid indicator and details why invalid
	 * 
	 * @param addressList list of all the Address POJOs
	 */

	protected void printValidatedAddresses(List<Address> addressList) {
		log.info("\n\n -----------------VALIDATION INFO FOR All ADDRESS DETAILS BELOW--------------------");

		for (Address address : addressList) {
			log.info(getValidatedAdddressString(address));
		}
	}

	/**
	 * Method that builds up string of Address and indicates if the address is valid
	 * or not and if not why not
	 * 
	 * @param address Address POJO
	 * @return String string used to display the address details, valid/invalid
	 *         indicator and reason why not valid
	 */

	private String getValidatedAdddressString(Address address) {
		AddressLineDetails addressLineDetail = address.getAddressLineDetail();
		ProvinceOrStateDetails provinceOrState = address.getProvinceOrState();
		CountryDetails country = address.getCountry();
		List<String> invalidAddressList = address.getInvalidAddressList();

		String addressLines = (addressLineDetail != null
				? (addressLineDetail.getLine1() + " " + addressLineDetail.getLine2())
				: "n/a");
		String provinceOrStateStr = (provinceOrState != null ? provinceOrState.getName() : "n/a");
		String countryStr = (country != null ? country.getName() : "n/a");

		String invalidMessage = "";
		for (String invalidString : invalidAddressList) {
			invalidMessage += String.format("%s \n", invalidString);
		}

		String validationStr = (address.getIsAddressValid() ? "Address is Valid"
				: "Address is invalid because: \n" + invalidMessage);
		String format = String.format("\n%s: \n %s \n %s \n %s \n %s \n %s \n%s", address.getType().getName(),
				addressLines, address.getCityOrTown(), provinceOrStateStr, address.getPostalCode(), countryStr,
				validationStr);
		return format;

	}

}
