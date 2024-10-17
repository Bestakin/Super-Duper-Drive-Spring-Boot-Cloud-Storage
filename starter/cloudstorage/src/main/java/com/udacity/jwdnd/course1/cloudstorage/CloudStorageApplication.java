package com.udacity.jwdnd.course1.cloudstorage;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("mapper")
public class CloudStorageApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

}
