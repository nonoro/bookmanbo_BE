package toyproject1.bookmanbo.aladin.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject1.bookmanbo.aladin.AladinClient;
import toyproject1.bookmanbo.aladin.dto.ItemListQueryType;
import toyproject1.bookmanbo.aladin.dto.request.ItemListReq;
import toyproject1.bookmanbo.aladin.dto.request.ItemLookUpReq;
import toyproject1.bookmanbo.aladin.dto.request.ItemSearchReq;
import toyproject1.bookmanbo.domain.entity.Book;
import toyproject1.bookmanbo.myLibrary.dto.BookLookupDto;
import toyproject1.bookmanbo.myLibrary.dto.BookSearchDto;
import toyproject1.bookmanbo.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final AladinClient aladinClient;
    private final BookRepository bookRepository;


    //검색
    public List<BookSearchDto> search(String query) {
        //상품 검색
        var itemSearchReq = new ItemSearchReq(query);

        var itemSearchRes = aladinClient.itemSearch(itemSearchReq);

        if (itemSearchRes.getTotalResults() > 0) {
            List<BookSearchDto> bookSearchDtoList = new ArrayList<>();

            itemSearchRes.getItem().forEach(book -> {
                var bookSearchDto = new BookSearchDto();
                bookSearchDto.setIsbn(book.getIsbn13());
                bookSearchDto.setTitle(book.getTitle());
                bookSearchDto.setAuthor(book.getAuthor());
                bookSearchDto.setCoverImage(book.getCover());
                bookSearchDto.setCategory(book.getCategoryName());
                bookSearchDto.setPublisher(book.getPublisher());
                bookSearchDtoList.add(bookSearchDto);
            });
            return bookSearchDtoList;

        }
        return new ArrayList<>();
    }

    //책 상세보기
    public BookLookupDto lookupBook(String isbn) {
        var bookItem = aladinClient.itemLookUp(new ItemLookUpReq(isbn)).getItem().stream().findFirst().get();
        var bookLookupDto = new BookLookupDto();
        bookLookupDto.setIsbn(bookItem.getIsbn13());
        bookLookupDto.setTitle(bookItem.getTitle());
        bookLookupDto.setAuthor(bookItem.getAuthor());
        bookLookupDto.setPubDate(bookItem.getPubDate());
        bookLookupDto.setDescription(bookItem.getDescription());
        bookLookupDto.setCoverImage(bookItem.getCover());
        bookLookupDto.setCategory(bookItem.getCategoryName());
        bookLookupDto.setPublisher(bookItem.getPublisher());
        bookLookupDto.setItemPage(bookItem.getSubInfo().getItemPage());
        bookLookupDto.setSalesPoint(bookItem.getSalesPoint());
        bookLookupDto.setCustomerReviewRank(bookItem.getCustomerReviewRank());

        bookRepository.save(toEntity(bookLookupDto));

        return bookLookupDto;
    }

    //신간, 베스트 리스트
    public List<BookSearchDto> itemListSearch(ItemListQueryType itemListQueryType) {
        var itemListReq = new ItemListReq(itemListQueryType);
        var itemListRes = aladinClient.itemlist(itemListReq);

        if (itemListRes.getTotalResults() > 0) {
            List<BookSearchDto> bookSearchDtoList = new ArrayList<>();

            itemListRes.getItem().forEach(book -> {
                var myLibraryDto = new BookSearchDto();
                myLibraryDto.setIsbn(book.getIsbn13());
                myLibraryDto.setTitle(book.getTitle());
                myLibraryDto.setAuthor(book.getAuthor());
                myLibraryDto.setCoverImage(book.getCover());
                myLibraryDto.setCategory(book.getCategoryName());
                myLibraryDto.setPublisher(book.getPublisher());
                bookSearchDtoList.add(myLibraryDto);
            });
            return bookSearchDtoList;
        }
        return new ArrayList<>();
    }



    public Book toEntity(BookLookupDto bookLookupDto) {
        return new Book(bookLookupDto.getIsbn(),
                bookLookupDto.getTitle(),
                bookLookupDto.getAuthor(),
                bookLookupDto.getPubDate(),
                bookLookupDto.getDescription(),
                bookLookupDto.getCoverImage(),
                bookLookupDto.getCategory(),
                bookLookupDto.getPublisher(),
                bookLookupDto.getItemPage());
    }



}
