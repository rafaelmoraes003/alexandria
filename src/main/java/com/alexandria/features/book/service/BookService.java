package com.alexandria.features.book.service;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.book.entity.Book;
import com.alexandria.features.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;

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

}
