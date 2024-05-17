/**
 * @author KK
 * @version 1.0
 */

package com.cs.organize.albumphotos.api;

import com.cs.organize.albumphotos.model.out.AlbumOutput;
import com.cs.organize.albumphotos.service.AlbumPhotoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlbumPhotoController {

    private final AlbumPhotoService albumPhotoService;

    public AlbumPhotoController(AlbumPhotoService albumPhotoService) {
        this.albumPhotoService = albumPhotoService;
    }

    @GetMapping("/album/{albumId}")
    public AlbumOutput getAlbumById(@PathVariable("albumId") long id) {
        return albumPhotoService.getAlbum(id);
    }
}
