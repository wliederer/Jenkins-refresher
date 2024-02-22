package com.family.refresh.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.refresh.models.Friend;
import com.family.refresh.service.FriendService;

@RestController
@RequestMapping("/friends")
public class FriendController {
	
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	private FriendService friendService;
	
	@GetMapping("/{id}")
	public Optional<Friend> getById(@PathVariable Long id) {
		logger.info("Searching friend by id "+ id);
		return friendService.getById(id);
		
	}

}
