package com.family.refresh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.refresh.models.Child;
import com.family.refresh.models.Parent;
import com.family.refresh.repo.ChildRepository;
import com.family.refresh.repo.ParentRepository;

@Service
public class ParentService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParentService.class);

	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private ChildRepository childRepository;
	
	
	public List<Parent> getAllParents() {
		return parentRepository.findAll();
	}
	
	public Parent insertParent(Parent parent) {
		
		return parentRepository.save(parent);


	}
	
	
	
}
