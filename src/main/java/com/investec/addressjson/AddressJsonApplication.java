package com.investec.addressjson;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.investec.addressjson.manager.AddressManager;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AddressJsonApplication implements CommandLineRunner {

	@Autowired
	private AddressManager addressManager;

	public static void main(String[] args) {
		SpringApplication.run(AddressJsonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String filePath = "";
		String addressType = "";

		Scanner sc = new Scanner(System.in);

		try {
			System.out.print("Please enter the path to the file used e.g. c:/addresses.json :");
			filePath = sc.nextLine().trim();
			System.out.print(
					"Please enter the address type (POSTAL,BUSINESS,PHYSICAL) you wish to view. If no address type entered then all address types will be shown:");
			addressType = sc.nextLine().trim();
			sc.close();
		} catch (Exception e) {
			log.error("Error Occured will getting values from user.");
			throw new RuntimeException("Error Occured will getting values from user.", e);
		}

		addressManager.processPrintingOfAddressFile(filePath, addressType);
	}

}
