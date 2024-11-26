package vn.iotstar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.iotstar.entity.UserInfo;
import vn.iotstar.repository.UserInfoRepository;

public class UserInfoService {

	@Autowired
	UserInfoRepository repository;

	public void UserInfoUserDetails(UserInfoRepository userInfoRepository) {
	this.repository = userInfoRepository;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repository.findByName(username);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
	}
}
