package com.polarbookshop.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.polarbookshop.catalogservice.domain.Book;

@JsonTest
public class BookJsonTests {

	@Autowired
	private JacksonTester<Book> json;
	
	@Test
	public void testSerialize() throws Exception {
		var book = new Book(null, "1234567891", "Title", "Author", 9.90, "LeftWord", null, null, 0);
		var jsonContent = json.write(book);
		assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo(book.getIsbn());
		assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo(book.getTitle());
		assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo(book.getAuthor());
		assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(book.getPrice());
	}
	
	@Test
	public void testDeserialize() throws Exception {
		var content = "{\"isbn\": \"1234567891\", \"title\": \"Title\", \"author\": \"Author\", \"price\": 9.90}";
		assertThat(json.parse(content))
		.usingRecursiveComparison()
		.isEqualTo(new Book(null, "1234567891", "Title", "Author", 9.90, "LeftWord", null, null, 0));
	}
}
