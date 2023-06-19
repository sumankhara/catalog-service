package com.polarbookshop.catalogservice.domain;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class Book {
 
	@NotBlank(message = "The book ISBN must be defined.")
	@Pattern(
		regexp = "^([0-9]{10}|[0-9]{13})$",
		message = "The ISBN format must be valid."
	)
	private final String isbn;
	
	@NotBlank(message = "The book title must be defined.")
	private final String title;
	
	@NotBlank(message = "The book author must be defined.")
	private final String author;
	
	@NotNull(message = "The book price must be defined.")
	@Positive(message = "The book price must be greater than zero.")
	private final Double price;
	
	private Book() {
		this.isbn = null;
		this.title = null;
		this.author = null;
		this.price = 0.0;
	}

	public Book(String isbn, String title, String author, Double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn, title);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		} else if(!(obj instanceof Book)) {
			return false;
		} else {
			Book book = (Book) obj;
			return Objects.equals(title, book.title)
					&& Objects.equals(author, book.author);
		}
	}
	
}
