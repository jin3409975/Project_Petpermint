package com.ssafy.db.repository;

import com.ssafy.db.entity.Animal;
import com.ssafy.db.entity.VideoRoom;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


/**
 * 화상 방 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByUserIdAndIsDelete(String userId, int isDelete);
    Animal findByAnimalIdAndIsDelete(int animalId, int isDelete);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Animal set name = :name , picture = :picture , specie = :specie , age = :age , " +
            "note = :note , weight = :weight , gender = :gender " +
            "where animalId = :animalId", nativeQuery = true )
    void updateAnimal(@Param("animalId") int animalId, @Param("name") String name, @Param("picture") String picture,
                        @Param("specie") String specie, @Param("age") String age, @Param("note") String note,
                        @Param("weight") String weight, @Param("gender") String gender);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Animal set isDelete = 1 where animalId = :animalId", nativeQuery = true )
    void deleteAnimal(@Param("animalId") int animalId);

    @Query(value = "select * from Animal where animalId = :animalId", nativeQuery = true)
    Animal findPicture(int animalId);
}