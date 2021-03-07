package ru.achernyavskiy0n.springintegrationexample.transfromer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.achernyavskiy0n.springintegrationexample.domain.Person;
import ru.achernyavskiy0n.springintegrationexample.jms.ServiceMessage;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */

@Slf4j
@Component
public class StringToJsonTransformer {

    public Person convert(ServiceMessage<Person> data) {
        return null;
        //todo - make converter
//        Person.builder()
//                .firstName(data.getPayload().getFirstName())
//                .lastName(data.getPayload().getLastName())
//                .build();
    }
}
