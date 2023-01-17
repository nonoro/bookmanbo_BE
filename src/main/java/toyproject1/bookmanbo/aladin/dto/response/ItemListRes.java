package toyproject1.bookmanbo.aladin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemListRes {
//    private String version;
    private String title;
    private String link;
//    private LocalDateTime pubDate;
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
        private String title;
        private String link;
        private String author;
        private String pubDate;
        private String description;
        private String isbn;
        private String isbn13;
        private int pricesales;
        private int pricestandard;
        private String mallType;
        private String stockstatus;
        private int mileage;
        private String cover;
        private String publisher;
        private int salesPoint;
        private boolean adult;
        private String categoryName;
        private  List<SearchSubInfo> subinfo;

        @Data
        @NoArgsConstructor
        public static class SearchSubInfo{

        }



    }





}
