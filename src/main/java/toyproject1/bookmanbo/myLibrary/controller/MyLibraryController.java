package toyproject1.bookmanbo.myLibrary.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyproject1.bookmanbo.myLibrary.dto.BookLookupDto;
import toyproject1.bookmanbo.myLibrary.dto.MyBookDto;
import toyproject1.bookmanbo.myLibrary.service.MyLibraryService;

import java.util.List;

@RestController
@RequestMapping("/my-library")
@RequiredArgsConstructor
public class MyLibraryController {
    private final MyLibraryService myLibraryService;

    //나의 서재에 책 추가
    @PostMapping("/add")
    public void addMyLibrary(@RequestBody BookLookupDto bookLookupDto){
        myLibraryService.addMyLibrary(bookLookupDto);
    }


    //나의 서재 전체 받기
   @GetMapping("/all")
    public List<MyBookDto> findAll(){
        return myLibraryService.findAll();
    }

}
