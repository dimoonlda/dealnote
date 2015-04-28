package biz.dealnote.rest.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import biz.dealnote.web.model.User;

public class UserContext implements UserDetails {

	private User user;
	
	public UserContext(User user){
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = 
				new ArrayList<SimpleGrantedAuthority>();
		authorities.addAll(
			user.getPartJobs()
				.stream()
				.filter(partJob -> Objects.nonNull(partJob.getFullRoleName()))
				.map(partJob -> new SimpleGrantedAuthority(partJob.getFullRoleName()))
				.collect(Collectors.toList()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPasswd();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isActive();
	}

	@Override
	public boolean isEnabled() {
		return user.isActive();
	}

	@Override
	public boolean equals(Object o) {
		return this == o
			|| o != null && o instanceof UserContext
			&& Objects.equals(user, ((UserContext) o).user);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}

	@Override
	public String toString() {
		return "UserContext{" +
			"user=" + user +
			'}';
	}
}
