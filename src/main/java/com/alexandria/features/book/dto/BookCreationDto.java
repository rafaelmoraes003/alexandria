package com.alexandria.features.book.dto;

import com.alexandria.features.book.entity.Book;

public record BookCreationDto(String title, String genre) {

  public Book toEntity() {
    return new Book(title, genre);
  }

}