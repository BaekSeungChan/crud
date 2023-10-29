package com.qortmdcks.crud.repository;

import com.qortmdcks.crud.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
