package com.entrophy.entrophy_back.global.security;

import com.entrophy.entrophy_back.user.entity.Users;
import com.entrophy.entrophy_back.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Users users = userRepository.findByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException("해당 아이디의 유저 없음"));
        return toUserDetails(users);
    }

    public UserDetails loadUserById(Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("해당 아이디의 유저 없음"));
        return toUserDetails(users);
    }

    private UserDetails toUserDetails(Users users) {
        return new org.springframework.security.core.userdetails.User(
                users.getLoginId(),
                users.getPassword(),
                List.of()
        );
    }
}
