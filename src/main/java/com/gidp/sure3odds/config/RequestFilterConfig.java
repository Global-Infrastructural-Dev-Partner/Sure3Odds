package com.gidp.sure3odds.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "sure.conf.param.filter.request-signature")
public class RequestFilterConfig {

    private List<String> whitelist;
    private Boolean disabled;
    private Long timeBufferInMilliseconds;

    public RequestFilterConfig() {
    }

    public List<String> getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(List<String> whitelist) {
        this.whitelist = whitelist;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Long getTimeBufferInMilliseconds() {
        return timeBufferInMilliseconds;
    }

    public void setTimeBufferInMilliseconds(Long timeBufferInMilliseconds) {
        this.timeBufferInMilliseconds = timeBufferInMilliseconds;
    }

    @Override
    public String toString() {
        return "RequestSignatureFilterConfig{" +
                "whitelist=" + whitelist +
                ", disabled=" + disabled +
                ", timeBufferInMilliseconds=" + timeBufferInMilliseconds +
                '}';
    }
}

