package toyproject1.bookmanbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject1.bookmanbo.domain.entity.Note;
import toyproject1.bookmanbo.domain.entity.NoteKeyword;

import java.util.List;

public interface NoteKeywordRepository extends JpaRepository<NoteKeyword, Long> {
    List<NoteKeyword> findAllByNote(Note note);

    void deleteByNote(Note note);
}
