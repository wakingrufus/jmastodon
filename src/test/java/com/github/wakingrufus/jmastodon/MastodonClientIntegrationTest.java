package com.github.wakingrufus.jmastodon;

import com.github.wakingrufus.jmastodon.client.Account;
import com.github.wakingrufus.jmastodon.client.MastodonClient;
import com.github.wakingrufus.jmastodon.client.MastodonClientFactory;
import com.github.wakingrufus.jmastodon.client.OAuthClientFactory;
import com.github.wakingrufus.jmastodon.client.OAuthVerification;
import com.github.wakingrufus.jmastodon.client.OauthClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
@Category(IntegrationTestCategory.class)
public class MastodonClientIntegrationTest {
    // @Rule
    // public MockServerRule mockServerRule = new MockServerRule(this, 8080);

    //  private MockServerClient mockServerClient;

    @Before
    public void setup() {
        //    mockServerClient
        //          .when(HttpRequest.request().withMethod("POST").withPath("oauth/token"))
        //        .respond(HttpResponse.response().withBody(loginResponse));
    }

    @Test
    public void test() throws Exception {
        String redirectUrl = "http://localhost:8080";
        String baseUrl = "https://mastodon.technology/";
        OauthClient oauthClient = new OAuthClientFactory().newClient(baseUrl);
        OAuthVerification app = oauthClient.createApp("test", "http://localhost:8080", redirectUrl);
        log.info("confirm url: " + app.getUrl());
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(app.getUrl()));
        }
        String accessToken = getToken();
        while ((accessToken == null)) {
            TimeUnit.SECONDS.sleep(5);
            accessToken = getToken();

        }
        String authToken = oauthClient.login(app.getClientId(), app.getClientSecret(), accessToken, redirectUrl);

        MastodonClientFactory mastodonClientFactory = new MastodonClientFactory();
        MastodonClient mastodonClient = mastodonClientFactory.newClient(baseUrl, authToken);
        Account verifyCredentials = mastodonClient.verifyCredentials();
        log.info(verifyCredentials.toString());
        Account getAccount = mastodonClient.getAccount(verifyCredentials.getId());
        log.info(getAccount.toString());


    }

    private String getToken() {
        String accessToken = null;
        try (Scanner scanner = new Scanner(new File(new File(System.getProperty("user.home")), "token.txt"))) {
            String line = scanner.nextLine();
            if (line == null || line.trim().isEmpty()) {
                log.warn("no token found yet");
            } else {
                accessToken = line;
            }
        } catch (Exception e) {
            log.warn("no token found yet");
        }
        return accessToken;
    }


}