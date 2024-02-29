package com.family.refresh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.refresh.repo.ChildRepository;

@Service
public class ChildService {
	
	@Autowired
	private ChildRepository childRepository;
	
	public void deleteChild(Long id) {
		childRepository.deleteById(id);
	}

}
