package com.github.wakingrufus.jmastodon.mock;

import com.github.wakingrufus.jmastodon.client.AccessTokenResponse;
import com.github.wakingrufus.jmastodon.client.CreateAppResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.UUID;

@Path("/")
public class OAuthMockResource {
    @Path("api/v1/apps")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CreateAppResponse createApp(@QueryParam("client_name") String clientName,
                                       @QueryParam("redirect_uris") String redirectUris,
                                       @QueryParam("scopes") String scopes,
                                       @QueryParam("website") String website) {
        return CreateAppResponse.builder()
                .clientId(UUID.randomUUID().toString())
                .clientSecret(UUID.randomUUID().toString())
                .redirectUrl(redirectUris)
                .build();
    }

    @Path("oauth/token")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("client_id") String clientId,
                          @QueryParam("client_secret") String clientSecret,
                          @QueryParam("code") String code,
                          @QueryParam("redirect_uri") String redirectUri,
                          @QueryParam("grant_type") String grantType) {
        if (clientId == null || clientSecret == null || code == null
                || grantType == null || !grantType .equals("authorization_code")) {
            return Response.serverError().build();
        }
        return Response.ok().entity(AccessTokenResponse.builder()
                .accessToken(UUID.randomUUID().toString())
                .createdAt(Instant.now().getEpochSecond())
                .scope("read write follow")
                .tokenType("")
                .build()).build();
    }
}
