package com.myblogrestapi.controller;

import com.myblogrestapi.payload.CommentDto;
import com.myblogrestapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/api
@RequestMapping("/api/")

public class CommentController {



    private CommentService commentService;



    public CommentController(CommentService commentService) {

        this.commentService = commentService;

    }


//http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")

    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,

                                                    @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

    }
    @GetMapping("/posts/{postId}/comments")

    public List<CommentDto> getCommentByPostId(@PathVariable("postId") long postId){

        List<CommentDto> commentDtos = commentService.getCommentByPostId(postId);
        return commentDtos;

    }
    @GetMapping("/posts/{postId}/comments/{commentId}")

    public CommentDto getCommentByPostId(
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId
    )
    {

        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return commentDto;

    }
    @GetMapping("/comments")
    public List<CommentDto> getAllComment(){

        return commentService.getAllComment();

    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")

    public ResponseEntity<String> deleteCommentById(
            @PathVariable("postId") long postId,
             @PathVariable("commentId") long commentId
    )
    {

      commentService.deleteCommentById(postId,commentId);
      return new ResponseEntity<>("comment is deleted",HttpStatus.OK);


    }

}
