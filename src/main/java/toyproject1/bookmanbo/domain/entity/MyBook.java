package toyproject1.bookmanbo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyproject1.bookmanbo.domain.Period;
import toyproject1.bookmanbo.domain.Status;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MyBook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myBook_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn")
    private Book book;
    @Embedded
    private Period period;
    private int rating;
    @Enumerated(EnumType.STRING)
    private Status status;

}
