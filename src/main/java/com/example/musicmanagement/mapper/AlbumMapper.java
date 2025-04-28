package com.example.musicmanagement.mapper;

import com.example.musicmanagement.entity.Album;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AlbumMapper {

    @Select("SELECT * FROM albums")
    List<Album> selectAllAlbums();
}
