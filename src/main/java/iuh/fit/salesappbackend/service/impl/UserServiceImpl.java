package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.service.interfaces.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    public UserServiceImpl(JpaRepository<User, Long> repository) {
        super(repository, User.class);
    }
}
