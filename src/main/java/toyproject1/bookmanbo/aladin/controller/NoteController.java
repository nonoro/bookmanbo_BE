package toyproject1.bookmanbo.aladin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyproject1.bookmanbo.aladin.service.NoteService;
import toyproject1.bookmanbo.dto.request.NoteRequest;
import toyproject1.bookmanbo.dto.response.NoteResponse;
import toyproject1.bookmanbo.dto.response.Response;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/notes")
@RestController
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/new/{myBookId}")
    public Response<List<NoteResponse>> createNote(@RequestBody @Valid NoteRequest request, @PathVariable Long myBookId) {

        return noteService.save(request, myBookId);
    }

}
