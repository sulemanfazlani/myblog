package com.myblogrestapi;

import com.myblogrestapi.repository.CommentRepository;
import com.myblogrestapi.repository.PostRepository;
import com.myblogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyblogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyblogRestApiApplication.class, args);
	}

	@Bean
public ModelMapper modelMapper(){
		return new ModelMapper();
}



}
