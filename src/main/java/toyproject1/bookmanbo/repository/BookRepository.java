package toyproject1.bookmanbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject1.bookmanbo.domain.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
