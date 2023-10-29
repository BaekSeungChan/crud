package com.qortmdcks.crud.controller;


import com.qortmdcks.crud.entity.Post;
import com.qortmdcks.crud.payload.PostDto;
import com.qortmdcks.crud.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    // 데이터를 등록하는 api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    // 데이터를 가져오는 api(전체)


    // 데이터를 가져오는 api(상세)


    // 데이터를 수정하는 api


    // 데이터를 삭제하는 api

}