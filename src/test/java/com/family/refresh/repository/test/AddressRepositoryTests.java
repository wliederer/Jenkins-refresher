package com.family.refresh.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.family.refresh.models.Address;
import com.family.refresh.repo.AddressRepository;

@DataJpaTest
public class AddressRepositoryTests {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Test
	void itShouldGetAddressById() {
		Address address = new Address();
		String street = "testStreet";
		long id = 1;
		address.setStreet(street);
		address.setId(id);
		
		addressRepository.save(address);
		
	 Optional<Address> found = addressRepository.findById(id);
	 
	 assertThat(found.get().getStreet()).isEqualTo(address.getStreet());
	}

}
