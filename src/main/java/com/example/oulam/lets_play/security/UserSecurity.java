package com.example.oulam.lets_play.security;

import com.example.oulam.lets_play.model.User;
import com.example.oulam.lets_play.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {
    private final UserRepository userRepository;

    public AuthorizationManager<RequestAuthorizationContext> canUpdateUser() {
        return (authentication, context) -> {
            String userId = context.getVariables().get("id");
            return new AuthorizationDecision(checkUserAccess(authentication, userId));
        };
    }

    public AuthorizationManager<RequestAuthorizationContext> canDeleteUser() {
        return (authentication, context) -> {
            String userId = context.getVariables().get("id");
            return new AuthorizationDecision(checkUserAccess(authentication, userId));
        };
    }

    private boolean checkUserAccess(Supplier<Authentication> authenticationSupplier, String targetUserId) {
        Authentication authentication = authenticationSupplier.get();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        User currentUser = userRepository.findByEmail(authentication.getName()).orElse(null);
        User targetUser = userRepository.findById(targetUserId).orElse(null);

        if (currentUser == null || targetUser == null) {
            return false;
        }

        // Allow access if user is updating their own account or if user is an admin
        return currentUser.getId().equals(targetUser.getId()) ||
                "ADMIN".equals(currentUser.getRole());
    }
}
