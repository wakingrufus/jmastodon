package com.github.wakingrufus.jmastodon.client;

import com.github.wakingrufus.jmastodon.mock.OAuthMockResource;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Feature;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class OauthClientTest extends JerseyTest {
    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new ResourceConfig(OAuthMockResource.class);
    }

    @Override
    public TestContainerFactory getTestContainerFactory() {
        return new InMemoryTestContainerFactory();
    }

    @Before
    public void setup() {
        Logger logger = Logger.getLogger(getClass().getName());
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        this.getClient().register(feature);
        log.debug("base url for testing: " + this.getBaseUri());
    }

    @Test
    public void createApp() throws Exception {
        OauthClient oauthClient = new OauthClient(this.getClient(), this.getBaseUri().toString());
        OAuthVerification actual = oauthClient.createApp("name", "website", "redirectUrl");
        Assert.assertNotNull("returns redirect url", actual.getUrl());
    }

    @Test
    public void login() throws Exception {
        OauthClient oauthClient = new OauthClient(this.getClient(), this.getBaseUri().toString());
        String actual = oauthClient.login("clientId", "clientSecret", "code","redirectUri");

    }

}