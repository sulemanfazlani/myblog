package com.myblogrestapi.repository;

import com.myblogrestapi.entity.Comment;
import com.myblogrestapi.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
   List <Comment> findByPostId(long postId);
}
