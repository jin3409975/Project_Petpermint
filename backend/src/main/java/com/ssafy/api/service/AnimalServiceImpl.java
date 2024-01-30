package com.ssafy.api.service;

import com.ssafy.api.request.AnimalReq;
import com.ssafy.db.entity.Animal;
import com.ssafy.db.repository.AnimalRepository;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AnimalService")
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean animalCreate(AnimalReq animalReq,String url) {
        if(userRepository.countByUserId(animalReq.getUserId()) == 1) {
            Animal animal = new Animal();
            animal.setUserId(animalReq.getUserId());
            animal.setName(animalReq.getName());
            animal.setAge(animalReq.getAge());
            animal.setNote(animalReq.getNote());
            animal.setGender(animalReq.getGender());
            animal.setPicture(url);
            animal.setSpecie(animalReq.getSpecie());
            animal.setWeight(animalReq.getWeight());
            animalRepository.save(animal);
            return true;
        }
        return false;
    }

    @Override
    public List<Animal> findAllAnimalByUserId(String userId) {
        if(userRepository.countByUserId(userId) == 1) {
            return animalRepository.findAllByUserIdAndIsDelete(userId,0);
        }
        return null;
    }

    @Override
    public Animal findAnimalByAnimalId(int animalId) {
        return animalRepository.findByAnimalIdAndIsDelete(animalId,0);
    }

    @Override
    public boolean animalUpdate(AnimalReq animalReq, String url) {
        animalRepository.updateAnimal(animalReq.getAnimalId(), animalReq.getName(), url,
                animalReq.getSpecie(), animalReq.getAge(), animalReq.getNote(), animalReq.getWeight(), animalReq.getGender());
        return true;
    }

    @Override
    public boolean animalDelete(int animalId) {
        animalRepository.deleteAnimal(animalId);
        return true;
    }
}
