package toyproject1.bookmanbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject1.bookmanbo.domain.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
