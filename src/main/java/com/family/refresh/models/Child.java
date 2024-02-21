package com.family.refresh.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;


@Entity
public class Child extends Person {
	
	@ManyToOne
	@Exclude
	private Parent parent;

}
