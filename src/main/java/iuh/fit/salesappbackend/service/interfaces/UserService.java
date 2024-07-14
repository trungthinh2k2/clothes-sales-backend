package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends BaseService<User, Long>, UserDetailsService {
}
