package com.cs.organize.albumphotos;

import com.cs.organize.albumphotos.service.DataLoadingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AlbumPhotosApplication implements CommandLineRunner {

    private final DataLoadingService dataLoadingService;

    public AlbumPhotosApplication(DataLoadingService dataLoadingService) {
        this.dataLoadingService = dataLoadingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AlbumPhotosApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        log.info("In run");
        dataLoadingService.loadAlbums();
        log.info("Return from run");
    }
}
