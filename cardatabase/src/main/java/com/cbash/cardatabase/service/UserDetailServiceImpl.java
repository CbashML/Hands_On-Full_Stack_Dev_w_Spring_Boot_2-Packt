package com.cbash.cardatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cbash.cardatabase.domain.User;
import com.cbash.cardatabase.domain.UserRepository;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentUser = userRepository.findByUsername(username);
		List<GrantedAuthority>
			grantedAuthorityList = AuthorityUtils
										.createAuthorityList(currentUser.getRole());
		UserDetails SecurityUserDetails =
						new org.springframework.security.core.userdetails.User(
							username, 
							currentUser.getPassword(),
							true, 
							true, 
							true, 
							true, 
							grantedAuthorityList
						);
		return SecurityUserDetails;
	}

}
