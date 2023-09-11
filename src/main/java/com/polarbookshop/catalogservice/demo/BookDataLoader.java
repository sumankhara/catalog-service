package com.polarbookshop.catalogservice.demo;

import java.time.Instant;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;

@Component
@Profile("testdata")
public class BookDataLoader {

	private final BookRepository bookRepository;

	public BookDataLoader(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		bookRepository.deleteAll();
		var book1 = new Book(null, "1234567891", "Northern Lights", "Lyra Silverstar", 9.90, "Penguin", Instant.now(), Instant.now(), 0);
		var book2 = new Book(null, "1234567892", "Polar Journey", "Iorek Polarson", 12.90, "Harper Collins", Instant.now(), Instant.now(), 0);
		bookRepository.saveAll(List.of(book1, book2));
	}
}
