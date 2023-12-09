package com.pray2you.p2uhomepage.global.security.refreshtoken;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value= "jwtToken", timeToLive = 60*60*24*7)
public class RefreshToken {

    @Id
    private String id;

    private String token;

    public RefreshToken(String id, String token) {
        this.id = id;
        this.token = token;
    }
}
