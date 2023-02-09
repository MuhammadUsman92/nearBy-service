package com.muhammadusman92.nearbyservice.services;

import com.muhammadusman92.nearbyservice.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(String userEmail, PostDto postDto);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPosts();
    void deletePost(Integer postId);
}
