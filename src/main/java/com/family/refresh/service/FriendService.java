package com.family.refresh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.refresh.models.Friend;
import com.family.refresh.repo.FriendRepository;

@Service
public class FriendService {
	
	@Autowired
	private FriendRepository friendRepository;
	
	public Optional<Friend> getById(Long id) {
		return friendRepository.findById(id);
	}

}
