package com.alexandria.features.author.service;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.author.entity.Author;
import com.alexandria.features.author.repository.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Author findById(Long id) throws ObjectNotFoundException {
    return authorRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("author not found."));
  }

  public List<Author> findAll() {
    return authorRepository.findAll();
  }

  public Author create(Author author) {
    return authorRepository.save(author);
  }

  public Author update(Long id, Author author) throws ObjectNotFoundException {
    Author authorFromDb = findById(id);

    authorFromDb.setName(author.getName());
    authorFromDb.setNationality(author.getNationality());

    return authorRepository.save(authorFromDb);
  }

  public Author deleteById(Long id) throws ObjectNotFoundException {
    Author author = findById(id);

    authorRepository.deleteById(id);

    return author;
  }
}
