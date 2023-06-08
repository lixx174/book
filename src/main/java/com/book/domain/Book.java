package com.book.domain;

import com.book.application.book.BookMergeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jinx
 * @ignore
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_book")
public class Book implements MergeAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String logo;
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "master_id")
    private User master;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_book_user",
            joinColumns = {
                    @JoinColumn(name = "book_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    private List<User> members;
    private LocalDateTime createTime = LocalDateTime.now();
    public Book(String name, String logo, User master) {
        this.name = name;
        this.logo = logo;
        this.master = master;
    }

    public Book renewByDto(BookMergeDto dto, List<User> members) {
        if (couldMerge(dto.getName())) {
            this.name = dto.getName();
        }
        if (couldMerge(dto.getLogo())) {
            this.logo = dto.getLogo();
        }

        this.members = members;

        if (dto.isDefaultSelector()) {
            master.setDefaultBook(this);
        }
        return this;
    }
}
