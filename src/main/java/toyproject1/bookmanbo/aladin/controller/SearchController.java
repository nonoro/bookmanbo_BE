package toyproject1.bookmanbo.aladin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyproject1.bookmanbo.aladin.dto.ItemListQueryType;
import toyproject1.bookmanbo.aladin.service.SearchService;
import toyproject1.bookmanbo.myLibrary.dto.BookLookupDto;
import toyproject1.bookmanbo.myLibrary.dto.BookSearchDto;


import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    //검색
    @GetMapping("/")
    public List<BookSearchDto> search(@RequestParam String query){
        return searchService.search(query);
    }

    //베스트 셀러 , 신간 리스트
    @GetMapping ("/{itemListQueryType}")
    public List<BookSearchDto> itemLIstSearch(@PathVariable ItemListQueryType itemListQueryType){
        return searchService.itemListSearch(itemListQueryType);
    }

    //책 상세보기
    @GetMapping("/book-detail")
    public BookLookupDto bookLookup(@RequestParam BookSearchDto bookSearchDto){
        return searchService.lookupBook(bookSearchDto.getIsbn());
    }


}
