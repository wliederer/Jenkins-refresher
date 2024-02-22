package com.family.refresh.redis;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;


@Configuration
public class RedisConfig {
	
//	 @Bean
//	    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
//	        return (builder) -> builder
//	                .withCacheConfiguration("parentCache",
//	                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)));
////	                .withCacheConfiguration("customerCache",
////	                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
//	    }

	    @Bean
	    public RedisCacheConfiguration cacheConfiguration() {
	        return RedisCacheConfiguration.defaultCacheConfig()
	                .entryTtl(Duration.ofSeconds(30))
	                .disableCachingNullValues()
	                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	    }
	
}
