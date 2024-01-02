package com.myblogrestapi.service;

import com.myblogrestapi.payload.CommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(long postId, CommentDto commentDto);
  public List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    List<CommentDto> getAllComment();

    void deleteCommentById(long postId, long commentId);
}
