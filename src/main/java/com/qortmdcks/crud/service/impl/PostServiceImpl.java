package com.qortmdcks.crud.service.impl;

import com.qortmdcks.crud.entity.Category;
import com.qortmdcks.crud.entity.Post;
import com.qortmdcks.crud.payload.PostDto;
import com.qortmdcks.crud.repository.CategoryRepository;
import com.qortmdcks.crud.repository.PostRepository;
import com.qortmdcks.crud.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private CategoryRepository categoryRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,CategoryRepository categoryRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto){

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("No id"));
        Post post = mapToEntity(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);


        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts(){
//        List<Post> posts = postRepository.findAll();
//        return List<PostDto> posts = ;

        List<Post> posts = postRepository.findAll();
        return posts.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("No id"));
        return mapToDTO(post);
    }

    @Override
    public void deletePostById(long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("No id"));
        postRepository.delete(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("No id"));


        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setLocation(postDto.getLocation());
        post.setPrice(postDto.getPrice());


        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
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
