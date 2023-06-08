package com.book.application.book;

import com.book.application.ReplyPage;
import com.book.domain.Book;
import com.book.domain.User;
import com.book.infra.mapper.BookMapper;
import com.book.infra.repo.BookRepository;
import com.book.infra.repo.UserRepository;
import com.book.vender.Base64Util;
import com.book.vender.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jinx
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repo;
    private final UserRepository userRepo;
    private final BookMapper mapper;


    public ReplyPage<BookDto> page(BookPageDto dto) {
        User user = SecurityUtil.getUser(userRepo);

        return ReplyPage.of(dto, mapper.convert(user.getAllBooks()));
    }

    public BookDto info(Book book) {
        return mapper.convert(book, SecurityUtil.getUser(userRepo));
    }


    public void add(BookPersistDto dto) {
        User master = SecurityUtil.getUser(userRepo);

        Book book = new Book(dto.getName(), dto.getLogo(), master);

        repo.save(book);
    }


    public void modify(BookMergeDto dto) {
        Optional<Book> book = repo.findById(dto.getId());
        if (book.isPresent()) {
            List<User> members = userRepo.findAllById(dto.getMemberIds());
            repo.save(book.get().renewByDto(dto, members));
        } else {
            throw new IllegalStateException("illegal book id :%s".formatted(dto.getId()));
        }
    }


    public void delete(Book book) {
        repo.delete(book);
    }

    public String invite(String id) {
        return Base64Util.encode(id);
    }

}
