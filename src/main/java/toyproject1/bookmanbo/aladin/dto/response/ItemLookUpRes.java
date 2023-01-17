package toyproject1.bookmanbo.aladin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemLookUpRes {
    private String version;
    private String title;
    private String link;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    private String query;
    private int searchCategoryId;
    private String searchCategoryName;
    private List<SearchItem> item;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchItem{
        private String isbn13;
        private String title;
        private String author;
        private LocalDate pubDate;
        private String description;
        private String mallType;
        private String cover;
        private String categoryName;
        private String publisher;
        //판매지수
        private int salesPoint;
        private boolean adult;

        //회원 리뷰 평점
        private float customerReviewRank;

        //책 페이지 수
        private  SearchSubInfo subInfo;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SearchSubInfo{
            private int itemPage;
        }



    }





}
