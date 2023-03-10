package toyproject1.bookmanbo.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Note {

    private static final int MAX_LENGTH = 1000;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myBook_id")
    private MyBook myBook;

    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(updatable = false)
    protected LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "note", orphanRemoval = true)
    private List<NoteKeyword> keywords = new ArrayList<>();

    public Note(String contents) {
        validate(contents);
        this.contents = contents;
    }

    public Note(String contents, MyBook myBook) {
        validate(contents);
        this.contents = contents;
        this.myBook = myBook;
    }

    private void validate(String contents) {
        if (contents == null || contents.equals("")) {
            throw new IllegalArgumentException("내용은 빈값이거나 null 일 수 없습니다");
        }

        if (contents.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("내용의 길이는 " + MAX_LENGTH + "보다 클 수 없습니다.");
        }
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }

    public void addKeywords(NoteKeyword noteKeyword) {
        this.keywords.add(noteKeyword);
    }
}
