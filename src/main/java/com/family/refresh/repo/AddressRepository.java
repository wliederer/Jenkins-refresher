package com.family.refresh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.refresh.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
