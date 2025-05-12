package com.example.musicmanagement.mapper;

import com.example.musicmanagement.entity.Album;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlbumMapper {

    @Select("SELECT * FROM albums")
    List<Album> selectAllAlbums();

    @Insert("""
            INSERT INTO albums (title, artist, release_date) 
            VALUES (#{title}, #{artist}, #{releaseDate})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "albumId")
    void insertAlbum(Album album);

    @Select("SELECT * FROM albums WHERE album_id = #{albumId}")
    Album selectAlbumById(long albumId);

    @Delete("DELETE FROM albums WHERE album_id = #{albumId}")
    void deleteAlbumById(long albumId);
}
