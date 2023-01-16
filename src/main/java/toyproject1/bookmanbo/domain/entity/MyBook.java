package toyproject1.bookmanbo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject1.bookmanbo.domain.Period;
import toyproject1.bookmanbo.domain.Status;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MyBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_book_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    @Embedded
    private Period period;
    private int rating;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "myBook")
    private List<Note> notes = new ArrayList<>();

    public MyBook(Account account, Book book, Status status) {
        this.account = account;
        this.book = book;
        this.status = status;
    }
}
