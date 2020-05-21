package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.entity.User;
import com.example.demo.reponsitory.UserReponsitoty;

@SpringBootApplication
public class BaiTapLonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaiTapLonApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
//	@Autowired
//	UserReponsitoty userRepository;
//	@Autowired
//	PasswordEncoder passwordEncoder;
//

}
