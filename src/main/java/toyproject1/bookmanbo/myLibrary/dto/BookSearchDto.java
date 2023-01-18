package toyproject1.bookmanbo.myLibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookSearchDto {
    private String isbn;
    private String coverImage;
    private String title;
    private String author;
    private String category;
    private String publisher;
}
