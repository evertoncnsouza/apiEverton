package org.everton.domain.repository;
import org.everton.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Comments extends JpaRepository<Comment,Integer> {

}


