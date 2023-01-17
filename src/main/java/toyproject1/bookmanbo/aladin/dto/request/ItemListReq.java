package toyproject1.bookmanbo.aladin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import toyproject1.bookmanbo.aladin.dto.ItemListQueryType;
import toyproject1.bookmanbo.aladin.dto.ItemSearchQueryType;
import toyproject1.bookmanbo.aladin.dto.SearchTarget;
import toyproject1.bookmanbo.aladin.dto.Sort;

// 알라딘의 상품검색API를 호출하는 클래스입니다. 아래 쿼리타입(ItemListQueryType)을 지정해 결과를 리스트로 받습니다.
//        ItemNewAll : 신간 전체 리스트
//        ItemNewSpecial : 주목할 만한 신간 리스트
//        ItemEditorChoice : 편집자 추천 리스트
//        (카테고리로만 조회 가능 - 국내도서/음반/외서만 지원)
//        Bestseller : 베스트셀러
//        BlogBest : 블로거 베스트셀러 (국내도서만 조회 가능)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemListReq {
//    @Value("${aladin.url.TTBKey}")
    private String ttbKey="ttbkmimi05281551001";
    private ItemListQueryType itemListQueryType = ItemListQueryType.ITEMNEWALL;
    private SearchTarget searchTarget=SearchTarget.BOOK;
    private int start=1;
    private int maxResult=10;
    private Sort sort=Sort.ACCURACY;
    private String cover="Mid";
    private String category;
    private int categoryId=0;
    private String output="js";
//    private String partner;
//    private int includeKey;
    private String inputEncoding="utf-8";
    private String version= "20131101";
//    private String outofStockfilter;


    public ItemListReq(ItemListQueryType itemListQueryType) {
        this.itemListQueryType = itemListQueryType;
    }

    public MultiValueMap<String, String> toMultiValueMap(){
        var map=new LinkedMultiValueMap<String,String>();
        map.add("TTBKey",ttbKey);
        map.add("queryType",String.valueOf(itemListQueryType));
        map.add("start",String.valueOf(start));
        map.add("maxResult",String.valueOf(maxResult));
        map.add("searchTarget",String.valueOf(searchTarget));
        map.add("output",output);
        map.add("version",version);

        return map;
    }

}
