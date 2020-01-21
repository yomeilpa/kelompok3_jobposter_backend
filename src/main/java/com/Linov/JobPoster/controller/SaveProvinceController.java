package com.Linov.JobPoster.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Linov.JobPoster.model.ProvinceModel;
import com.Linov.JobPoster.service.ProvinceService;

@RestController
@CrossOrigin("*")
public class SaveProvinceController {
	private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

	@Autowired
	private ProvinceService posr;

	@PostMapping("/citypos")
	public void savedatabase() {
		List<ProvinceModel> provs = readProvinceFromCSV("E:\\BOOTCAMP\\bahan project\\provincesaaa.csv");

		for (ProvinceModel pro : provs) {
			System.out.println(pro.getProvince());
			posr.saveProv(pro);
		}

	}

	public String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	private List<ProvinceModel> readProvinceFromCSV(String fileName) {
		List<ProvinceModel> province = new ArrayList<ProvinceModel>();
		Path pathToFile = Paths.get(fileName);

		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			String line = br.readLine();
			int i = 0;
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");
				String code = "P"+i;
				ProvinceModel book = createBook(attributes,code);
				i++;
				// adding book into ArrayList
				province.add(book);

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return province;

	}
	
	@GetMapping("/province")
	public ResponseEntity<?> getProvince(){
		return ResponseEntity.ok(posr.find());
	}

	private ProvinceModel createBook(String[] metadata,String code) {
		String prov = metadata[0];

		// create and return book of this metadata
		return new ProvinceModel(code, prov);
	}


}
