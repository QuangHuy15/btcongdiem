package vn.iotstar.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.UserInfo;
import vn.iotstar.repository.UserInfoRepository;

@Service
public class UserInfoUserDetails {

	public record UserService(UserInfoRepository repository,
	PasswordEncoder passwordEncoder) {
	public String addUser(UserInfo userInfo) {
	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	repository. save(userInfo);
	return "Them user thanh cong!";
	}

	}
}
