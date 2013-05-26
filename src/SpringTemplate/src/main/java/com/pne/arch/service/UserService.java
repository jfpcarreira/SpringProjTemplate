package com.pne.arch.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pne.arch.entity.Permission;
import com.pne.arch.entity.Role;
import com.pne.arch.entity.User;
import com.pne.arch.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (!StringUtils.hasText(username)) {
			throw new UsernameNotFoundException("Username was empty");
		}

		User user = userRepository.findByUsername(username);

		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
			for (Permission permission : role.getPermissions()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
			}
		}

		logger.debug("List of authorities for user [" + username + "] : " + grantedAuthorities);

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
