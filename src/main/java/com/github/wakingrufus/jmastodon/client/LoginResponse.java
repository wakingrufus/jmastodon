package com.github.wakingrufus.jmastodon.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class LoginResponse {
    @JsonProperty("access_token")
    private final String accessToken;
}
