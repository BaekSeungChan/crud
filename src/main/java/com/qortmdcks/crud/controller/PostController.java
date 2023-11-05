package com.qortmdcks.crud.controller;


import com.qortmdcks.crud.entity.Post;
import com.qortmdcks.crud.payload.PostDto;
import com.qortmdcks.crud.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Post API's")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    // 데이터를 등록하는 api
    @PostMapping
    @Operation(
            summary = "Create Post",
            description = "Create Post API"
    )
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    // 데이터를 가져오는 api(전체)
    @GetMapping("/all")
    @Operation(
            summary = "Get All Post",
            description = "Get All Post API"
    )
    @ApiResponse(responseCode = "200", description = "Get All Posts")
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    // 데이터를 가져오는 api(상세)
    @GetMapping("/{id}")
    @Operation(
            summary = "Get Post By Id",
            description = "Get Post By Id API"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // 데이터를 수정하는 api
    @PutMapping("/{id}")
    @Operation(
            summary = "Update Post",
            description = "Update Post API"
    )
    @ApiResponse(responseCode = "200", description = "Update")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") long id, @Valid @RequestBody PostDto postDto){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    // 데이터를 삭제하는 api
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Post",
            description = "Delete Post API"
    )
    @ApiResponse(responseCode = "200", description = "Deleted")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("deleted post", HttpStatus.OK);
    }
}
