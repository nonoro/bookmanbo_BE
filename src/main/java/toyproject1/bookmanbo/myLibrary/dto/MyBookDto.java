package toyproject1.bookmanbo.myLibrary.dto;

import jakarta.persistence.*;
import lombok.*;
import toyproject1.bookmanbo.domain.Period;
import toyproject1.bookmanbo.domain.Status;
import toyproject1.bookmanbo.domain.entity.Account;
import toyproject1.bookmanbo.domain.entity.Book;
import toyproject1.bookmanbo.domain.entity.Note;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBookDto {
        private Long id;
        private Account account;
        private Book book;
        private Period period;
        private int rating;
        private Status status;

        private List<Note> notes = new ArrayList<>();

    }
