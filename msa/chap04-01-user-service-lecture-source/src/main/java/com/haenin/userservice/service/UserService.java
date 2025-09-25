package com.haenin.userservice.service;

import com.haenin.userservice.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

// solid... 를 위해 서비스 인터페이스
public interface UserService extends UserDetailsService {

    void registUser(UserDTO userDTO);

    UserDTO getUserById(String memNo);
}
