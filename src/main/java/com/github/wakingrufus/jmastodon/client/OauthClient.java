package com.github.wakingrufus.jmastodon.client;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.client.Client;

@Slf4j
public class OauthClient {
    private final String baseUrl;
    private final Client restClient;


    public OauthClient(Client restClient, String baseUrl) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
    }


    public OAuthVerification createApp(String clientName, String website, String oauthRedirectUrl) {
        CreateAppResponse createAppResponse = restClient.target(baseUrl).path("api/v1/apps")
                .queryParam("client_name", clientName)
                .queryParam("redirect_uris", oauthRedirectUrl)
                .queryParam("scopes", "read write follow")
                .queryParam("website", website)
                .request().post(null, CreateAppResponse.class);
        log.debug(createAppResponse.toString());
        return OAuthVerification.builder()
                .clientId(createAppResponse.getClientId())
                .url(baseUrl + "oauth/authorize?response_type=code&client_id=" + createAppResponse.getClientId() + "&redirect_uri=" + oauthRedirectUrl + "&scope=read+write+follow")
                .clientSecret(createAppResponse.getClientSecret())
                .build();
    }

    public String login(String clientId, String clientSecret, String authorizationCode, String redirectUrl) {
        AccessTokenResponse loginResponse = restClient.target(baseUrl).path("oauth/token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", authorizationCode)
                .queryParam("redirect_uri", redirectUrl)
                .queryParam("grant_type", "authorization_code")
                .request().post(null, AccessTokenResponse.class);

        log.debug("access token: " + loginResponse.getAccessToken());
        return loginResponse.getAccessToken();
    }


}
