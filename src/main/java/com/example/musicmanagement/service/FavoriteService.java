package com.example.musicmanagement.service;

import com.example.musicmanagement.entity.Music;
import com.example.musicmanagement.exception.MusicNotFoundException;
import com.example.musicmanagement.repository.FavoriteRepository;
import com.example.musicmanagement.repository.MusicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MusicRepository musicRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, MusicRepository musicRepository) {
        this.favoriteRepository = favoriteRepository;
        this.musicRepository = musicRepository;
    }

    @Transactional
    public void insertFavorite(long userId, long albumId, long musicId) {
        Music existingMusic = musicRepository.selectMusicByMusicId(musicId);
        if (existingMusic == null) {
            throw new MusicNotFoundException("Music not found", albumId);
        }

        favoriteRepository.insertFavorite(userId, musicId);
    }

    public void deleteFavorite(long userId, long musicId) {
        favoriteRepository.deleteFavorite(userId, musicId);
    }
}
