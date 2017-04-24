package com.github.wakingrufus.jmastodon.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
public class Account {
    long id;
    String username;
    String acct;
    @JsonProperty("display_name")
    String displayName;
    boolean locked;
    @JsonProperty("created_at")
    ZonedDateTime createdAt;
    @JsonProperty("followers_count")
    int followersCount;
    @JsonProperty("following_count")
    int followingCount;
    @JsonProperty("statuses_count")
    int statusesCount;
    String note;
    String url;
    String avatar;
    @JsonProperty("avatar_static")
    String avatarStatic;
    String header;
    @JsonProperty("header_static")
    String headerStatic;

}
