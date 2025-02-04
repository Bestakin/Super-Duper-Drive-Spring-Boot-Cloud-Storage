package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface FileMapper {
    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, fileData, userid) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(Files file);

    // Fetch a file by its ID
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFile(Integer fileId);

    // Fetch all files for a specific user
    @Select("SELECT * FROM FILES WHERE userId =#{userId}")
    List<Files> getFileByUser(Integer userId);

    // Fetch all files
    @Select("SELECT * FROM FILES")
    Files getAllFile();

    // Fetch a file by user ID and file name
    @Select("SELECT * FROM FILES WHERE userId = #{userId} AND filename = #{filename}")
   Files getFileByUserAndName(Integer userId, String filename);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFileById(Integer fileId);

    //@Update("UPDATE FILES SET (filename, contenttype, fileSize, filedata, userid) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId}) WHERE fileId = #{fileId}")
   // int updateUser(Files file);
}
