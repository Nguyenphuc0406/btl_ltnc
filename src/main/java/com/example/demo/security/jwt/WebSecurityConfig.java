//package com.example.demo.security.jwt;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.example.demo.service.impl.UserServiceImpl;
//
//@Configuration
//@EnableWebSecurity
//
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {
//
//	@Autowired
//	UserServiceImpl userService;
//
//	@Bean
//	public JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter();
//	}
//
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		// Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
//		return new BCryptPasswordEncoder();
//	}
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
////    }
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userService)
//		//auth.userDetailsService(userService) // Cung cáp userservice cho spring security
//				.passwordEncoder(passwordEncoder()); // cung cấp password encoder
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.csrf().disable(); // config loi 403
//		http.cors() // Ngăn chặn request từ một domain khác
//				.and().authorizeRequests().antMatchers("/api/customer/login").permitAll() // Cho phép tất cả mọi người truy cập
//																					// vào địa chỉ nà
//				.anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập
//		// Thêm một lớp Filter kiểm tra jwt
//		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//	}
//}