package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.polarbookshop.catalogservice.config.DataConfig;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;
	
	@Ignore
    void findBookByIsbnWhenExisting() {
        var bookIsbn = UUID.randomUUID().toString();
        var book = new Book(1L, bookIsbn, "Title", "Author", 12.90, "LeftWord", Instant.now(), Instant.now(), 0);
        jdbcAggregateTemplate.insert(book);

        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().getIsbn()).isEqualTo(book.getIsbn());
    }
}
