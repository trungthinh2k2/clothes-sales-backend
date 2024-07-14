package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.models.UserDetail;
import iuh.fit.salesappbackend.repositories.UserRepository;
import iuh.fit.salesappbackend.service.interfaces.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(JpaRepository<User, Long> repository, UserRepository userRepository) {
        super(repository, User.class);
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetail(user);
    }
}
