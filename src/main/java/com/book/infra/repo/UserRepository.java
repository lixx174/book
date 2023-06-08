package com.book.infra.repo;

import com.book.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author jinx
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOpenid(String openid);

}
