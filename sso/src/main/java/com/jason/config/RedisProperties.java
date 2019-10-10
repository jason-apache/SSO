package com.jason.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String nodes;
    private Integer maxAttempts;
    private Integer commandTimeout;
    private Integer expire;

    public String getNodes() {
        return nodes;
    }

    public RedisProperties setNodes(String nodes) {
        this.nodes = nodes;
        return this;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public RedisProperties setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
        return this;
    }

    public Integer getCommandTimeout() {
        return commandTimeout;
    }

    public RedisProperties setCommandTimeout(Integer commandTimeout) {
        this.commandTimeout = commandTimeout;
        return this;
    }

    public Integer getExpire() {
        return expire;
    }

    public RedisProperties setExpire(Integer expire) {
        this.expire = expire;
        return this;
    }
}
