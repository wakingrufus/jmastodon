# jmastodon
Java client library for Mastodon. This project is abandoned. Use https://github.com/sys1yagi/mastodon4j instead.

## Running Integration Test
1) change the server variable in the integration test to point to an instance on which you own an account
2) Run `gradle integrationTest`
3) The browser will open up to the OAuth page. Authenticate.
4) the browser will redirect to localhost, and there will be a token in the url
5) put the token into a file at: `~/token`
6) the integration test will continue automatically
