package com.mainul35.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.util.*;

@Document
public class OAuth2AccessTokenDomain implements OAuth2AccessToken {
    private String tokenId;
    private Set<String> scope;
    private String oAuth2RefreshToken;
    private String tokenType;
    private boolean expired;
    private Date expiration;
    private int expiresIn;
    private String value;

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public OAuth2RefreshToken getRefreshToken() {
        final String refreshToken = oAuth2RefreshToken != null ? oAuth2RefreshToken : UUID.randomUUID().toString();
        return new OAuth2RefreshToken() {
            @Override
            public String getValue() {
                return refreshToken;
            }
        };
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public String getTokenType() {
        return this.tokenType;
    }

    @Override
    public boolean isExpired() {
        return this.expired;
    }

    @Override
    public Date getExpiration() {
        return this.expiration;
    }

    @Override
    public int getExpiresIn() {
        return this.expiresIn;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OAuth2AccessTokenDomain that = (OAuth2AccessTokenDomain) o;
        return expired == that.expired &&
                expiresIn == that.expiresIn &&
                Objects.equals(scope, that.scope) &&
                Objects.equals(oAuth2RefreshToken, that.oAuth2RefreshToken) &&
                Objects.equals(tokenType, that.tokenType) &&
                Objects.equals(expiration, that.expiration) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scope, oAuth2RefreshToken, tokenType, expired, expiration, expiresIn, value);
    }
}
