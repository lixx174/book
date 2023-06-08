package com.book.application.journal;

import com.book.application.ReplyPage;
import com.book.domain.Book;
import com.book.domain.Journal;
import com.book.domain.User;
import com.book.infra.mapper.JournalMapper;
import com.book.infra.repo.BookRepository;
import com.book.infra.repo.JournalRepository;
import com.book.infra.repo.UserRepository;
import com.book.vender.SecurityUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author jinx
 */
@Service
@RequiredArgsConstructor
public class JournalService {

    private final BookRepository bookRepo;
    private final JournalRepository journalRepo;
    private final UserRepository userRepo;
    private final JournalMapper mapper;


    public ReplyPage<JournalDto> page(JournalPageDto dto) {
        Page<Journal> page = journalRepo.findAll(
                (Root<Journal> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
                    List<Predicate> predicates = new LinkedList<>() {{
                        if (dto.getType() != null) {
                            add(builder.equal(root.get("type"), dto.getType()));
                        }

                    }};

                    return query.where(predicates.toArray(new Predicate[0])).getRestriction();
                },
                dto.page()
        );

        return ReplyPage.of(page, mapper.convert(page.getContent()));
    }

    public JournalDto info(Journal journal) {
        return mapper.convert(journal);
    }


    public void add(JournalPersistDto dto) {
        User user = SecurityUtil.getUser(userRepo);

        Optional<Book> book = bookRepo.findById(dto.getBookId());
        if (book.isEmpty()) {
            throw new IllegalArgumentException("非法bookId :%s".formatted(dto.getBookId()));
        }

        Journal journal = new Journal(
                book.get(),
                user,
                dto.getType(),
                dto.getCategory(),
                dto.getAmount(),
                dto.getRemark()
        );

        journalRepo.save(journal);
    }

    public void delete(Journal journal) {
        journalRepo.delete(journal);
    }
}
