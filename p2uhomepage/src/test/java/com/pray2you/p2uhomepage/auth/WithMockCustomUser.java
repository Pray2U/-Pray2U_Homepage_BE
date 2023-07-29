package com.pray2you.p2uhomepage.auth;

import com.pray2you.p2uhomepage.domain.model.Role;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityFactory.class)
public @interface WithMockCustomUser {
    long userId() default 0L;
    String githubId() default "gildong Hong";
    Role roles() default Role.ROLE_ADMIN;
}
