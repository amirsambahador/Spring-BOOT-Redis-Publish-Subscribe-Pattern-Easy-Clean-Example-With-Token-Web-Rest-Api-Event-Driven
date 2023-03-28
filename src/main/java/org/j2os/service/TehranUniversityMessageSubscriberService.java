package org.j2os.service;

import lombok.SneakyThrows;
import org.j2os.common.JSON;
import org.j2os.model.Person;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/*
    Bahador, Amirsam
 */
@Service
public class TehranUniversityMessageSubscriberService implements MessageListener {
    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        var person = JSON.parse(Person.class, message);
        System.out.println("Tehran:" + person.getName() + " " + person.getFamily());
    }
}
