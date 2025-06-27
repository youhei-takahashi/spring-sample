package com.example.musicmanagement.service;

import com.example.musicmanagement.entity.Album;
import com.example.musicmanagement.entity.Music;
import com.example.musicmanagement.exception.AlbumNotFoundException;
import com.example.musicmanagement.exception.MusicNotFoundException;
import com.example.musicmanagement.form.MusicForm;
import com.example.musicmanagement.repository.AlbumRepository;
import com.example.musicmanagement.repository.MusicRepository;
import com.example.musicmanagement.viewmodel.MusicViewModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MusicService {
    private final MusicRepository musicRepository;
    private final AlbumRepository albumRepository;

    public MusicService(MusicRepository musicRepository, AlbumRepository albumRepository) {
        this.musicRepository = musicRepository;
        this.albumRepository = albumRepository;
    }

    public List<Music> getMusicsByAlbumId(long albumId) {
        return musicRepository.getMusicsByAlbumId(albumId);
    }

    @Transactional
    public void createMusic(MusicForm musicForm) {
        Album existingAlbum = albumRepository.getAlbumById(musicForm.getAlbumId());
        if (existingAlbum == null) {
            throw new AlbumNotFoundException("Album not found");
        }

        Music music = new Music();
        music.setAlbumId(musicForm.getAlbumId());
        music.setTitle(musicForm.getTitle());
        music.setDuration(musicForm.getDuration());

        musicRepository.insertMusic(music);
    }

    public void deleteMusic(long musicId) {
        musicRepository.deleteMusicByMusicId(musicId);
    }

    @Transactional
    public void updateMusic(long musicId, Music music) {
        Music existingMusic = getMusicByMusicId(musicId);
        if (existingMusic == null) {
            throw new MusicNotFoundException("Music not found", music.getAlbumId());
        }

        if (musicId != music.getMusicId()) {
            throw new MusicNotFoundException("Music not found", music.getAlbumId());
        }
        musicRepository.updateMusic(music);
    }

    public Music getMusicByMusicId(long musicId) {
        return musicRepository.selectMusicByMusicId(musicId);
    }

    public List<MusicViewModel> selectMusicsWithFavorite(long albumId, long userId) {
        return musicRepository.selectMusicsWithFavorite(albumId, userId);
    }
}
