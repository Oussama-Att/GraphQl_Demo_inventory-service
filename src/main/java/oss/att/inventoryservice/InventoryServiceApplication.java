package oss.att.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import oss.att.inventoryservice.entities.Creator;
import oss.att.inventoryservice.entities.Video;
import oss.att.inventoryservice.repositories.CreatorRepository;
import oss.att.inventoryservice.repositories.VideoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	private CreatorRepository creatorRepository;
	private VideoRepository videoRepository;

	public InventoryServiceApplication(CreatorRepository creatorRepository, VideoRepository videoRepository) {
		this.creatorRepository = creatorRepository;
		this.videoRepository = videoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
		return args -> {
			List<Creator> creators = List.of(
					Creator.builder().name("os1").email("os1@gmail.com").build(),
					Creator.builder().name("os2").email("os2@gmail.com").build()
			);
			creatorRepository.saveAll(creators);

			List<Video> videos = List.of(
					Video.builder().name("Video 1").url("http://video1.com").description("Learn Spring").datePublication(new Date()).creator(creators.get(0)).build(),
					Video.builder().name("Video 2").url("http://video2.com").description("Learn GraphQL").datePublication(new Date()).creator(creators.get(1)).build()
			);
			videoRepository.saveAll(videos);
		};
	}
}
