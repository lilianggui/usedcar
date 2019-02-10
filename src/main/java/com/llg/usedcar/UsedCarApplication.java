package com.llg.usedcar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = "com.llg.usedcar")
@ServletComponentScan
@MapperScan("com.llg.usedcar.mapper.dao")
public class UsedCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsedCarApplication.class, args);
	}

}

