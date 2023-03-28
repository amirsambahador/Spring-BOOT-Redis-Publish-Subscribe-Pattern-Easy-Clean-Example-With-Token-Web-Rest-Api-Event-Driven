package org.j2os.api;

import org.j2os.model.Person;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.j2os.common.JSON.convert;

/*
    Bahador, Amirsam
 */
@RestController
public class API {
    private final RedisTemplate<String, Object> redisTemplate;

    public API(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public String send() {
        new Thread(() -> redisTemplate.convertAndSend("J2OS", convert(Person.builder().name("samyar").family("bahador").build()))).start();//No Wait
        redisTemplate.convertAndSend("RIEMP", convert(Person.builder().name("marjan").family("bahador").build()));// Wait
        new Thread(() -> redisTemplate.convertAndSend("TEHRAN", convert(Person.builder().name("amirsam").family("bahador").build()))).start();//No Wait
        redisTemplate.convertAndSend("SHARIF", convert(Person.builder().name("amir").family("bahador").build()));// Wait
        return "OK";
    }
}
