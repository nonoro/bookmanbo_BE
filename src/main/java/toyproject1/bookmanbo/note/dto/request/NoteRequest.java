package toyproject1.bookmanbo.note.dto.request;

import jakarta.validation.constraints.NotBlank;
import toyproject1.bookmanbo.domain.entity.MyBook;
import toyproject1.bookmanbo.domain.entity.Note;

import java.util.Set;

public record NoteRequest(

        @NotBlank(message = "제목은 공백일 수 없습니다.")
        String contents,

        Set<String> keywords
) {

    public Note toEntity(MyBook myBook) {
        return new Note(contents, myBook);
    }
}
