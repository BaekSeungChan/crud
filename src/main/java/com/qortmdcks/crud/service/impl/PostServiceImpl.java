package com.qortmdcks.crud.service.impl;

import com.qortmdcks.crud.entity.Post;
import com.qortmdcks.crud.payload.PostDto;
import com.qortmdcks.crud.repository.PostRepository;
import com.qortmdcks.crud.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto){

        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public List<Post> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public PostDto getPostById(long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("No id"));
        return mapToDTO(post);
    }

    private PostDto mapToDTO(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);

        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
        return post;
    }
}
