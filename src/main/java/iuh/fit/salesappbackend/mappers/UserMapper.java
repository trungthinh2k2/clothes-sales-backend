package iuh.fit.salesappbackend.mappers;

import iuh.fit.salesappbackend.dtos.requests.UserDto;
import iuh.fit.salesappbackend.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final AddressMapper addressMapper;
    public User userDto2User(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .gender(userDto.getGender())
                .role(userDto.getRole())
                .dateOfBirth(userDto.getDateOfBirth())
                .address(addressMapper.addressDto2Address(userDto.getAddress()))
                .build();
    }
}
