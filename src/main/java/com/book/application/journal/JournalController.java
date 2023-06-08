package com.book.application.journal;

import com.book.application.ReplyPage;
import com.book.application.Result;
import com.book.domain.Journal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流水
 *
 * @author jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("journal")
public class JournalController {

    private final JournalService service;

    /**
     * 分页
     */
    @GetMapping
    public Result<ReplyPage<JournalDto>> page(JournalPageDto dto) {
        return Result.ok(service.page(dto));
    }

    /**
     * 详情
     *
     * @ignoreParams journal
     */
    @GetMapping("{id}")
    public Result<JournalDto> info(@PathVariable("id") Journal journal) {
        return Result.ok(service.info(journal));
    }

    /**
     * 新增
     */
    @PostMapping
    public Result<Void> add(@RequestBody JournalPersistDto dto) {
        service.add(dto);
        return Result.ok();
    }

    /**
     * 删除
     *
     * @ignoreParams journal
     */
    @DeleteMapping("{id}")
    public Result<Void> delete(@PathVariable("id") Journal journal) {
        service.delete(journal);
        return Result.ok();
    }
}
