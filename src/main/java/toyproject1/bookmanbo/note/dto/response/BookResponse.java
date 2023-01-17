package toyproject1.bookmanbo.note.dto.response;

import toyproject1.bookmanbo.domain.entity.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookResponse(
        Long bookId,
        String title,
        String author,
        String publisher,
        String description,
        String category,
        String coverImage,
        String isbn,
        int itemPage,
        LocalDate pubDate

) {

    public static BookResponse from(Book book) {

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getDescription(),
                book.getCategory(),
                book.getCoverImage(),
                book.getIsbn(),
                book.getItemPage(),
                book.getPubDate()
        );
    }
}
