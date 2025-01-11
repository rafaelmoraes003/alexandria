package com.alexandria.features.book.repository;

import com.alexandria.features.book.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {

}
