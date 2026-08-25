package com.example.library.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Column(name = "delete_at")
	private LocalDateTime deleteAt;

	@Column(name = "version", nullable = false)
	private Long version;

	@Column(name = "staff_code", unique = true)
	private String staffCode;


}