/**
 * @author KK
 * @version 1.0
 */

package com.cs.organize.albumphotos.service;

import com.cs.organize.albumphotos.domain.entity.AlbumEntity;
import com.cs.organize.albumphotos.domain.entity.PhotoEntity;
import com.cs.organize.albumphotos.domain.repo.AlbumsRepository;
import com.cs.organize.albumphotos.domain.repo.PhotosRepository;
import com.cs.organize.albumphotos.dao.in.AlbumData;
import com.cs.organize.albumphotos.dao.in.PhotoData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataLoadingService {

    private final AlbumsRepository albumsRepository;
    private final PhotosRepository photosRepository;
    private final RestClient restClient;

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public DataLoadingService(AlbumsRepository albumsRepository, PhotosRepository photosRepository, RestTemplate restTemplate) {
        this.albumsRepository = albumsRepository;
        this.photosRepository = photosRepository;
        this.restClient = RestClient.create(restTemplate);
    }

    public void loadAlbums() {
        log.info("In loadAlbums");
        List<AlbumData> albumDataList = restClient.get()
                .uri(BASE_URL + "/albums")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });


        List<PhotoData> photoDataList = restClient.get()
                .uri(BASE_URL + "/photos")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if (albumDataList != null && photoDataList != null) {
            log.info("Downloaded albums = {}", albumDataList.size());
            Map<Long, AlbumEntity> dict = new HashMap<>();
            for (AlbumData albumData : albumDataList) {
                var albumEntity = new AlbumEntity();
                albumEntity.setId(albumData.id());
                albumEntity.setTitle(albumData.title());
                albumEntity.setCreatedAt(LocalDateTime.now());

                albumEntity = albumsRepository.save(albumEntity);
                dict.put(albumData.id(), albumEntity);
            }

            log.info("Downloaded photos = {}", photoDataList.size());
            for (PhotoData photoData : photoDataList) {
                var photoEntity = new PhotoEntity();
                photoEntity.setId(photoData.id());
                photoEntity.setTitle(photoData.title());
                photoEntity.setThumbnailUrl(photoData.thumbnailUrl());
                photoEntity.setUrl(photoData.url());
                photoEntity.setCreatedAt(LocalDateTime.now());
                photoEntity.setAlbumEntity(dict.get(photoData.albumId()));

                photosRepository.save(photoEntity);
            }
        }

        log.info("Return from loadAlbums");
    }
}
