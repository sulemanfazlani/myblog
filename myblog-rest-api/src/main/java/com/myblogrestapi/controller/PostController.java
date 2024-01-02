package com.myblogrestapi.controller;
import com.myblogrestapi.payload.PostResponse;
import com.myblogrestapi.service.impl.PostServiceImpl;
import com.myblogrestapi.payload.PostDto;
import com.myblogrestapi.service.PostService;
import com.myblogrestapi.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

   private final PostService postService ;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
@PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    PostDto dto = postService.savePost(postDto);
    return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post delete Successfully",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPost(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping //PageNo=0 & PageSize=5
    public PostResponse getAllPosts(
            @RequestParam (value ="PageNo" , defaultValue="0", required=false) int PageNo,
            @RequestParam (value="PageSize" , defaultValue="5", required=false) int PageSize,
            @RequestParam (value="SortBy" , defaultValue="id", required=false) String SortBy,
            @RequestParam (value="SortDer" , defaultValue="asc", required=false) String SortDer
    )
    {
        PostResponse postResponse = postService.getAllPosts(PageNo,PageSize,SortBy,SortDer);
        return postResponse;
    }
}
