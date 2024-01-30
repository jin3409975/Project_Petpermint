package com.ssafy.db.repository;

import com.ssafy.db.entity.PostFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 파일 업/다운로드 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface PostFilesRepository extends JpaRepository<PostFiles, Long> {

    List<PostFiles> findByPostId(int postId);

    int deleteByPostId(int postId);
}