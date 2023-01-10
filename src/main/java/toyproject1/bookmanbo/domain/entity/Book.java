package toyproject1.bookmanbo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;
    private String title;
    private String author;
    private LocalDateTime pubDate;
    private String description;
    private String coverImage;
    private String category;
    private String publisher;
    private int itemPage;

    @OneToMany(mappedBy = "book")
    private List<MyBook> myBooks = new ArrayList<>();

}
