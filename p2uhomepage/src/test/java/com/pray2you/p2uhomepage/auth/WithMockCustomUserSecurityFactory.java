package com.pray2you.p2uhomepage.auth;

import com.pray2you.p2uhomepage.global.security.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;
import java.util.Collection;

public class WithMockCustomUserSecurityFactory
        implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser){
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .asList(new SimpleGrantedAuthority(customUser.roles().getRole()));

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(new CustomUserDetails(customUser.userId(),
                customUser.githubId(),
                authorities),
                "password",
                authorities);
        context.setAuthentication(authenticationToken);
        return context;
    }
}
