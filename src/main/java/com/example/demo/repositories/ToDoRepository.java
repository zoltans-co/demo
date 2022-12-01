package com.example.demo.repositories;

import com.example.demo.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    Optional<ToDo> findByUserIdAndId(final Long userId, final Long toDoId);

    List<ToDo> findByUserId(Long userId);
}
