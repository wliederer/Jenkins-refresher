package com.family.refresh.models;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Person implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@NotBlank
	protected String firstName;
	@NotBlank
	protected String lastName;
	
	@PastOrPresent
	@NotNull
//	@JsonDeserialize(using = CustomDateDeserializer.class)
	protected LocalDate birthday;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id", referencedColumnName = "id")
	@NotNull
	protected Address address;
	
	protected LocalDate dateCreated;
	protected LocalDate dateUpdated;
	
}
