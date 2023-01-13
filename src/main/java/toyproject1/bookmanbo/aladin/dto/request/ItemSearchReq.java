package toyproject1.bookmanbo.aladin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import toyproject1.bookmanbo.aladin.dto.ItemSearchQueryType;
import toyproject1.bookmanbo.aladin.dto.SearchTarget;
import toyproject1.bookmanbo.aladin.dto.Sort;


// 알라딘 상품 검색 API를 호출하는 클래스입니다. 고유키와 검색어를 이용해 검색 결과를 받습니다

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearchReq {
//    @Value("${aladin.url.TTBKey}")
    private String ttbKey="ttbkmimi05281551001";
    private String query="자기 신뢰";
    private ItemSearchQueryType itemSearchQueryType = ItemSearchQueryType.KEYWORD;
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

    public MultiValueMap<String, String> toMultiValueMap(){
        var map=new LinkedMultiValueMap<String,String>();
        map.add("TTBKey",ttbKey);
        map.add("query",query);
        map.add("queryType",String.valueOf(itemSearchQueryType));
        map.add("start",String.valueOf(start));
        map.add("maxResult",String.valueOf(maxResult));
        map.add("searchTarget",String.valueOf(searchTarget));
        map.add("output",output);
        map.add("version",version);

        return map;
    }

}
