package com.example.musicmanagement.controller;

import com.example.musicmanagement.service.AlbumService;
import com.example.musicmanagement.service.MusicService;
import com.example.musicmanagement.viewmodel.AlbumViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final AlbumService albumService;
    private final MusicService musicService;

    public AlbumRestController(AlbumService albumService, MusicService musicService) {
        this.albumService = albumService;
        this.musicService = musicService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumViewModel>> albums(@RequestParam(defaultValue = "") String title,
                                 Model model) {
        List<AlbumViewModel> albums = title.isEmpty() ? albumService.getAllAlbumsWithMusicCount() :
                albumService.selectAlbumsWithMusicCountByKeyword(title);

        return ResponseEntity.ok(albums);
    }
}
