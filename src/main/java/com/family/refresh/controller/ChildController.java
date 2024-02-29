package com.family.refresh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.refresh.service.ChildService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/childs")
@Validated
public class ChildController {
	
	@Autowired
	private ChildService childService;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteChild(@Valid @PathVariable Long id){
		childService.deleteChild(id);
		return new ResponseEntity<>("Child Deleted",HttpStatus.ACCEPTED);
	}

}
