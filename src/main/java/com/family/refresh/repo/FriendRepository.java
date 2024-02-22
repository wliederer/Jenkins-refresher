package com.family.refresh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.refresh.models.Friend;


public interface FriendRepository extends JpaRepository<Friend,Long> {

}
