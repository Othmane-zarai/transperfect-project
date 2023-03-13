package com.example.project.repository;

import com.example.project.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByAgeOrNameOrGender(Integer age,String name,String genger);
}
