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

    public void insertMusic(Music music) {
        musicMapper.insertMusic(music);
    }

    public void deleteMusicByMusicId(long musicId) {
        musicMapper.deleteMusicByMusicId(musicId);
    }

    public void updateMusic(Music music) {
        musicMapper.updateMusic(music);
    }

    public Music selectMusicByMusicId(long musicId) {
        return musicMapper.selectMusicByMusicId(musicId);
    }
}
