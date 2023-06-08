package com.book.infra.mapper;

import com.book.application.user.UserDto;
import com.book.domain.User;
import org.mapstruct.Mapper;

/**
 * @author jinx
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto convert(User user);
}
