package com.github.wakingrufus.jmastodon.client;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;

@Slf4j
public class MastodonClient {
    private final String baseUrl;
    private final Client restClient;
    private final String accessToken;


    public MastodonClient(Client restClient, String baseUrl, String accessToken) {
        this.restClient = restClient;
        this.baseUrl = baseUrl;
        this.accessToken = accessToken;
    }

    public Account verifyCredentials() {
        Account account = restClient.target(baseUrl).path("api/v1/accounts/verify_credentials")
                .request()
                .header("Authorization", "Bearer " + accessToken).get(Account.class);
        log.debug(account.toString());
        return account;
    }

    public Account getAccount(long id) {
        Account account = restClient.target(baseUrl).path("api/v1/accounts/" + id)
                .request()
                .header("Authorization", "Bearer " + accessToken).get(Account.class);
        log.debug(account.toString());
        return account;
    }

    public Account updateAccount(Account account) {
        Form form = new Form();
        form.param("display_name", account.getDisplayName());
        form.param("avatar", account.getAvatar());
        form.param("header", account.getHeader());
        form.param("note", account.getNote());
        Account newAccount = restClient.target(baseUrl)
                .path("api/v1/accounts/" + account.getId())
                .request()
                .header("Authorization", "Bearer " + accessToken)
                .post(Entity.form(form), Account.class);
        log.debug(newAccount.toString());
        return newAccount;
    }
}
