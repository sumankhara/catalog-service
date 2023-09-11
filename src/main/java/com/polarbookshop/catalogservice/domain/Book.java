package com.polarbookshop.catalogservice.domain;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Book {
	
	@Id
	private Long id;
 
	@NotBlank(message = "The book ISBN must be defined.")
	@Pattern(
		regexp = "^([0-9]{10}|[0-9]{13})$",
		message = "The ISBN format must be valid."
	)
	@JsonProperty("isbn")
	private String isbn;
	
	@NotBlank(message = "The book title must be defined.")
	@JsonProperty("title")
	private String title;
	
	@NotBlank(message = "The book author must be defined.")
	@JsonProperty("author")
	private String author;
	
	@NotNull(message = "The book price must be defined.")
	@Positive(message = "The book price must be greater than zero.")
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("publisher")
	private String publisher;
	
	@CreatedDate
	private Instant createdDate;
	
	@LastModifiedDate
	private Instant lastModifiedDate;
	
	@Version
	private int version;
}
