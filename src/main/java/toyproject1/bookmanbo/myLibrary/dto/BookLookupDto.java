package toyproject1.bookmanbo.myLibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import toyproject1.bookmanbo.aladin.dto.response.ItemLookUpRes;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookLookupDto {
    private String isbn;
    private String title;
    private String author;
    private LocalDate pubDate;
    private String description;
    private String coverImage;
    private String category;
    private String publisher;
    //판매지수
    private int salesPoint;
    private int itemPage;
    private boolean adult;
    //회원 리뷰 평점
    private float customerReviewRank;

}
