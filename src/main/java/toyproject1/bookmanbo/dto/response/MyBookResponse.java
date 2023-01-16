package toyproject1.bookmanbo.dto.response;

import toyproject1.bookmanbo.domain.Status;
import toyproject1.bookmanbo.domain.entity.MyBook;

import java.util.List;

public record MyBookResponse (
        Long myBookId,
        BookResponse book,
        int rating,
        Status status,
        List<NoteResponse> notes

) {

    public static MyBookResponse from(MyBook myBook) {

        return new MyBookResponse(
                myBook.getId(),
                BookResponse.from(myBook.getBook()),
                myBook.getRating(),
                myBook.getStatus(),
                NoteResponse.from(myBook)
        );
    }
}
