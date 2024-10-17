package mapper;

import model.Credential;
import model.Files;
import model.User;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId} and filename = #{filename} LIMIT 1")
   Files getFileByUserAndName(Integer userId, String filename);

    @Select("SELECT * FROM FILES WHERE userId =#{userId}")
    List<Files> getFileByUser(Integer userId);

    @Select("SELECT * FROM FILES")
    Files getAllFile();

    @Insert("INSERT INTO FILES (filename, contenttype, fileSize, filedata, userid) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{filedata}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(Files file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(Integer fileId);

    @Update("UPDATE FILES SET (filename, contenttype, fileSize, filedata, userid) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId}) WHERE fileId = #{fileId}")
    int updateUser(Files file);
}
