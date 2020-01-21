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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Linov.JobPoster.model.CityModel;
import com.Linov.JobPoster.service.CityService;
import com.Linov.JobPoster.service.ProvinceService;

@RestController
@CrossOrigin("*")
public class saveCityController {
	
	@Autowired
	private ProvinceService posr;
	
	@Autowired
	private CityService citys;
	
	private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";


	@PostMapping("/pro")
	public void savedatabase() {
		List<CityModel> provs = readProvinceFromCSV("D:\\list city\\Aceh-11.csv");

		for (CityModel pro : provs) {
			citys.save(pro);
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

	
	@GetMapping("/prov/city/{name}")
	public ResponseEntity<?> findPro(@PathVariable("name") String pro){
		return ResponseEntity.ok(citys.citis(pro));
	}
	private List<CityModel> readProvinceFromCSV(String fileName) {
		List<CityModel> province = new ArrayList<CityModel>();
		Path pathToFile = Paths.get(fileName);

		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			int i =0;
			String line = br.readLine();
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");
				String code ="A"+i;
				CityModel book = createBook(attributes,code);
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

	private CityModel createBook(String[] metadata,String code) {
		String prov = metadata[0];
		// create and return book of this metadata
		return new CityModel(code, prov,posr.findByprovince("P0"));
	}


}
