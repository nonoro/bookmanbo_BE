package toyproject1.bookmanbo.aladin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject1.bookmanbo.domain.entity.MyBook;
import toyproject1.bookmanbo.dto.response.MyBookResponse;
import toyproject1.bookmanbo.dto.response.Response;
import toyproject1.bookmanbo.repository.MyBookRepository;


@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class MyBookService {

    private final MyBookRepository myBookRepository;

    public Response<MyBookResponse> getMyBook(Long myBookId) {

        MyBook myBook = myBookRepository
                .findById(myBookId)
                .orElseThrow(() -> new IllegalArgumentException("나의 서재에 존재하지 않는 책입니다."));

        return Response.ok(MyBookResponse.from(myBook));
    }
}
