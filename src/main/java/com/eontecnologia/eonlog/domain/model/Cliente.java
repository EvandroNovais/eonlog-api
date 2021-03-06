package com.eontecnologia.eonlog.domain.model;

import com.eontecnologia.eonlog.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//Using Lombok to generate EqualsAndHashCode and Getters and Setters.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

	@NotNull(groups = ValidationGroups.ClienteId.class)
	@EqualsAndHashCode.Include	//Specifies that only for the id EqualsAndHashCode is being generated.	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "fone")
	private String telefone;
		
}
