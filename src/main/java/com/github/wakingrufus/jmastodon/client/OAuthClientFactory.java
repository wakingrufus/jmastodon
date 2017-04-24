package com.github.wakingrufus.jmastodon.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OAuthClientFactory {
    public OauthClient newClient(String baseUrl) {
        ClientConfig clientConfig = new ClientConfig();
        ObjectMapper mapper = new ObjectMapperBuilder().build();
        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
        jacksonProvider.setMapper(mapper);
        clientConfig.getInstances().add(jacksonProvider);
        Logger logger = Logger.getLogger(getClass().getName());
        Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
        Client client = ClientBuilder.newClient(clientConfig);
        client.register(feature);
        return new OauthClient(client, baseUrl);
    }
}
