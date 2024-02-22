package com.family.refresh.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.family.refresh.models.Friend;
import com.family.refresh.repo.FriendRepository;

@Service
public class FriendService {
	
	private static final Logger logger = LoggerFactory.getLogger(FriendService.class);
	
	@Autowired
	private FriendRepository friendRepository;
	
	@Cacheable(value="friend")
	public Optional<Friend> getById(Long id) {
		logger.info("getting friend from DB by id "+id);
		return friendRepository.findById(id);
	}

}
