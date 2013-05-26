package com.pne.arch.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pne.arch.entity.Role;
import com.pne.arch.entity.User;
import com.pne.arch.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserService();

	@Mock
	private UserRepository userRepositoryMock;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldInitializeWithOneDemoUser() {
		// assert
		verify(userRepositoryMock, times(1)).save(any(User.class));
	}

	@Test
	public void shouldThrowExceptionWhenUserNotFound() {
		// arrange
		thrown.expect(UsernameNotFoundException.class);
		thrown.expectMessage("user not found");

		when(userRepositoryMock.findByUsername("user")).thenReturn(null);
		// act
		userService.loadUserByUsername("user");
	}

	@Test
	public void shouldReturnUserDetails() {
		// arrange
		User demoUser = new User("user", "demo", new HashSet<Role>());
		when(userRepositoryMock.findByUsername("user")).thenReturn(demoUser);

		// act
		UserDetails userDetails = userService.loadUserByUsername("user");

		// assert
		assertEquals(demoUser.getUsername(), userDetails.getUsername());
		assertEquals(demoUser.getPassword(), userDetails.getPassword());
		assertTrue(hasAuthority(userDetails, demoUser.getRoles()));
	}

	private boolean hasAuthority(UserDetails userDetails, Set<Role> roles) {
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			if(authority.getAuthority().equals(roles)) {
				return true;
			}
		}
		return false;
	}
}
