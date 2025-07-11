package com.twogether.deokhugam.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.twogether.deokhugam.book.dto.BookDto;
import com.twogether.deokhugam.book.dto.request.BookCreateRequest;
import com.twogether.deokhugam.book.entity.Book;
import com.twogether.deokhugam.book.repository.BookRepository;
import com.twogether.deokhugam.book.service.BookService;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @Mock
  private ThumbnailUploader thumbnailUploader;

  @InjectMocks
  private BookService bookService;

  @Test
  void registerBook() {
    // given
    BookCreateRequest request = new BookCreateRequest(
        "더쿠의 심리학",
        "박인규",
        "더쿠에 대한 심도깊은 해설",
        "이북리더즈",
        LocalDate.of(1989, 5, 12),
        null // ISBN 없음
    );

    ArgumentCaptor<Book> bookCaptor = ArgumentCaptor.forClass(Book.class);
    UUID generatedId = UUID.randomUUID();
    Instant now = Instant.now();

    Book savedBook = new Book(
        generatedId,
        "더쿠의 심리학",
        "박인규",
        "더쿠에 대한 심도깊은 해설",
        "이북리더즈",
        LocalDate.of(1989, 5, 12),
        null,
        null,
        0,
        0.0f,
        now,
        now
    );

    when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

    // when
    BookDto result = bookService.registerBook(request);

    // then
    verify(bookRepository).save(bookCaptor.capture());
    Book saved = bookCaptor.getValue();

    assertThat(result).isNotNull();
    assertThat(saved.getIsbn()).isNull();
    assertThat(saved.getTitle()).isEqualTo("더쿠의 심리학");
    assertThat(result.title()).isEqualTo("더쿠의 심리학");
  }
}
