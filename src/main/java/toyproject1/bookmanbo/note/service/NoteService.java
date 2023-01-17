package toyproject1.bookmanbo.note.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject1.bookmanbo.domain.entity.Keyword;
import toyproject1.bookmanbo.domain.entity.MyBook;
import toyproject1.bookmanbo.domain.entity.Note;
import toyproject1.bookmanbo.domain.entity.NoteKeyword;
import toyproject1.bookmanbo.note.dto.request.NoteRequest;
import toyproject1.bookmanbo.note.dto.response.NoteResponse;
import toyproject1.bookmanbo.note.dto.response.Response;
import toyproject1.bookmanbo.repository.KeywordRepository;
import toyproject1.bookmanbo.repository.MyBookRepository;
import toyproject1.bookmanbo.repository.NoteKeywordRepository;
import toyproject1.bookmanbo.repository.NoteRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class NoteService {

    private final MyBookRepository myBookRepository;

    private final NoteRepository noteRepository;

    private final KeywordRepository keywordRepository;

    private final NoteKeywordRepository noteKeywordRepository;


    @Transactional
    public Response<List<NoteResponse>> save(NoteRequest request, Long myBookId) {
        MyBook myBook = myBookRepository.findById(myBookId).get();
        Note note = request.toEntity(myBook);

        noteRepository.save(note);
        saveKeyword(request);
        saveNoteKeyword(request, note);

        return Response.ok(NoteResponse.from(myBook));
    }

    private void saveNoteKeyword(NoteRequest request, Note note) {
        if (checkKeywordOfNullOrEmpty(request)) return;
        List<Keyword> keywordList = keywordRepository.findAllByContentsIn(request.keywords());
        saveNoteKeyword(note, keywordList);
    }

    private void saveNoteKeyword(Note note, List<Keyword> keywordList) {
        for (Keyword keyword : keywordList) {
            NoteKeyword noteKeyword = noteKeywordRepository.save(new NoteKeyword(note, keyword));
            keyword.addNoteKeyword(noteKeyword);
            note.addKeywords(noteKeyword);
        }
    }

    private void saveKeyword(NoteRequest request) {
        if (checkKeywordOfNullOrEmpty(request)) return;
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

    @Transactional
    public Response<NoteResponse> update(NoteRequest request, Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new NoSuchElementException("메모가 존재하지 않습니다."));

        updateNote(request, note);
        saveUpdatedKeyword(request, note);
        deleteUnusedKeyword();

        List<NoteKeyword> noteKeywords = noteKeywordRepository.findAllByNote(note);

        //TODO : 업데이트된 값 리턴만 추가해서 커밋하기
        return Response.ok(NoteResponse.from(note, noteKeywords));
    }

    private void updateNote(NoteRequest request, Note note) {
        note.updateContents(request.contents());
        saveKeyword(request);
        noteKeywordRepository.deleteByNote(note);
    }

    private void deleteUnusedKeyword() {
        List<NoteKeyword> noteKeywords = noteKeywordRepository.findAll();
        List<Keyword> keywords = keywordRepository.findAll();

        for (NoteKeyword noteKeyword : noteKeywords) {
            for (int j = 0; j < keywords.size(); j++) {
                if (noteKeyword.getKeyword().equals(keywords.get(j))) {
                    keywords.remove(keywords.get(j));
                }
            }
        }
        keywordRepository.deleteAll(keywords);
    }

    private void saveUpdatedKeyword(NoteRequest request, Note note) {
        if (checkKeywordOfNullOrEmpty(request)) return;
        List<Keyword> keywordList = keywordRepository.findAllByContentsIn(request.keywords());
        saveNoteKeyword(note, keywordList);
    }

    private static boolean checkKeywordOfNullOrEmpty(NoteRequest request) {
        request.keywords().removeAll(Arrays.asList("", null));
        if (request.keywords() == null || request.keywords().isEmpty()) {
            return true;
        }
        return false;
    }
}
