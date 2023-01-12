package toyproject1.bookmanbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject1.bookmanbo.domain.entity.Keyword;

import java.util.Collection;
import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    List<Keyword> findAllByContentsIn(Collection<String> keywords);
}
