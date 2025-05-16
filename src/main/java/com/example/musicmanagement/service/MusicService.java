package com.example.musicmanagement.service;

import com.example.musicmanagement.entity.Music;
import com.example.musicmanagement.form.MusicForm;
import com.example.musicmanagement.repository.MusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getMusicsByAlbumId(long albumId) {
        return musicRepository.getMusicsByAlbumId(albumId);
    }

    public void createMusic(MusicForm musicForm) {
        Music music = new Music();
        music.setAlbumId(musicForm.getAlbumId());
        music.setTitle(musicForm.getTitle());
        music.setDuration(musicForm.getDuration());

        musicRepository.insertMusic(music);
    }

    public void deleteMusic(long musicId) {
        musicRepository.deleteMusicByMusicId(musicId);
    }
}
