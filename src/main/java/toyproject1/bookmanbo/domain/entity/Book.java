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
    @Column(name = "book_id")
    private Long id;
    private String isbn;
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

    public Book(String isbn, String title, String author, LocalDateTime pubDate, String description, String coverImage, String category, String publisher, int itemPage) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pubDate = pubDate;
        this.description = description;
        this.coverImage = coverImage;
        this.category = category;
        this.publisher = publisher;
        this.itemPage = itemPage;
    }
}
