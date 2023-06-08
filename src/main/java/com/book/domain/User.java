package com.book.domain;

import com.book.application.user.UserMergeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jinx
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_user")
public class User implements MergeAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String openid;
    private String unionid;
    private String mobile;
    private String nickname = "微信用户";
    private String avatar = "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
    private LocalDateTime createTime = LocalDateTime.now();
    @OneToOne(targetEntity = Book.class)
    @JoinColumn(name = "default_book_id")
    private Book defaultBook;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_book_user",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id")
            }
    )
    private Set<Book> joinBooks;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "master_id")
    private Set<Book> holdBooks;
    public User(String openid, String unionid, Book book) {
        this(StringUtils.hasText(openid) ? openid : null,
                StringUtils.hasText(unionid) ? unionid : null,
                null,
                book
        );
    }
    public User(String openid, String unionid, String mobile, Book book) {
        this.openid = openid;
        this.unionid = unionid;
        this.mobile = mobile;
        addJoinBook(book);
    }

    public void addJoinBook(Book book) {
        if (joinBooks == null) {
            joinBooks = new HashSet<>();
        }
        if (book != null) {
            joinBooks.add(book);
        }
    }

    public User renewByDto(UserMergeDto dto) {
        if (couldMerge(dto.getMobile())) {
            mobile = dto.getMobile();
        }
        if (couldMerge(dto.getNickname())) {
            nickname = dto.getNickname();
        }
        if (couldMerge(dto.getAvatar())) {
            avatar = dto.getAvatar();
        }

        return this;
    }


    /**
     * 获取所有账本 包括：创建 参与
     */
    public Set<Book> getAllBooks() {
        if (holdBooks == null) {
            return joinBooks;
        }
        if (joinBooks == null) {
            return holdBooks;
        }

        return new HashSet<>() {{
            addAll(holdBooks);
            addAll(joinBooks);
        }};
    }
}
