package com.alexandria.features.book.service;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.book.entity.Book;
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

  @Autowired
  public BookService(
      BookRepository bookRepository
  ) {
    this.bookRepository = bookRepository;
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

}
