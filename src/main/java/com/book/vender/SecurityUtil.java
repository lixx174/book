package com.book.vender;

import com.book.domain.User;
import com.book.infra.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * @author jinx
 */
public class SecurityUtil {

    public static Long getUserId() {
        return (Long) getRequest().getAttribute("userId");
    }


    public static User getUser(UserRepository repo) {
        Assert.notNull(repo, "repo can't be null");
        Optional<User> user = repo.findById(getUserId());

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalStateException("illegal token that user isn't exist");
        }
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }
}
