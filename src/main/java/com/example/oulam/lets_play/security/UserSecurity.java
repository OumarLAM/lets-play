package com.example.oulam.lets_play.security;

import com.example.oulam.lets_play.model.User;
import com.example.oulam.lets_play.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {
    private final UserRepository userRepository;

    public boolean canUpdateUser(Authentication authentication, String userId) {
        User currentUser = userRepository.findByEmail(authentication.getName()).orElse(null);
        User targetUser = userRepository.findById(userId).orElse(null);

        if (currentUser == null || targetUser == null) {
            return false;
        }

        return currentUser.getId().equals(targetUser.getId()) || currentUser.getRole().equals("ADMIN");
    }

    public boolean canDeleteUser(Authentication authentication, String userId) {
        User currentUser = userRepository.findByEmail(authentication.getName()).orElse(null);
        User targetUser = userRepository.findById(userId).orElse(null);

        if (currentUser == null || targetUser == null) {
            return false;
        }

        return currentUser.getId().equals(targetUser.getId()) || currentUser.getRole().equals("ADMIN");
    }
}
