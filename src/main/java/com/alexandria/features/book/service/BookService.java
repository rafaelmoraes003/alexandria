package com.alexandria.features.book.service;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.author.entity.Author;
import com.alexandria.features.author.service.AuthorService;
import com.alexandria.features.book.entity.Book;
import com.alexandria.features.book.entity.BookDetail;
import com.alexandria.features.book.repository.BookDetailRepository;
import com.alexandria.features.book.repository.BookRepository;
import com.alexandria.features.publisher.entity.Publisher;
import com.alexandria.features.publisher.service.PublisherService;
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
  private final PublisherService publisherService;
  private final AuthorService authorService;

  @Autowired
  public BookService(
      BookRepository bookRepository,
      BookDetailRepository bookDetailRepository,
      PublisherService publisherService,
      AuthorService authorService
  ) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
    this.publisherService = publisherService;
    this.authorService = authorService;
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

  public BookDetail getBookDetail(Long bookId) throws ObjectNotFoundException {
    Book book = findById(bookId);
    BookDetail bookDetail = book.getDetail();

    if (bookDetail == null) {
      throw new ObjectNotFoundException("book detail not found.");
    }

    return bookDetail;
  }

  public BookDetail updateBookDetail(Long bookId, BookDetail bookDetail)
      throws ObjectNotFoundException {
    BookDetail bookDetailDB = getBookDetail(bookId);

    bookDetailDB.setSummary(bookDetail.getSummary());
    bookDetailDB.setPageCount(bookDetail.getPageCount());
    bookDetailDB.setYear(bookDetail.getYear());
    bookDetailDB.setIsbn(bookDetail.getIsbn());

    return bookDetailRepository.save(bookDetailDB);
  }

  public BookDetail removeBookDetail(Long bookId)
      throws ObjectNotFoundException {
    Book book = findById(bookId);
    BookDetail bookDetail = book.getDetail();

    if (bookDetail == null) {
      throw new ObjectNotFoundException("book detail not found.");
    }

    book.setDetail(null);
    bookDetail.setBook(null);

    bookDetailRepository.delete(bookDetail);

    return bookDetail;
  }

  public Book setBookPublisher(Long bookId, Long publisherId)
      throws ObjectNotFoundException {
    Book book = findById(bookId);
    Publisher publisher = publisherService.findById(publisherId);

    book.setPublisher(publisher);

    return bookRepository.save(book);
  }

  public Book removeBookPublisher(Long bookId) throws ObjectNotFoundException {
    Book book = findById(bookId);

    book.setPublisher(null);

    return bookRepository.save(book);
  }

  public Book addBookAuthor(Long bookId, Long authorId)
      throws ObjectNotFoundException {
    Book book = findById(bookId);
    Author author = authorService.findById(authorId);

    book.getAuthors().add(author);

    return bookRepository.save(book);
  }

}
