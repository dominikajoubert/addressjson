package com.investec.addressjson.manager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.investec.addressjson.pojo.Address;
import com.investec.addressjson.util.AddressConstants;
import com.investec.addressjson.util.AddressUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddressManager {

	@Autowired
	private AddressUtil addressUtil;
	@Autowired
	private PrettyPrintManager prettyPrintManager;
	@Autowired
	private AddressValidator addressValidator;

	/**
	 * Method that processes the Json file and then prints all the different type of
	 * printing (PrettyPrint, Specific address type print and address validation
	 * Print)
	 * 
	 * @param filePath    Path to the Json file
	 * @param addressType Address type enter by user, or NULL if no type was entered
	 * @return List of all Addresses in file, mostly added for testing
	 */

	public List<Address> processPrintingOfAddressFile(String filePath, String addressType) {
		List<Address> addressList = processAddressFile(filePath);
		processAllAdressPrinting(addressType, addressList);
		return addressList;
	}

	/**
	 * Method that processes the Json file and maps it to the Address POJO
	 * 
	 * @param filePath Path to the Json file
	 * @return List of all Addresses in file
	 */

	private List<Address> processAddressFile(String filePath) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return Arrays.asList(mapper.readValue(Paths.get(filePath).toFile(), Address[].class));
		} catch (JsonParseException e) {
			log.error("Error Occured while parsing file, please check format in file is correct.", e);
			throw new RuntimeException("Error Occured while parsing file, please check format in file is correct.", e);
		} catch (JsonMappingException e) {
			log.error("Error Occured while mapping data.", e);
			throw new RuntimeException("Error Occured while mapping data.", e);
		} catch (IOException e) {
			log.error("I/O Exception occured when trying to read file from path. Double check path. ", e);
			throw new RuntimeException("Error Occured no file found in specified path.", e);
		}
	}

	/**
	 * Method that prints all the different type of printing (PrettyPrint, Specific
	 * address type print and address validation Print)
	 * 
	 * @param addressType Address type enter by user, or NULL if no type was entered
	 * @param addressList List of all the addresses extracted from Json file
	 */

	private void processAllAdressPrinting(String addressType, List<Address> addressList) {
		prettyPrintManager.prettyPrintAllAddress(addressList);
		printSpecifiedAddressType(addressList, addressType);
		addressValidator.validateAddressList(addressList);
		prettyPrintManager.printValidatedAddresses(addressList);
	}

	/**
	 * Method that determines what address type details should be shown based on
	 * what user entered. If no address type entered then all address type details
	 * will be shown
	 *
	 * @param addressList List of all the addresses extracted from Json file
	 * @param addressType Address type enter by user, or NULL if no type was entered
	 */

	private void printSpecifiedAddressType(List<Address> addressList, String addressType) {
		if (addressType != null && !addressType.isEmpty()) {
			if (addressUtil.isValidAddressType(addressType)) {
				prettyPrintManager.prettyPrintAddressOfType(addressType, addressList);
			} else {
				log.error("Incorrect Address Type specified.");
			}
		} else {
			prettyPrintManager.prettyPrintAddressOfType(AddressConstants.POSTAL, addressList);
			prettyPrintManager.prettyPrintAddressOfType(AddressConstants.PHYSICAL, addressList);
			prettyPrintManager.prettyPrintAddressOfType(AddressConstants.BUSINESS, addressList);
		}
	}
}