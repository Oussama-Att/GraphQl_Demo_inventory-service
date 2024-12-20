package oss.att.inventoryservice.web;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import oss.att.inventoryservice.entities.Creator;
import oss.att.inventoryservice.entities.Video;
import oss.att.inventoryservice.repositories.CreatorRepository;
import oss.att.inventoryservice.repositories.VideoRepository;

import java.util.List;
@Controller
public class VideoGraphQlController {
    CreatorRepository creatorRepository;
    VideoRepository videoRepository;
    public VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        if (creatorRepository == null || videoRepository == null) {
            throw new IllegalArgumentException("Repositories must not be null");
        }
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
    }

    @QueryMapping
    public List<Video> videoList(){
        return videoRepository.findAll();
    }
    @QueryMapping
    public Creator creatorById(@Argument String id) {
        return creatorRepository.findById(String.valueOf(Long.parseLong(id)))
                .orElseThrow(() -> new RuntimeException(String.format("Creator with ID %s not found", id)));
    }




}
