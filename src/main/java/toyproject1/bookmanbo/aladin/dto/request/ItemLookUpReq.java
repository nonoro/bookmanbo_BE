package toyproject1.bookmanbo.aladin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import toyproject1.bookmanbo.aladin.dto.ItemSearchQueryType;
import toyproject1.bookmanbo.aladin.dto.SearchTarget;
import toyproject1.bookmanbo.aladin.dto.Sort;

// 알라딘 상품조회 api를 호출하는 클래스입니다. 고유키와 책의 고유키인 isbn13을 이용해 도서의 상세정보를 받습니다.



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemLookUpReq {
//    @Value("${aladin.url.TTBKey}")
    private String ttbKey="ttbkmimi05281551001";
    //ISBN
    private String itemId="";
    private String ItemIdType="ISBN13";
    private String cover="Mid";
    private String output="js";
    private String version= "20131101";

    public ItemLookUpReq(String itemId) {
        this.itemId = itemId;
    }

    public MultiValueMap<String, String> toMultiValueMap(){
        var map=new LinkedMultiValueMap<String,String>();
        map.add("TTBKey",ttbKey);
        map.add("itemId",itemId);
        map.add("itemIdType",ItemIdType);
        map.add("cover",cover);
        map.add("output",output);
        map.add("version",version);

        return map;
    }


}
