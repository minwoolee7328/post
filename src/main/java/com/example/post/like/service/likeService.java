package com.example.post.like.service;

import com.example.post.entity.Post;
import com.example.post.like.entity.postLike;
import com.example.post.like.repository.likeRepository;
import com.example.post.repository.PostRepository;
import com.example.post.security.UserDetailsImpl;
import com.example.post.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class likeService {

    private final likeRepository likeRepository;
    private final PostRepository postRepository;

    public likeService(com.example.post.like.repository.likeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }


    // 라이크 테이블에 데이터가 존재하는지 확인
    // 라이크 테이블에 있는 데이터가  true/false 인지 확인
    public boolean checkLike(Long id, UserDetailsImpl userDetails) {
        //해당 게시글이 존재하는지 확인
        Post post = findPost(id);
        User user = userDetails.getUser();

        // 토큰에 저장된 해당 유저의 데이터를 찾는다
        Optional<postLike> likeCheck = likeRepository.findByPost_idAndUser_id(post.getId(), user.getId());

        // like 테이블에 User 데이터가 있는지 확인
        if(likeCheck.isEmpty()){
            // 없다면 true로 데이터 생성
            postLike postLike = new postLike(user, post);
            likeRepository.save(postLike);

            // 해당 게시글의 좋아요 수 증가


            return postLike.isLikechek();
        }

        //데이터가 있으면 종아요를 클릭했는지 안했는지를 ture/false로 반환
        return likeCheck.get().isLikechek();

    }

    // 사용자가 버튼을 눌렀을시 좋아요 의 상태를 확인해서 변경 해준다
    @Transactional
    public boolean chLike(Long id, UserDetailsImpl userDetails) {
        Post post = findPost(id);
        User user = userDetails.getUser();

        // 토큰에 저장된 해당 유저의 데이터를 찾는다
        Optional<postLike> likeCheck = likeRepository.findByPost_idAndUser_id(post.getId(), user.getId());

        if(likeCheck.get().isLikechek()){
            // ture인 상태
            likeCheck.get().setLikechek(false);

            likeRepository.save(likeCheck.get());
        }else{
            // false인 상태
            likeCheck.get().setLikechek(true);

            likeRepository.save(likeCheck.get());
        }

        return likeCheck.get().isLikechek();
    }

    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
