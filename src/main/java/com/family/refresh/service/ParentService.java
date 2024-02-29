package com.family.refresh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.family.refresh.models.Child;
import com.family.refresh.models.Parent;
import com.family.refresh.repo.ChildRepository;
import com.family.refresh.repo.ParentRepository;

import jakarta.transaction.Transactional;

@Service
public class ParentService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParentService.class);

	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired 
	private ChildRepository childRepository;
	
//	@Autowired
//	private ChildRepository childRepository;
	
//	@Cacheable(value="parents", key="'all'")
	public List<Parent> getAllParents() {
		logger.info("Getting all parents from db");
		return parentRepository.findAll();
	}
	
//	@CacheEvict(value="parents", key="'all'")
	public Parent insertParent(Parent parent) {
		Parent p = parentRepository.save(parent);
		List<Child> children = new ArrayList<>();
		p.getChildren().stream().forEach(c-> {c.setParent(p);
		children.add(c);});
		
		childRepository.saveAll(children);
		
		
		logger.info("Parent saved and children updated "+ children);
		return p;
		


	}
	
//	@CacheEvict(value="parents", key="'all'")
	@Transactional
	public Parent updateParent(Parent parent, Long id) {
		Parent p = parentRepository.findById(id).orElse(null);
		List<Long> deleteChildren = new ArrayList<>();
		List<Child> saveChildren = new ArrayList<>();
		logger.info("Updating parent " + p);
	
		if(p != null) {
			p.getChildren().stream().forEach(c->{
				if(!parent.getChildren().contains(c)) {
					deleteChildren.add(c.getId());
				}
			});
			if(p.getChildren().isEmpty()) {
				
				parent.getChildren().stream().forEach(c->{
					
						c.setParent(parent);
						saveChildren.add(c);
					
				});
			}else {
				parent.getChildren().stream().forEach(c->{
					if(!p.getChildren().contains(c)) {
						c.setParent(parent);
						saveChildren.add(c);
					}
				});
				
			}
		}
		
		
		if(!deleteChildren.isEmpty()) {
			logger.info("delete children "+ deleteChildren);
						
			
			childRepository.deleteAllById(deleteChildren);

		}
		
		if(!saveChildren.isEmpty()) {
			logger.info("save children "+ saveChildren);
			childRepository.saveAll(saveChildren);
		}
		
		return parentRepository.save(parent);
	}
	
	public void deleteParent(Long id) {
		logger.info("Deleting parent " + id);
		parentRepository.deleteById(id);
	}
	
	
	
}
