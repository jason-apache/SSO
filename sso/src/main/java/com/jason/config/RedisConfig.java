package com.jason.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisCluster getJedisCluster(){

        String nodes = redisProperties.getNodes();
        String[] split = nodes.split(",");
        Set<HostAndPort> hostAndPortSet = new HashSet<>();

        for(String hostAndPort : split){
            String[] str = hostAndPort.split(":");
            hostAndPortSet.add(new HostAndPort(str[0],Integer.parseInt(str[1])));
        }
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet,redisProperties.getCommandTimeout(), redisProperties.getMaxAttempts());
        return jedisCluster;
    }
}
