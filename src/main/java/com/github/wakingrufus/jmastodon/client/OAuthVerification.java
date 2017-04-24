package com.github.wakingrufus.jmastodon.client;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OAuthVerification {
    String clientId;
    String url;
    String clientSecret;
}
