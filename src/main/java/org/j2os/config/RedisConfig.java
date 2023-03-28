package org.j2os.config;

import org.j2os.service.J2OSMessageSubscriberService;
import org.j2os.service.RIEMPMessageSubscriberService;
import org.j2os.service.SharifUniversityMessageSubscriberService;
import org.j2os.service.TehranUniversityMessageSubscriberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/*
    Bahador, Amirsam
 */
@Configuration
public class RedisConfig {
    @Value("${org.j2os.redis.password}")
    private String password;
    @Value("${org.j2os.redis.host}")
    private String host;
    @Value("${org.j2os.redis.port}")
    private int port;

    @Bean
    RedisTemplate<String, Object> getRedisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getRedisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }

    @Bean
    RedisConnectionFactory getRedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setPassword(password);
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisMessageListenerContainer getRedisContainer() {
        final RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(getRedisConnectionFactory());
        redisMessageListenerContainer.addMessageListener(new J2OSMessageSubscriberService(), new ChannelTopic("J2OS"));
        redisMessageListenerContainer.addMessageListener(new RIEMPMessageSubscriberService(), new ChannelTopic("RIEMP"));
        redisMessageListenerContainer.addMessageListener(new SharifUniversityMessageSubscriberService(), new ChannelTopic("SHARIF"));
        redisMessageListenerContainer.addMessageListener(new TehranUniversityMessageSubscriberService(), new ChannelTopic("TEHRAN"));
        return redisMessageListenerContainer;
    }
}