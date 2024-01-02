package com.myblogrestapi.service;

import com.myblogrestapi.payload.PostDto;
import com.myblogrestapi.payload.PostResponse;

import java.util.List;


public interface PostService {

    PostDto savePost(PostDto postDto);

    void deletePost(long id);

    PostDto updatePost(long id, PostDto postDto);

    PostDto getPost(long id);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDer);
}
