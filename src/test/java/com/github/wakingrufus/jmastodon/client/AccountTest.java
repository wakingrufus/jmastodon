package com.github.wakingrufus.jmastodon.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class AccountTest {
    private final String json = "{\"id\":1234,\"username\":\"user\",\"acct\":\"user\",\"display_name\":\"user\",\"locked\":false,\"created_at\":\"2017-04-08T05:44:47.662Z\",\"followers_count\":9,\"following_count\":8,\"statuses_count\":25,\"note\":\"Software Developer\",\"url\":\"https://mastodon.technology/@user\",\"avatar\":\"\",\"avatar_static\":\"\",\"header\":\"/headers/original/missing.png\",\"header_static\":\"/headers/original/missing.png\"}";

    @Test
    public void testSerialization() throws IOException {
        ObjectMapper objectMapper = new ObjectMapperBuilder().build();
        Account fromJson = objectMapper.readValue(json, Account.class);
        Assert.assertEquals(5489L, fromJson.getId());
        String secondJson = objectMapper.writeValueAsString(fromJson);
        Account actual = objectMapper.readValue(secondJson, Account.class);
        Assert.assertEquals(fromJson, actual);
    }
}
