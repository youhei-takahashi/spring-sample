package com.example.musicmanagement.repository;

import com.example.musicmanagement.entity.Album;
import com.example.musicmanagement.mapper.AlbumMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumRepository {
    private final AlbumMapper albumMapper;

    public AlbumRepository(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    public List<Album> getAllAlbums() {
        return albumMapper.selectAllAlbums();
    }
}
