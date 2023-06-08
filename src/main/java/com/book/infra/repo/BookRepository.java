package com.book.infra.repo;

import com.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jinx
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
