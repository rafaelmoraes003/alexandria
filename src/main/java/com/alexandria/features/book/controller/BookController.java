package com.alexandria.features.book.controller;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.book.dto.BookCreationDto;
import com.alexandria.features.book.dto.BookDetailCreationDto;
import com.alexandria.features.book.dto.BookDetailDto;
import com.alexandria.features.book.dto.BookDto;
import com.alexandria.features.book.entity.Book;
import com.alexandria.features.book.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> getBookById(@PathVariable Long id) throws ObjectNotFoundException {
    Book book = bookService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(BookDto.fromEntity(book));
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize
  ) {
    List<Book> books = bookService.findAll(pageNumber, pageSize);
    return ResponseEntity.status(HttpStatus.OK).body(books);
  }

  @PostMapping
  public ResponseEntity<BookDto> createBook(@RequestBody BookCreationDto bookCreationDto) {
    Book book = bookService.create(bookCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.OK).body(BookDto.fromEntity(book));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDto> updateBook(@PathVariable Long id,
      @RequestBody BookCreationDto bookCreationDto
  ) throws ObjectNotFoundException {
    Book book = bookService.update(id, bookCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.OK).body(BookDto.fromEntity(book));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BookDto> deleteBookById(@PathVariable Long id)
      throws ObjectNotFoundException {
    Book book = bookService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(BookDto.fromEntity(book));
  }

  @PostMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> createBookDetail(
      @PathVariable Long bookId,
      @RequestBody BookDetailCreationDto bookDetailCreationDto
  ) throws ObjectNotFoundException {
    BookDetailDto bookDetailDto = BookDetailDto.fromEntity(
        bookService.createBookDetail(bookId, bookDetailCreationDto.toEntity())
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(bookDetailDto);
  }

  @GetMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> getBookDetail(@PathVariable Long bookId)
      throws ObjectNotFoundException {
    BookDetailDto bookDetailDto = BookDetailDto.fromEntity(
        bookService.getBookDetail(bookId)
    );
    return ResponseEntity.status(HttpStatus.OK).body(bookDetailDto);
  }

  @PutMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> updateBookDetail(@PathVariable Long bookId,
      @RequestBody BookDetailCreationDto bookDetailCreationDto)
      throws ObjectNotFoundException {
    BookDetailDto bookDetailDto = BookDetailDto.fromEntity(
        bookService.updateBookDetail(bookId, bookDetailCreationDto.toEntity())
    );
    return ResponseEntity.status(HttpStatus.OK).body(bookDetailDto);
  }

  @DeleteMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> removeBookDetail(@PathVariable Long bookId)
      throws ObjectNotFoundException {
    BookDetailDto bookDetailDto = BookDetailDto.fromEntity(
        bookService.removeBookDetail(bookId)
    );
    return ResponseEntity.status(HttpStatus.OK).body(bookDetailDto);
  }

  @PutMapping("/{bookId}/publisher/{publisherId}")
  public ResponseEntity<BookDto> setBookPublisher(
      @PathVariable Long bookId,
      @PathVariable Long publisherId
  ) throws ObjectNotFoundException {
    BookDto bookDto = BookDto.fromEntity(
        bookService.setBookPublisher(bookId, publisherId)
    );
    return ResponseEntity.status(HttpStatus.OK).body(bookDto);
  }

  @DeleteMapping("/{bookId}/publisher")
  public ResponseEntity<BookDto> removeBookPublisher(@PathVariable Long bookId)
      throws ObjectNotFoundException {
    BookDto bookDto = BookDto.fromEntity(
        bookService.removeBookPublisher(bookId)
    );
    return ResponseEntity.status(HttpStatus.OK).body(bookDto);
  }

  @PutMapping("/{bookId}/authors/{authorId}")
  public ResponseEntity<BookDto> addBookAuthor(@PathVariable Long bookId,
      @PathVariable Long authorId) throws ObjectNotFoundException {
    BookDto bookDto = BookDto.fromEntity(
        bookService.addBookAuthor(bookId, authorId)
    );
    return ResponseEntity.status(HttpStatus.OK).body(bookDto);
  }

  @DeleteMapping("/{bookId}/authors/{authorId}")
  public ResponseEntity<BookDto> removeBookAuthor(@PathVariable Long bookId,
      @PathVariable Long authorId) throws ObjectNotFoundException {
    BookDto bookDto = BookDto.fromEntity(
        bookService.removeBookAuthor(bookId, authorId)
    );
    return ResponseEntity.status(HttpStatus.OK).body(bookDto);
  }
}
