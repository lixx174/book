package com.book.application.user;

import com.book.domain.User;
import com.book.infra.mapper.UserMapper;
import com.book.infra.repo.UserRepository;
import com.book.vender.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author jinx
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;


    public void modify(UserMergeDto dto) {
        User user = SecurityUtil.getUser(repo);
        repo.save(user.renewByDto(dto));
    }

    public UserDto profile() {
        return mapper.convert(SecurityUtil.getUser(repo));
    }
}
