package com.alexandria.features.author.controller;

import com.alexandria.exception.ObjectNotFoundException;
import com.alexandria.features.author.dto.AuthorCreationDto;
import com.alexandria.features.author.dto.AuthorDto;
import com.alexandria.features.author.entity.Author;
import com.alexandria.features.author.service.AuthorService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

  private final AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id)
      throws ObjectNotFoundException {
    Author author = authorService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(AuthorDto.fromEntity(author));
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> getAllAuthors() {
    List<AuthorDto> authors = authorService.findAll().stream()
        .map(AuthorDto::fromEntity)
        .toList();
    return ResponseEntity.status(HttpStatus.OK).body(authors);
  }

  @PostMapping
  public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorCreationDto authorCreationDto) {
    Author author = authorService.create(authorCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(AuthorDto.fromEntity(author));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id,
      @RequestBody AuthorCreationDto authorCreationDto) throws ObjectNotFoundException {
    Author author = authorService.update(id, authorCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.OK).body(AuthorDto.fromEntity(author));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AuthorDto> deleteAuthorById(@PathVariable Long id)
      throws ObjectNotFoundException {
    Author author = authorService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(AuthorDto.fromEntity(author));
  }
}
