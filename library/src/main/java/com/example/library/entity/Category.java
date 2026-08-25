package com.example.library.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "category_code", nullable = false, unique = true)
	private String categoryCode;

	@Column(name = "category_name", nullable = false)
	private String categoryName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private Account createdBy;

	@Column(name = "delete_at")
	private LocalDateTime deleteAt;

	@Column(name = "version", nullable = false)
	private Long version;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;


}