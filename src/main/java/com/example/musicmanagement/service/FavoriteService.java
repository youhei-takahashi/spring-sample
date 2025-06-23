package com.example.musicmanagement.service;

import com.example.musicmanagement.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public void insertFavorite(long userId, long musicId) {
        favoriteRepository.insertFavorite(userId, musicId);
    }

    public void deleteFavorite(long userId, long musicId) {
        favoriteRepository.deleteFavorite(userId, musicId);
    }
}
