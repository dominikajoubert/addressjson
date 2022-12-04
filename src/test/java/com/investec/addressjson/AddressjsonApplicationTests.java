package com.investec.addressjson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.investec.addressjson.manager.AddressManager;
import com.investec.addressjson.manager.AddressValidator;
import com.investec.addressjson.pojo.Address;
import com.investec.addressjson.util.AddressConstants;

@SpringBootTest
class AddressJsonApplicationTests {

	@MockBean
	private AddressJsonApplication addressJsonApplication;
	@Autowired
	AddressManager addressManager;
	@Autowired
	AddressValidator addressValidator;

	@Test
	public void testReadingFile() {

		String path = "./src/test/java/com/investec/addressjson";
		String fileName = "addresses.json";

		File file = new File(path, fileName);
		List<Address> addressList = addressManager.processPrintingOfAddressFile(file.getPath(),
				AddressConstants.PHYSICAL);

		Address address = addressList.get(0);

		assertEquals("City 1", address.getCityOrTown());
		assertEquals("1234", address.getPostalCode());
		assertNotEquals("Suburb 3", address.getSuburbOrDistrict());

		assertTrue(addressList.get(0).getIsAddressValid());
		assertFalse(addressList.get(1).getIsAddressValid());
		assertFalse(addressList.get(2).getIsAddressValid());
	}

}
