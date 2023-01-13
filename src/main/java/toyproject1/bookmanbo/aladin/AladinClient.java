package toyproject1.bookmanbo.aladin;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import toyproject1.bookmanbo.aladin.dto.request.ItemListReq;
import toyproject1.bookmanbo.aladin.dto.request.ItemLookUpReq;
import toyproject1.bookmanbo.aladin.dto.request.ItemSearchReq;
import toyproject1.bookmanbo.aladin.dto.response.ItemListRes;
import toyproject1.bookmanbo.aladin.dto.response.ItemLookUpRes;
import toyproject1.bookmanbo.aladin.dto.response.ItemSearchRes;

@Component
public class AladinClient {
    @Value("${aladin.url.item-search}")
    private String aladinUrlItemSearch;
    @Value("${aladin.url.item-list}")
    private String aladinUrlItemList;
    @Value("${aladin.url.item-lookup}")
    private String aladinUrlItemLookup;


    //상품검색 api
    public ItemSearchRes itemSearch(ItemSearchReq itemSearchReq){
        var uri= UriComponentsBuilder.fromUriString(aladinUrlItemSearch)
                .queryParams(itemSearchReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity=new HttpEntity<>(headers);


        var responseType=new ParameterizedTypeReference<ItemSearchRes>(){};

        var responseEntity=new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseEntity.getBody();

    }


    //상품 리스트 api
    public ItemListRes itemlist(ItemListReq itemListReq){
        var uri=UriComponentsBuilder.fromUriString(aladinUrlItemList)
                .queryParams(itemListReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity=new HttpEntity<>(headers);

        var responseType=new ParameterizedTypeReference<ItemListRes>(){};
        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseEntity.getBody();
    }

    //상품조회 api
    public ItemLookUpRes itemLookUp(ItemLookUpReq itemLookUpReq){
        var uri =UriComponentsBuilder.fromUriString(aladinUrlItemLookup)
                .queryParams(itemLookUpReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var httpEntity=new HttpEntity<>(headers);

        var responseType=new ParameterizedTypeReference<ItemLookUpRes>(){};

        var responseEntity=new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return responseEntity.getBody();
    }
}
