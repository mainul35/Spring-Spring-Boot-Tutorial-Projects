## Guideline

### 1. For receiving access token & refresh token

http://localhost:8081/oauth/token?grant_type=password&username=mainul35&password=secret

* Request Method: POST
* Authorization should be: Basic Auth
* Client ID: my-trusted-client
* Client Secret: secret

* Header:
    Accept: application/json

### 2. For refreshing the access token with refresh token upon access token invalidity

http://localhost:8081/oauth/token?grant_type=refresh_token&refresh_token=YOUR_REFRESH_TOKEN

* YOUR_REFRESH_TOKEN should be the refresh token you have received from the previous request.

For example,
http://localhost:8081/oauth/token?grant_type=refresh_token&refresh_token=056b4216-ebbb-40fa-aa5f-7c752e84b04c

* Other settings will be same as before.

### 3. For accessing the resources

http://localhost:8081/api/?access_token=YOUR_ACCESS_TOKEN

YOUR_ACCESS_TOKEN should be the access token you have received from any of the previous steps. Note that, the access token must have to be the latest one.

For example,
http://localhost:8081/api/?access_token=aeef583d-33b3-4775-ad3b-5d3e9349fcc4


## Further Note:

The application comes with 2 different endpoints with 2 different user types. Explore the rest by yourself.

## Acknowledgement:
http://websystique.com/spring-security/secure-spring-rest-api-using-oauth2/
