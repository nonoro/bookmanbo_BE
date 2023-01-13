package toyproject1.bookmanbo.aladin.dto;

// ItemListReq의 queryType 필드 도메인


public enum ItemListQueryType {
    //신간전체
    ITEMNEWALL("ItemNewAll"),
    //주목할 만한 신간 리스트
    ITEMNEWSPECIAL("ItemNewSpecial"),
    //편집자 추천 리스트
    ITEMEDITORCHOICE("ItemEditorChoice"),
    //베스트셀러
    BESTSELLER("Bestseller"),
    //블로거 베스트셀러 (국내도서만)
    BLOGBEST("BlogBest");
    private final String queryType;

    ItemListQueryType(String queryType) {
        this.queryType = queryType;
    }

}
