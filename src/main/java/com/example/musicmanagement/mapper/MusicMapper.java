package com.example.musicmanagement.mapper;

import com.example.musicmanagement.entity.Music;
import com.example.musicmanagement.viewmodel.MusicViewModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MusicMapper {

    @Select("SELECT * FROM musics WHERE album_id = #{albumId}")
    List<Music> selectMusicsByAlbumId(long albumId);

    @Insert("INSERT INTO musics (title, duration, album_id) VALUES (#{title}, #{duration}, #{albumId})")
    @Options(useGeneratedKeys = true, keyProperty = "musicId")
    void insertMusic(Music music);

    @Delete("DELETE FROM musics WHERE music_id = #{musicId}")
    void deleteMusicByMusicId(long musicId);

    @Update("""
            UPDATE musics 
            SET
                title = #{title}, 
                duration = #{duration}
            WHERE music_id = #{musicId}
            """)
    void updateMusic(Music music);

    @Select("SELECT * FROM musics WHERE music_id = #{musicId}")
    Music selectMusicByMusicId(long musicId);

    @Select("""
            SELECT
                musics.music_id,
                title,
                duration,
                favorites.user_id IS NOT NULL AS is_favorite
            FROM musics
            LEFT OUTER JOIN favorites ON musics.music_id = favorites.music_id AND favorites.user_id = #{userId}
            WHERE album_id = #{albumId}
            """)
    List<MusicViewModel> selectMusicsWithFavorite(long albumId, long userId);
}
