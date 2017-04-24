package com.github.wakingrufus.jmastodon.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccessTokenResponse {
    @JsonProperty("access_token")
    String accessToken;
    @JsonProperty("token_type")
    String tokenType;
    String scope;
    @JsonProperty("created_at")
    long createdAt;
}
