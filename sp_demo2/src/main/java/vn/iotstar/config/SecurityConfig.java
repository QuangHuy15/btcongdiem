package vn.iotstar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import vn.iotstar.repository.UserInfoRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	UserInfoRepository repository;

	// authentication
	@Bean
	UserDetailsService userDetailsService() {
		return new UserInfoDetailService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean

	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF (nếu không cần)
				.authorizeHttpRequests(auth -> auth.requestMatchers("/user/new").permitAll()
						.requestMatchers("/hello").permitAll() // Trang chủ không yêu cầu xác thực
						.requestMatchers("/customer/**").authenticated() 
				).formLogin(Customizer.withDefaults()) // Sử dụng form login mặc định
				.build();
	}
}
