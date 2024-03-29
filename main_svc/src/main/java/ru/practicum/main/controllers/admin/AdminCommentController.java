package ru.practicum.main.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.CommentDto;
import ru.practicum.main.dto.NewCommentDto;
import ru.practicum.main.services.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
public class AdminCommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getCommentsByEventId(@Positive
                                                 @RequestParam(value = "eventId") Long eventId,
                                                 @PositiveOrZero
                                                 @RequestParam(value = "from", defaultValue = "0") Integer from,
                                                 @Positive
                                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return commentService.getCommentsByEventIdByAdmin(eventId, from, size);
    }

    @GetMapping("/{commentId}")
    public CommentDto getCommentById(@PathVariable(value = "commentId") Long commentId) {
        return commentService.getCommentsByIdByAdmin(commentId);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable(value = "commentId") Long commentId) {
        commentService.deleteCommentByAdmin(commentId);
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateComment(@RequestBody @Valid NewCommentDto newCommentDto,
                                    @PathVariable(value = "commentId") Long commentId) {
        return commentService.updateCommentByAdmin(newCommentDto, commentId);
    }
}
