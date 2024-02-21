package com.family.refresh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.refresh.models.Parent;
import com.family.refresh.service.ParentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/parents")
@Validated
public class ParentController {
	
	@Autowired
	private ParentService parentService;
	
	@GetMapping
	public ResponseEntity<List<Parent>> getAllParents() {
		List<Parent> parents = parentService.getAllParents();
		
		return new ResponseEntity<>(parents,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Parent> insertParent(@Valid @RequestBody Parent parent) {
		Parent p = parentService.insertParent(parent);
		
		return new ResponseEntity<>(p,HttpStatus.CREATED);
	}

}
