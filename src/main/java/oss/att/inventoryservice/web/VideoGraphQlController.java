package oss.att.inventoryservice.web;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import oss.att.inventoryservice.dtos.CreatorRequestDTO;
import oss.att.inventoryservice.dtos.CreatorResponseDTO;
import oss.att.inventoryservice.dtos.VideoRequestDTO;
import oss.att.inventoryservice.dtos.VideoResponseDTO;
import oss.att.inventoryservice.entities.Creator;
import oss.att.inventoryservice.entities.Video;
import oss.att.inventoryservice.repositories.CreatorRepository;
import oss.att.inventoryservice.repositories.VideoRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@Controller
public class VideoGraphQlController {
    CreatorRepository creatorRepository;
    VideoRepository videoRepository;
    public VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository) {
       /* if (creatorRepository == null || videoRepository == null) {
            throw new IllegalArgumentException("Repositories must not be null");
        }*/
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

    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequestDTO creator) {
        Creator newCreator = Creator.builder()
                .name(creator.getName())
                .email(creator.getEmail())
                .build();
        return creatorRepository.save(newCreator);
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoRequestDTO video) {
        if (video == null) {
            throw new IllegalArgumentException("videoRequest must not be null");
        }

        Creator creator = creatorRepository.findByEmail(video.getCreator().getEmail())
                .orElseGet(() -> {
                    Creator newCreator = Creator.builder()
                            .name(video.getCreator().getName())
                            .email(video.getCreator().getEmail())
                            .build();
                    return creatorRepository.save(newCreator);
                });

        // Correctly parse the date in yyyy-MM-dd format
        LocalDate publicationDate = LocalDate.parse(video.getDatePublication()); // No additional DateTimeFormatter needed for 'yyyy-MM-dd'

        Video newVideo = Video.builder()
                .name(video.getName())
                .url(video.getUrl())
                .description(video.getDescription())
                .datePublication(publicationDate)
                .creator(creator)
                .build();

        return videoRepository.save(newVideo);
    }


}
