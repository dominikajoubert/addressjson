package com.investec.addressjson.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.investec.addressjson.pojo.Address;

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
		return address.toPrettyPrintString();
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
		return address.toValidatedString();
	}

}
