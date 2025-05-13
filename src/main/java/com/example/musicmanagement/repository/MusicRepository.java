package com.example.musicmanagement.repository;

import com.example.musicmanagement.entity.Music;
import com.example.musicmanagement.mapper.MusicMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicRepository {
    private final MusicMapper musicMapper;

    public MusicRepository(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }

    public List<Music> getMusicsByAlbumId(long albumId) {
        return musicMapper.selectMusicsByAlbumId(albumId);
    }
}
