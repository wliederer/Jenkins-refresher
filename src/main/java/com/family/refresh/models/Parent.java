package com.family.refresh.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
//@RedisHash("Parent")
public class Parent extends Person {
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id")
	private List<Child> children;

}
