package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookValidationTests {

	private static Validator validator;
	
	@BeforeAll
	public static void setup() {
		ValidatorFactory validatorfactory = Validation.buildDefaultValidatorFactory();
		validator = validatorfactory.getValidator();
	}
	
	@Test
	public void whenAllFieldsCorrectThenValidationSucceeds() {
		var book = new Book(null, "1234567891", "Title", "Author", 9.90, "LeftWord", Instant.now(), Instant.now(), 0);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).isEmpty();
	}
	
	@Test
	public void whenIsbnDefinedButInvalidThenValidationFails() {
		var book = new Book(null, "123ABC4567891", "Title", "Author", 9.90, "LeftWord", Instant.now(), Instant.now(), 0);
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
		.isEqualTo("The ISBN format must be valid.");
	}
}
