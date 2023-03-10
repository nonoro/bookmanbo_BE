package toyproject1.bookmanbo.note.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toyproject1.bookmanbo.note.service.MyBookService;
import toyproject1.bookmanbo.note.dto.response.MyBookResponse;
import toyproject1.bookmanbo.note.dto.response.Response;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/myBooks")
@RestController
public class MyBookController {

    private final MyBookService myBookService;

    @GetMapping("/book/{myBookId}")
    public Response<MyBookResponse> getMyBook(@PathVariable Long myBookId) {

        return myBookService.getMyBook(myBookId);
    }
}
