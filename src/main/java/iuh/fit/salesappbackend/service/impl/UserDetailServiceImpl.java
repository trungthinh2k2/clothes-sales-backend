package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.models.UserDetail;
import iuh.fit.salesappbackend.repositories.UserRepository;
import iuh.fit.salesappbackend.service.interfaces.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetail(user);
    }
}
