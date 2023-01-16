package toyproject1.bookmanbo.aladin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject1.bookmanbo.domain.entity.Keyword;
import toyproject1.bookmanbo.domain.entity.MyBook;
import toyproject1.bookmanbo.domain.entity.Note;
import toyproject1.bookmanbo.domain.entity.NoteKeyword;
import toyproject1.bookmanbo.dto.request.NoteRequest;
import toyproject1.bookmanbo.dto.response.NoteResponse;
import toyproject1.bookmanbo.dto.response.Response;
import toyproject1.bookmanbo.repository.KeywordRepository;
import toyproject1.bookmanbo.repository.MyBookRepository;
import toyproject1.bookmanbo.repository.NoteKeywordRepository;
import toyproject1.bookmanbo.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NoteService {

    private final MyBookRepository myBookRepository;

    private final NoteRepository noteRepository;

    private final KeywordRepository keywordRepository;

    private final NoteKeywordRepository noteKeywordRepository;


    public Response<List<NoteResponse>> save(NoteRequest request, Long myBookId) {

        MyBook myBook = myBookRepository.findById(myBookId).get();
        Note note = request.toEntity(myBook);

        noteRepository.save(note);
        saveKeyword(request);
        saveNoteKeyword(request, note);

        return Response.ok(NoteResponse.from(myBook));
    }

    private void saveNoteKeyword(NoteRequest request, Note note) {
        List<Keyword> keywordList = keywordRepository.findAllByContentsIn(request.keywords());

        for (Keyword keyword : keywordList) {
            NoteKeyword noteKeyword = noteKeywordRepository.save(new NoteKeyword(note, keyword));
            keyword.addNoteKeyword(noteKeyword);
            note.addKeywords(noteKeyword);
        }
    }

    private void saveKeyword(NoteRequest request) {
        List<Keyword> findKeywords = keywordRepository.findAll();
        if (findKeywords.isEmpty()) {
            request.keywords().forEach(k -> keywordRepository.save(new Keyword(k)));
        } else {
            List<String> saveKeywords = new ArrayList<>(request.keywords());

            for (Keyword findKeyword : findKeywords) {
                for (int j = 0; j < saveKeywords.size(); j++) {
                    if (findKeyword.getContents().equals(saveKeywords.get(j))) {
                        saveKeywords.remove(j);
                    }
                }
            }
            List<Keyword> keywords = Keyword.from(saveKeywords);
            keywordRepository.saveAll(keywords);
        }
    }
}
