package toyproject1.bookmanbo.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Note 도메인 테스트")
@DataJpaTest
class NoteTest {

    @DisplayName("내용으로 생성할 수 있다.")
    @Test
    void createNoteTest() {
        assertDoesNotThrow(() -> new Note("contents"));
    }

    @DisplayName("내용이 null 이거나 빈 값인 경우 예외가 발생한다.")
    @NullAndEmptySource
    @ParameterizedTest(name = "입력값: {0}")
    void contentsNullAndEmptyTest(String contents) {
        assertThatThrownBy(() -> new Note(contents)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1보다 작거나 1000보다 큰 경우 예외가 발생한다.")
    @ParameterizedTest(name = "입력값: {0}")
    @MethodSource
    void lengthExceptionTest(String contents) {
        assertThatThrownBy(() -> new Note(contents)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> lengthExceptionTest() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("q".repeat(1001))
        );
    }
}
