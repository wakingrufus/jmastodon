package com.github.wakingrufus.jmastodon.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateAppResponse {
    private final String id;
    @JsonProperty("client_id")
    private final String clientId;
    @JsonProperty("client_secret")
    private final String clientSecret;
    @JsonProperty("redirect_uri")
    private final String redirectUrl;
}
