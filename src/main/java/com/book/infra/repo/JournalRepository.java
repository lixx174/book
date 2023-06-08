package com.book.infra.repo;

import com.book.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author jinx
 */
@Repository
public interface JournalRepository extends JpaRepository<Journal, Long>, JpaSpecificationExecutor<Journal> {

}
