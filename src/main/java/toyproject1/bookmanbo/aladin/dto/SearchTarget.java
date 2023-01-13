package toyproject1.bookmanbo.aladin.dto;

public enum SearchTarget {
    BOOK("Book"),
//    외국도서
    FOREIGN("Foreign"),
    MUSIC("Music"),
    DVD("DVD"),
//    중고
    USED("Used"),
    EBOOK("eBook"),
//    위의 모든 타겟(몰)
    ALL("All");

    private final String searchtarget;

    SearchTarget(String searchtarget) {
        this.searchtarget = searchtarget;
    }
}
