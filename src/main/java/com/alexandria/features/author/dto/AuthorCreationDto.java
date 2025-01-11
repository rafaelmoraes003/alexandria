package com.alexandria.features.author.dto;

import com.alexandria.features.author.entity.Author;

public record AuthorCreationDto(String name, String nationality) {

  public Author toEntity() {
    return new Author(name, nationality);
  }
}
