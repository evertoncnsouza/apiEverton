package org.everton.domain.repository;
import org.everton.domain.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Videos extends JpaRepository<Video, Integer > {

}
