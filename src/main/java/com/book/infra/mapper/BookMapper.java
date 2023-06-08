package com.book.infra.mapper;

import com.book.application.book.BookDto;
import com.book.domain.Book;
import com.book.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

/**
 * @author jinx
 */
@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mappings({
            @Mapping(target = "defaultSelector", ignore = true),
    })
    BookDto convert(Book book);

    default BookDto convert(Book book, User user) {
        BookDto dto = convert(book);
        if (user.getDefaultBook() != null) {
            dto.setDefaultSelector(book.getId().equals(user.getDefaultBook().getId()));
        }
        return dto;
    }

    List<BookDto> convert(Set<Book> books);
}
