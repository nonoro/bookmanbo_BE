package toyproject1.bookmanbo.note.dto.response;

import toyproject1.bookmanbo.domain.entity.MyBook;
import toyproject1.bookmanbo.domain.entity.Note;
import toyproject1.bookmanbo.domain.entity.NoteKeyword;

import java.util.ArrayList;
import java.util.List;

public record NoteResponse(
        Long id,
        String contents,
        List<String> keywords
) {
    public static List<NoteResponse> from(MyBook myBook) {
        List<NoteResponse> noteResponses = new ArrayList<>();
        for (Note entity : myBook.getNotes()) {
            noteResponses.add(new NoteResponse(
                    entity.getId(),
                    entity.getContents(),
                    entity.getKeywords()
                            .stream()
                            .map(NoteKeyword::keywordName)
                            .toList()));
        }
        return noteResponses;
    }

    public static NoteResponse from(Note note, List<NoteKeyword> noteKeywords) {
        return new NoteResponse(
                note.getId(),
                note.getContents(),
                noteKeywords.stream()
                        .map(nk -> nk.getKeyword().getContents())
                        .toList()
        );
    }
}
