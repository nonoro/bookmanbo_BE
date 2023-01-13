package toyproject1.bookmanbo.aladin.dto;

// ItemSearchReq의 queryType 필드 도메인

public enum ItemSearchQueryType {
    //제목+저자
    KEYWORD("Keyword"),
    TITLE("Title"),
    AUTHOR("Author"),
    PUBLISHER("Publisher");
    private final String queryType;

    ItemSearchQueryType(String queryType) {
        this.queryType = queryType;
    }

}
