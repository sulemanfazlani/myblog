package com.myblogrestapi.service.impl;

import com.myblogrestapi.entity.Comment;
import com.myblogrestapi.entity.Post;
import com.myblogrestapi.exception.ResourceNotFound;
import com.myblogrestapi.payload.CommentDto;
import com.myblogrestapi.repository.CommentRepository;
import com.myblogrestapi.repository.PostRepository;
import com.myblogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl( CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
            Post post = postRepository.findById(postId).orElseThrow(
                    ()-> new ResourceNotFound("post not found with id"+postId)
            );
            Comment comment = mapToEntity(commentDto);
//        Comment comment = new Comment();
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
            CommentDto dto = mapToDto(savedComment);
            return dto;


    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFound("post not found with id"+postId)
        );
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return  commentDtos;
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFound("post not found with id"+postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFound("comment not found with id"+commentId)
        );
        CommentDto commentDto = mapToDto(comment);
        return commentDto;

    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFound("post not found with id"+postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFound("comment not found with id"+commentId)
        );
        commentRepository.deleteById(commentId);
    }

    private CommentDto mapToDto(Comment savedComment) {
        CommentDto dto = mapper.map(savedComment, CommentDto.class);
        return dto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        return  comment;

    }
}
