package com.book.application.book;

import com.book.application.ReplyPage;
import com.book.application.Result;
import com.book.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账本
 *
 * @author jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {

    private final BookService service;

    /**
     * 分页
     */
    @GetMapping
    public Result<ReplyPage<BookDto>> page(BookPageDto dto) {
        return Result.ok(service.page(dto));
    }

    /**
     * 详情
     *
     * @ignoreParams book
     */
    @GetMapping("{id}")
    public Result<BookDto> info(@PathVariable("id") Book book) {
        return Result.ok(service.info(book));
    }

    /**
     * 新增
     */
    @PostMapping
    public Result<Void> add(@RequestBody BookPersistDto dto) {
        service.add(dto);
        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> modify(@RequestBody BookMergeDto dto) {
        service.modify(dto);
        return Result.ok();
    }

    /**
     * 删除
     *
     * @ignoreParams book
     */
    @DeleteMapping("{id}")
    public Result<Void> delete(@PathVariable("id") Book book) {
        service.delete(book);
        return Result.ok();
    }


    /**
     * 邀请成员
     *
     * @param id 账本id
     * @apiNote 通过该接口获取邀请码 分享出去的链接在登录时通过url链接带上该邀请码 eg:inviteCode=xx
     */
    @GetMapping("invite/{id}")
    public Result<String> invite(@PathVariable("id") String id) {
        return Result.ok(service.invite(id));
    }
}
