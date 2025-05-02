package com.example.musicmanagement.mapper;

import com.example.musicmanagement.entity.Album;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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

}
