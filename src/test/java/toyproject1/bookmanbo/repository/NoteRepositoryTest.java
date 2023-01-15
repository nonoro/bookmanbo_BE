package toyproject1.bookmanbo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import toyproject1.bookmanbo.config.JpaConfiguration;
import toyproject1.bookmanbo.domain.Status;
import toyproject1.bookmanbo.domain.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("[Repository] NoteRepository 테스트")
@Import(JpaConfiguration.class)
@DataJpaTest
class NoteRepositoryTest {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MyBookRepository myBookRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private NoteKeywordRepository noteKeywordRepository;

    @DisplayName("Entity 를 저장할 수 있다.[키워드가 없는 경우]")
    @Transactional
    @Test
    void saveNoteTest() {
        Account account = new Account("test", "test", "test");
        Book book = new Book("1231", "test", "test", LocalDateTime.now(), "test", "test", "test", "test", 100);
        MyBook myBook = new MyBook(account, book, Status.WISH);
        Note note = new Note("정말 재미있는 책이다", myBook);

        accountRepository.save(account);
        bookRepository.save(book);
        myBookRepository.save(myBook);
        Note result = noteRepository.save(note);

        assertThat(result.getId()).isNotNull();
    }

    @DisplayName("Entity 를 저장할 수 있다.[키워드가 있는 경우]")
    @Transactional
    @Test
    void saveNoteAndKeywordTest() {
        Set<String> keywords = Set.of("test1", "test2");
        Account account = new Account("test", "test", "test");
        Book book = new Book("1231", "test", "test", LocalDateTime.now(), "test", "test", "test", "test", 100);
        MyBook myBook = new MyBook(account, book, Status.WISH);
        Note note = new Note("정말 재미있는 책이다", myBook);

        accountRepository.save(account);
        bookRepository.save(book);
        myBookRepository.save(myBook);
        noteRepository.save(note);
        List<Keyword> findKeywords = keywordRepository.findAll();
        for (String keyword : keywords) {
            if (!findKeywords.contains(keyword)) {
                keywordRepository.save(new Keyword(keyword));
            }
        }

        List<Keyword> keywordList = keywordRepository.findAllByContentsIn(keywords);
        for (Keyword keyword : keywordList) {
            noteKeywordRepository.save(new NoteKeyword(note, keyword));
        }

        List<NoteKeyword> noteKeywords = noteKeywordRepository.findAllByNote(note);

        assertThat(noteKeywords.size()).isEqualTo(keywords.size());
    }

    @DisplayName("note 의 내용을 수정할 수 있다.")
    @Transactional
    @Test
    void updateNoteTest() {
        String updateContents = "너무 졸리다";
        Account account = new Account("test", "test", "test");
        Book book = new Book("1231", "test", "test", LocalDateTime.now(), "test", "test", "test", "test", 100);
        MyBook myBook = new MyBook(account, book, Status.WISH);
        Note note = new Note("정말 재미있는 책이다", myBook);

        accountRepository.save(account);
        bookRepository.save(book);
        myBookRepository.save(myBook);
        Note save = noteRepository.save(note);

        save.updateContents(updateContents);

        assertThat(note.getContents()).isEqualTo(updateContents);
    }

    @DisplayName("note 의 keyword 를 수정할 수 있다.")
    @Transactional
    @Test
    void updateKeyword() {
        List<String> defaultKeywords = List.of("test1", "test2", "test4", "test7", "test10");
        Account account = new Account("test", "test", "test");
        Book book = new Book("1231", "test", "test", LocalDateTime.now(), "test", "test", "test", "test", 100);
        MyBook myBook = new MyBook(account, book, Status.WISH);
        Note note = new Note("정말 재미있는 책이다", myBook);

        accountRepository.save(account);
        bookRepository.save(book);
        myBookRepository.save(myBook);
        noteRepository.save(note);

        List<Keyword> findKeywords = keywordRepository.findAll();
        if (findKeywords.isEmpty()) {
            defaultKeywords.forEach(k -> keywordRepository.save(new Keyword(k)));
        } else {
            for (Keyword findKeyword : findKeywords) {
                for (int j = 0; j < defaultKeywords.size(); j++) {
                    if (findKeyword.getContents().equals(defaultKeywords.get(j))) {
                        defaultKeywords.remove(j);
                    }
                }
            }

            List<Keyword> keywords = Keyword.from(defaultKeywords);
            keywordRepository.saveAll(keywords);
        }

        List<Keyword> keywordList = keywordRepository.findAllByContentsIn(defaultKeywords);

        for (Keyword keyword : keywordList) {
            NoteKeyword noteKeyword = noteKeywordRepository.save(new NoteKeyword(note, keyword));
            keyword.addNoteKeyword(noteKeyword);
            note.addKeywords(noteKeyword);
        }


        // 키워드를 수정한다

        List<String> newKeywordList = new ArrayList<>();

        newKeywordList.add("test1");
        newKeywordList.add("test4");
        newKeywordList.add("test5");
        newKeywordList.add("test6");

        List<Keyword> newKeywords = keywordRepository.findAll();

        for (Keyword newKeyword : newKeywords) {
            for (int j = 0; j < newKeywordList.size(); j++) {
                if (newKeyword.getContents().equals(newKeywordList.get(j))) {
                    newKeywordList.remove(j);
                }
            }
        }

        List<Keyword> updateKeywords = Keyword.from(newKeywordList);
        keywordRepository.saveAll(updateKeywords);

        List<Keyword> updateFindKeywords = keywordRepository.findAll();
        List<NoteKeyword> findNoteKeywords = noteKeywordRepository.findAll();

        for (NoteKeyword noteKeyword : findNoteKeywords) {
            if (!updateFindKeywords.contains(noteKeyword.getKeyword())) {
                keywordRepository.delete(noteKeyword.getKeyword());
            }
        }

        noteKeywordRepository.deleteByNote(note);

        List<Keyword> updateKeyword = keywordRepository.findAllByContentsIn(newKeywordList);

        for (Keyword keyword : updateKeyword) {
            NoteKeyword noteKeyword = noteKeywordRepository.save(new NoteKeyword(note, keyword));
            keyword.addNoteKeyword(noteKeyword);
        }

        List<NoteKeyword> result = noteKeywordRepository.findAllByNote(note);

        assertAll(() -> {
            assertThat(result.size()).isEqualTo(newKeywordList.size());
        });
    }
}
