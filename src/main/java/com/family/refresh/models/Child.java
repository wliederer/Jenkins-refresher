package com.family.refresh.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;


@Entity
//@Getter
//@Setter
public class Child extends Person {
	
	@ManyToOne
	@Exclude
	private Parent parent;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Friend> friends;

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
	
	
 
}
