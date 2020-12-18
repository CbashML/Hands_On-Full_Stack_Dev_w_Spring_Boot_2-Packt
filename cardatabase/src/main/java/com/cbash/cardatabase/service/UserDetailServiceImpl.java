package com.cbash.cardatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cbash.cardatabase.domain.User;
import com.cbash.cardatabase.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository urepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentUser = urepository.findByUsername(username);
		java.util.List<org.springframework.security.core.GrantedAuthority> 
			grantedAuthorityList = org.springframework.security.core.authority.AuthorityUtils
										.createAuthorityList(currentUser.getRole());
		UserDetails SecUsrDtlsUser = 
						new org.springframework.security.core.userdetails.User(
							username, 
							currentUser.getPassword(),
							true, 
							true, 
							true, 
							true, 
							grantedAuthorityList
						);
		return SecUsrDtlsUser;
	}

}
