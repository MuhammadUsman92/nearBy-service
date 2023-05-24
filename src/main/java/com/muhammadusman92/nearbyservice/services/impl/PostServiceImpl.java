package com.muhammadusman92.nearbyservice.services.impl;

import com.muhammadusman92.nearbyservice.config.ConversionDtos;
import com.muhammadusman92.nearbyservice.entity.Location;
import com.muhammadusman92.nearbyservice.entity.NotificationToBeSend;
import com.muhammadusman92.nearbyservice.entity.Post;
import com.muhammadusman92.nearbyservice.entity.User;
import com.muhammadusman92.nearbyservice.exception.ResourceNotFoundException;
import com.muhammadusman92.nearbyservice.payload.PostDto;
import com.muhammadusman92.nearbyservice.repo.LocationRepo;
import com.muhammadusman92.nearbyservice.repo.NotificationToBeSendRepo;
import com.muhammadusman92.nearbyservice.repo.PostRepo;
import com.muhammadusman92.nearbyservice.repo.UserRepo;
import com.muhammadusman92.nearbyservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private NotificationToBeSendRepo notificationToBeSendRepo;
    @Override
    public PostDto createPost(String userEmail, PostDto postDto) {
        Post post = ConversionDtos.postDtoToPost(postDto);
        User findUser = userRepo.findByEmail(userEmail)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userEmail));
        post.setUser(findUser);
        Post savePost = postRepo.save(post);
        List<Location> targetLocations = locationRepo.findAllByLatitudeBetweenAndLongitudeBetween(
                post.getStartLatitude(), post.getEndLatitude(), post.getStartLongitude(), post.getEndLongitude());
        for (Location location:targetLocations) {
            if(!(location.getUser().getEmail().equalsIgnoreCase(userEmail))) {
            NotificationToBeSend notificationToBeSend = new NotificationToBeSend();
            notificationToBeSend.setSubject(post.getSubject());
            notificationToBeSend.setDetail(post.getDetail());
            notificationToBeSend.setPaid(post.isPaid());
            if(post.isPaid())
                notificationToBeSend.setAmount(post.getAmount());
            notificationToBeSend.setUser(location.getUser());
            notificationToBeSend.setSenderEmail(userEmail);
            notificationToBeSendRepo.save(notificationToBeSend);
            }
        }
        return ConversionDtos.postToPostDto(savePost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = ConversionDtos.postDtoToPost(postDto);
        Post findPost = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        post.setId(findPost.getId());
        return ConversionDtos.postToPostDto(postRepo.save(post));
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post findPost = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        return ConversionDtos.postToPostDto(findPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepo.findAll();
        return postList.stream().map(ConversionDtos::postToPostDto).toList();
    }

    @Override
    public void deletePost(Integer postId) {
        Post findPost = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        postRepo.delete(findPost);
    }
}
