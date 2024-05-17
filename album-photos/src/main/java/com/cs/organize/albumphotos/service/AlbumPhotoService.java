/**
 * @author KK
 * @version 1.0
 */

package com.cs.organize.albumphotos.service;

import com.cs.organize.albumphotos.domain.entity.PhotoEntity;
import com.cs.organize.albumphotos.exceptions.ResourceNotFoundException;
import com.cs.organize.albumphotos.domain.entity.AlbumEntity;
import com.cs.organize.albumphotos.domain.repo.AlbumsRepository;
import com.cs.organize.albumphotos.model.out.AlbumOutput;
import com.cs.organize.albumphotos.model.out.PhotoOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumPhotoService {

    private final AlbumsRepository albumsRepository;

    public AlbumPhotoService(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public AlbumOutput getAlbum(Long id) {
        AlbumEntity albumEntity = albumsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album not found"));

        List<PhotoEntity> photoEntityList = Optional.ofNullable(albumEntity.getPhotoEntityList())
                .orElseThrow(() -> new ResourceNotFoundException("No photos found for album with id: " + id));

        List<PhotoOutput> photoOutputList = photoEntityList.stream()
                .map(photoEntity -> new PhotoOutput(
                        photoEntity.getId(),
                        photoEntity.getTitle(),
                        photoEntity.getUrl(),
                        photoEntity.getThumbnailUrl()))
                .toList();
        return new AlbumOutput(albumEntity.getId(), albumEntity.getTitle(), photoOutputList);
    }
}
