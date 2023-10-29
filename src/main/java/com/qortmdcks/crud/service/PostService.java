package com.qortmdcks.crud.service;

import com.qortmdcks.crud.entity.Post;
import com.qortmdcks.crud.payload.PostDto;
import com.qortmdcks.crud.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<Post> getAllPosts();

    PostDto getPostById(long id);

    void deletePostById(long id);

    PostDto updatePost(PostDto postDto, long id);

}
