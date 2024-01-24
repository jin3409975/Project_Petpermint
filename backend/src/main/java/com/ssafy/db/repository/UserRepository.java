package com.ssafy.db.repository;

import com.ssafy.db.entity.User;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.

    User findByUserIdAndIsDelete(String userId, int isDelete);

    Long countByUserId(String userId);
    User findByUserNameAndPhone(String userName, String phone);

    Long countByUserIdAndUserName(String userId, String UserName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User set password = :password where userId = :userId and userName = :userName", nativeQuery = true )
    void updatePassword(@Param("userId") String userId, @Param("userName") String userName, @Param("password") String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User set password = :password, userName = :userName, picture = :picture where userId = :userId", nativeQuery = true )
    void userUpdateNormal(@Param("userId") String userId, @Param("userName") String userName, @Param("password") String password, @Param("pirture") String picture);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ExpertUser set note = :note, startTime = :startTime, endTime = :endTime, hospitalNo = :hospitalNo" +
                    " where userId = :userId", nativeQuery = true )
    void userUpdateExpert(@Param("userId") String userId, @Param("note") String note, @Param("startTime") String startTime,
                          @Param("endTime") String endTime, @Param("hospitalNo") String hospitalNo);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User set isDelete = 1 where userId = :userId", nativeQuery = true )
    void deleteUser(@Param("userId") String userId);
}