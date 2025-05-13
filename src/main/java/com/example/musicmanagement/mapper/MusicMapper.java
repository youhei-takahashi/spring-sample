package com.example.musicmanagement.mapper;

import com.example.musicmanagement.entity.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MusicMapper {

    @Select("SELECT * FROM musics WHERE album_id = #{albumId}")
    List<Music> selectMusicsByAlbumId(long albumId);
}
