package com.book.infra.config.security;

import com.book.BookProperties;
import com.book.domain.Book;
import com.book.domain.User;
import com.book.infra.repo.BookRepository;
import com.book.infra.repo.UserRepository;
import com.book.vender.Base64Util;
import com.book.vender.wx.LoginRequest;
import com.book.vender.wx.WxClient;
import com.book.vender.wx.WxResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author jinx
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WxUserDetailService implements UserDetailsService {

    private final WxClient client;
    private final BookProperties properties;
    private final UserRepository userRepo;
    private final BookRepository bookRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] usernameAndInviteCode = username.split(",");
        if (usernameAndInviteCode.length != 2) {
            throw new AuthenticationServiceException("usernameAndInviteCode not match ,login fail");
        }

        LoginRequest loginRequest = new LoginRequest(
                properties.getWxApplet().getAppId(),
                properties.getWxApplet().getAppSecret(),
                usernameAndInviteCode[0]
        );
        WxResponse response = client.login(loginRequest);

        if (response.success()) {
            String[] openIdAndUnionId = response.kernel().split(",");
            if (openIdAndUnionId.length != 2) {
                throw new AuthenticationServiceException("wx login fail");
            }

            Optional<User> user = userRepo.findByOpenid(openIdAndUnionId[0]);
            Book book = loadBookByInviteCode(usernameAndInviteCode[1]);

            if (user.isEmpty()) {
                user = Optional.of(new User(openIdAndUnionId[0], openIdAndUnionId[1], book));
            } else {
                user.get().addJoinBook(book);
            }
            userRepo.save(user.get());

            return new UserDetail(user.get().getId(), user.get().getMobile());
        } else {
            throw new AuthenticationServiceException(response.errorTips());
        }
    }

    public Book loadBookByInviteCode(String inviteCode) {
        if (StringUtils.hasText(inviteCode)) {
            Long bookId = Base64Util.decodeToLong(inviteCode);
            Optional<Book> book = bookRepo.findById(bookId);
            if (book.isPresent()) {
                return book.get();
            } else {
                log.warn("无法加入账本  非法账本id :{}", bookId);
            }
        }

        return null;
    }
}
