package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.polarbookshop.catalogservice.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void whenPostRequestThenBookCreated() {
		var expectedBook = new Book("1234567891", "Title", "Author", 9.90);
		webTestClient.post().uri("/books").bodyValue(expectedBook).exchange().expectStatus().isCreated()
				.expectBody(Book.class).value(actual -> {
					assertThat(actual).isNotNull();
					assertThat(actual.getTitle()).isEqualTo(expectedBook.getTitle());
				});
	}

}
