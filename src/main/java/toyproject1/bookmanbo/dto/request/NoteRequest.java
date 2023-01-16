package toyproject1.bookmanbo.dto.request;

import jakarta.validation.constraints.NotBlank;
import toyproject1.bookmanbo.domain.entity.MyBook;
import toyproject1.bookmanbo.domain.entity.Note;

import java.util.List;

public record NoteRequest(

        @NotBlank(message = "제목은 공백일 수 없습니다.")
        String contents,

        List<String> keywords
) {

    public Note toEntity(MyBook myBook) {
        return new Note(contents, myBook);
    }
}
