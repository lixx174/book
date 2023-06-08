package com.book.application.user;

import com.book.application.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService service;


    /**
     * 登录后个人信息
     */
    @GetMapping("profile")
    public Result<UserDto> profile() {
        return Result.ok(service.profile());
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> modify(@RequestBody UserMergeDto dto) {
        service.modify(dto);
        return Result.ok();
    }
}
