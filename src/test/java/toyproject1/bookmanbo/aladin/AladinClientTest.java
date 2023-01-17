package toyproject1.bookmanbo.aladin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toyproject1.bookmanbo.aladin.dto.ItemListQueryType;
import toyproject1.bookmanbo.aladin.dto.request.ItemListReq;
import toyproject1.bookmanbo.aladin.dto.request.ItemLookUpReq;
import toyproject1.bookmanbo.aladin.dto.request.ItemSearchReq;

@SpringBootTest
public class AladinClientTest {

    @Autowired
    private AladinClient aladinClient;

    @Test
    public void itemSearchTest(){
        var search=new ItemSearchReq();
        search.setQuery("자기만의 방");
       var result= aladinClient.itemSearch(search);
        System.out.println(result);
        System.out.println(result.getPubDate());

    }
    @Test
    public void itemListTest(){
        var search =new ItemListReq(ItemListQueryType.ITEMNEWSPECIAL);
        var result=aladinClient.itemlist(search);
        System.out.println(result);
    }

    @Test
    public void itemLookUpTest(){
        var search=new ItemLookUpReq();
        search.setItemId("9791166813580");
        var result=aladinClient.itemLookUp(search);
        System.out.println(result);
    }


}
