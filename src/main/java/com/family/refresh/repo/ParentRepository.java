package com.family.refresh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.refresh.models.Parent;
import com.family.refresh.models.Person;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
	


}
