package com.family.refresh.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.refresh.models.Address;
import com.family.refresh.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Address>> getById(@PathVariable Long id) {
		logger.info("Searching address by id "+ id);
		Optional<Address> address = addressService.getById(id);
		return new ResponseEntity<>(address,HttpStatus.OK);
		
	}
}
