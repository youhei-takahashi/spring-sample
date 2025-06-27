package com.example.musicmanagement.service;

import com.example.musicmanagement.entity.Album;
import com.example.musicmanagement.exception.AlbumNotFoundException;
import com.example.musicmanagement.form.AlbumForm;
import com.example.musicmanagement.repository.AlbumRepository;
import com.example.musicmanagement.viewmodel.AlbumViewModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.getAllAlbums();
    }

    public void createAlbum(AlbumForm albumForm) {
        Album album = new Album();
        album.setTitle(albumForm.getTitle());
        album.setArtist(albumForm.getArtist());
        album.setReleaseDate(albumForm.getReleaseDate());
        albumRepository.insertAlbum(album);
        System.out.println("hello");
    }

    /**
     * 引数で受け取ったアルバムIDをもとにアルバム情報を1件取得
     * @param albumId アルバムID
     * @return アルバムの情報
     */
    public Album getAlbumById(long albumId) {
        return albumRepository.getAlbumById(albumId);
    }

    public void deleteAlbumById(long albumId) {
        albumRepository.deleteAlbumById(albumId);
    }

    @Transactional
    public void updateAlbum(long albumId, Album album) {
        Album existingAlbum = albumRepository.getAlbumById(albumId);
        if (existingAlbum == null) {
            throw new AlbumNotFoundException("Album not found");
        }

        if (albumId != album.getAlbumId()) {
            throw new AlbumNotFoundException("Album not found");
        }
        albumRepository.updateAlbum(album);
    }

    public List<AlbumViewModel> getAllAlbumsWithMusicCount() {
        return albumRepository.getAllAlbumsWithMusicCount();
    }
}
