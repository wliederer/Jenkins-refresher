package com.family.refresh.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.refresh.models.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {

}
