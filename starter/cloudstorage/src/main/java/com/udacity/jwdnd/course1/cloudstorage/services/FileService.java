package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    // Create a new file
    public int createFile(Files file) {
        return fileMapper.insert(file);
    }

    // Check if user has any files available
    public boolean isUserFileAvailable(Integer userId){
        return fileMapper.getFileByUser(userId) == null;
    }

    // Fetch all files (if needed)
    public Files getAllFile(){
        return fileMapper.getAllFile();
    }

    // Fetch a file by its ID
    public Files getFileById(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    // Fetch all files for a specific user
    public List<Files> getFilesByUser(Integer userId) {
        return fileMapper.getFileByUser(userId);
    }

    public Files getFile(Integer fileId){
        return fileMapper.getFile(fileId);
    }

   // Check if a file exists by user ID and file name
    public boolean isFileByUserAndFileNameAvailabe(Integer userId, String fileName){
        return fileMapper.getFileByUserAndName(userId, fileName) == null;
    }

    // Delete a file by its ID
    public int deleteRecordById(Integer fileId) {
        return fileMapper.deleteFileById(fileId);
    }
}
