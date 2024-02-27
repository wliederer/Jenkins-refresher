package com.family.refresh.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import com.family.refresh.models.Address;
import com.family.refresh.repo.AddressRepository;
import com.family.refresh.service.AddressService;

//@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class AddressServiceTests {

	@Mock
	private AddressRepository addressRepository;
	 @Mock
	    private CacheManager cacheManager;
//	@Autowired
	 @InjectMocks
	private AddressService addressService;
	
	@BeforeEach
	void setUp() {
		 MockitoAnnotations.openMocks(this);

	}
	
	@Test
	void canGetAddressById() {
		long id = 1;
		Address address = new Address();
		address.setId(id);
		address.setStreet("testStreet");
		
		Mockito.when(addressRepository.findById(1l)).thenReturn(Optional.of(address));
		
		// Mock caching behavior
        Mockito.when(cacheManager.getCache("address"))
                .thenReturn(new MockCache("address", address));
		
		Optional<Address> found = addressService.getById(id);
		
		assertThat(found.get().getStreet()).isEqualTo(address.getStreet());
		
	}
}
