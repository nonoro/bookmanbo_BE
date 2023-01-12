package toyproject1.bookmanbo.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject1.bookmanbo.domain.entity.Keyword;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[Repository] KeywordRepository 테스트")
@DataJpaTest
class KeywordRepositoryTest {

    @Autowired
    private KeywordRepository keywordRepository;

    @DisplayName("String 으로 받아온 여러개의 키워드를 저장한다.")
    @Transactional
    @Test
    void saveKeywords() {
        Set<String> keywords = Set.of("test1", "test2", "test3");

        for (String keyword : keywords) {
            keywordRepository.save(new Keyword(keyword));
        }

        List<Keyword> findKeywords = keywordRepository.findAll();

        assertThat(findKeywords.size()).isEqualTo(keywords.size());
    }


}
