package com.rekahdo.goodshop.user_service.security.config;

import com.rekahdo.goodshop.user_service.entities.AppUser;
import com.rekahdo.goodshop.user_service.exceptions.classes.AccountNotFoundException;
import com.rekahdo.goodshop.user_service.feign.clients.AdminServiceClient;
import com.rekahdo.goodshop.user_service.repositories.AppUserRepository;
import com.rekahdo.goodshop.user_service.utilities.ApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository repository;

	@Autowired
	private AdminServiceClient adminService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = repository.findByUsernameOrUidOrEmail(username)
				.orElseThrow(() -> new AccountNotFoundException(username));

		return new AppUserDetails(user, adminService.retrieveRoles(user.getId(), ApiKey.ADMIN_SERVICE));
	}

}
