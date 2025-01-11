package com.alexandria.features.book.dto;

import com.alexandria.features.book.entity.BookDetail;

public record BookDetailDto(
    Long id,
    String summary,
    Integer pageCount,
    Integer year,
    String isbn
) {

  public static BookDetailDto fromEntity(BookDetail bookDetail) {
    return new BookDetailDto(
        bookDetail.getId(),
        bookDetail.getSummary(),
        bookDetail.getPageCount(),
        bookDetail.getYear(),
        bookDetail.getIsbn()
    );
  }
}