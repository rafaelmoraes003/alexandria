package com.alexandria.features.book.dto;

import com.alexandria.features.book.entity.BookDetail;

public record BookDetailCreationDto(
    String summary,
    Integer pageCount,
    Integer year,
    String isbn) {

  public BookDetail toEntity() {
    return new BookDetail(summary, pageCount, year, isbn);
  }
}
