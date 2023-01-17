package toyproject1.bookmanbo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    private String contents;
    @OneToMany(mappedBy = "keyword")
    private List<NoteKeyword> noteKeywords = new ArrayList<>();

    public Keyword(String contents) {
        this.contents = contents;
    }

    public static List<Keyword> from(List<String> newKeywordList) {
        return newKeywordList.stream().map(Keyword::new).toList();
    }

    public void addNoteKeyword(NoteKeyword noteKeyword) {
        noteKeywords.add(noteKeyword);
    }
}
