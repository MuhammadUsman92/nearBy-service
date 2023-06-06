package com.muhammadusman92.nearbyservice.controllers;

import com.muhammadusman92.nearbyservice.payload.PostDto;
import com.muhammadusman92.nearbyservice.payload.Response;
import com.muhammadusman92.nearbyservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/")
    public ResponseEntity<Response> createPost(@RequestHeader("userEmail") String userEmail,@RequestBody PostDto postDto, HttpServletRequest request){

        PostDto savedPost = postService.createPost(userEmail,postDto);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Post is successfully created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .data(savedPost)
                .build(), CREATED);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<Response> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatePost = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Post is successfully updated")
                .status(OK)
                .statusCode(OK.value())
                .data(updatePost)
                .build(),OK);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<Response> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Post deleted successfully")
                .status(OK)
                .statusCode(OK.value())
                .build(),OK);
    }
    @GetMapping("/")
    public ResponseEntity<Response> getAllPosts(){
        List<PostDto> postDtoList = postService.getAllPosts();
        return new  ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("All Posts are successfully get")
                .status(OK)
                .statusCode(OK.value())
                .data(postDtoList)
                .build(),OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<Response> getPostById(@PathVariable Integer postId){
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Post with id "+postId+" are successfully get")
                .status(OK)
                .statusCode(OK.value())
                .data(postDto)
                .build(),OK);
    }
}
