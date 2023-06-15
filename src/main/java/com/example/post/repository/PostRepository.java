package com.example.post.repository;

import com.example.post.entity.Post;
import com.example.post.entity.PostFind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    //비밀번호를 생략한 데이터를 조회
    @Query(value = "select p.id as id , p.content as content, p.name as name, p.title as title, p.createdAt as createdAt  from Post p")
    List<PostFind> findPosts();

    @Query(value = "select p.id as id , p.content as content, p.name as name, p.title as title, p.createdAt as createdAt  from Post p where id =?1")
    List<PostFind> findCoicePosts(Long id);
    //선택된 하나의 게시물을 조회하는데 비밀번호는 생략

}
