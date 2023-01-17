package toyproject1.bookmanbo.note.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyproject1.bookmanbo.note.service.NoteService;
import toyproject1.bookmanbo.note.dto.request.NoteRequest;
import toyproject1.bookmanbo.note.dto.response.NoteResponse;
import toyproject1.bookmanbo.note.dto.response.Response;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/note")
@RestController
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/new/{myBookId}")
    public Response<List<NoteResponse>> createNote(@RequestBody @Valid NoteRequest request, @PathVariable Long myBookId) {

        return noteService.save(request, myBookId);
    }

    @PutMapping("/update/{noteId}")
    public Response<NoteResponse> updateNote(@RequestBody @Valid NoteRequest request, @PathVariable Long noteId) {

        return noteService.update(request, noteId);
    }
}
