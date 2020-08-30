package org.everton.rest.controller;
import org.everton.domain.entity.Comment;
import org.everton.domain.repository.Comments;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/videos/{id}/comments")
public class CommentController {

    private Comments comments;

    public CommentController(Comments comments) {
        this.comments = comments;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment save(@RequestBody @Valid Comment comment){
        return comments.save(comment);

    }
}
