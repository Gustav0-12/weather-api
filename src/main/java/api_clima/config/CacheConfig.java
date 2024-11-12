package api_clima.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
            RedisCacheConfiguration weatherCache = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofMinutes(30));

            return RedisCacheManager.builder(redisConnectionFactory)
                    .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                    .withCacheConfiguration("weather", weatherCache)
                    .build();
    }
}
