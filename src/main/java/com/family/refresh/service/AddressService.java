package com.family.refresh.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.family.refresh.models.Address;
import com.family.refresh.repo.AddressRepository;

@Service
public class AddressService {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

	@Autowired
	private AddressRepository addressRepository;
	
	@Cacheable(value="address")
	public Optional<Address> getById(Long id) {
		logger.info("Getting Address by id from DB " + id);
		return addressRepository.findById(id);
	}
}
