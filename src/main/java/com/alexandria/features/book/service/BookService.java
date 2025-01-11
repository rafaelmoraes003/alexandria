package com.alexandria.features.book.service;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.book.entity.Book;
import com.alexandria.features.book.entity.BookDetail;
import com.alexandria.features.book.repository.BookDetailRepository;
import com.alexandria.features.book.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final BookDetailRepository bookDetailRepository;

  @Autowired
  public BookService(
      BookRepository bookRepository,
      BookDetailRepository bookDetailRepository
  ) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
  }

  public Book findById(Long id) throws ObjectNotFoundException {
    return bookRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("book not found."));
  }

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public List<Book> findAll(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Book> page = bookRepository.findAll(pageable);

    return page.toList();
  }

  public Book create(Book book) {
    return bookRepository.save(book);
  }

  public Book update(Long id, Book book) throws ObjectNotFoundException {
    Book bookFromDb = findById(id);

    bookFromDb.setTitle(book.getTitle());
    bookFromDb.setGenre(book.getGenre());

    return bookRepository.save(bookFromDb);
  }

  public Book deleteById(Long id) throws ObjectNotFoundException {
    Book book = findById(id);

    bookRepository.deleteById(id);

    return book;
  }

  public BookDetail createBookDetail(Long bookId, BookDetail bookDetail)
      throws ObjectNotFoundException {
    Book book = findById(bookId);
    bookDetail.setBook(book);
    return bookDetailRepository.save(bookDetail);
  }

}
