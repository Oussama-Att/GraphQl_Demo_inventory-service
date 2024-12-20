package oss.att.inventoryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.att.inventoryservice.entities.Video;

public interface VideoRepository extends JpaRepository<Video,String> {
}
