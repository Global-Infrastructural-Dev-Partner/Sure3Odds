package com.gidp.sure3odds.config;

import java.util.List;

public class RequestFilterConfig {

    private List<String> whitelist;
    private Boolean disabled;
    private Long timeBufferInMilliseconds;

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
        return "RequestFilterConfig{" +
                "whitelist=" + whitelist +
                ", disabled=" + disabled +
                ", timeBufferInMilliseconds=" + timeBufferInMilliseconds +
                '}';
    }

    public RequestFilterConfig(List<String> whitelist, Boolean disabled, Long timeBufferInMilliseconds) {
        this.whitelist = whitelist;
        this.disabled = disabled;
        this.timeBufferInMilliseconds = timeBufferInMilliseconds;
    }
}
