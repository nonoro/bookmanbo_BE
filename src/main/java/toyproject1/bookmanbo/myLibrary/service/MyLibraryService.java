package toyproject1.bookmanbo.myLibrary.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject1.bookmanbo.domain.Status;
import toyproject1.bookmanbo.domain.entity.Account;
import toyproject1.bookmanbo.domain.entity.Book;
import toyproject1.bookmanbo.domain.entity.MyBook;
import toyproject1.bookmanbo.myLibrary.dto.BookLookupDto;
import toyproject1.bookmanbo.myLibrary.dto.MyBookDto;
import toyproject1.bookmanbo.repository.MyBookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyLibraryService {
    private final MyBookRepository myBookRepository;
    public void addMyLibrary(BookLookupDto bookLookupDto) {
        myBookRepository.save(toEntity(bookLookupDto, Status.ING));
    }
    public List<MyBookDto> findAll(){
        return myBookRepository.findAll()
                .stream()
                .map(it -> toDto(it))
                .collect(Collectors.toList());

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

    private MyBook toEntity(BookLookupDto bookLookupDto, Status status) {
        return new MyBook(new Account("", "", ""),
                toEntity(bookLookupDto),
                status);
    }
    private MyBookDto toDto(MyBook myBook){
        var myBookDto=new MyBookDto();
        myBookDto.setBook(myBook.getBook());
        myBookDto.setId(myBook.getId());
        myBookDto.setAccount(myBook.getAccount());
        myBookDto.setPeriod(myBook.getPeriod());
        myBookDto.setRating(myBook.getRating());
        myBookDto.setStatus(myBook.getStatus());
        myBookDto.setNotes(myBook.getNotes());

        return myBookDto;
    }


}
