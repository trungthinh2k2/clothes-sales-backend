package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.dtos.requests.ChangePasswordRequest;
import iuh.fit.salesappbackend.dtos.responses.LoginResponse;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.models.User;


public interface UserService extends BaseService<User, Long> {
    LoginResponse changePassword(ChangePasswordRequest changePasswordRequest) throws DataNotFoundException;
}
