package ru.achernyavskiy0n.springintegrationexample.transfromer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.achernyavskiy0n.springintegrationexample.domain.Person;
import ru.achernyavskiy0n.springintegrationexample.jms.ServiceMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */
@Slf4j
@Component
public class XmlToStringTransformer {

    public ServiceMessage<Person> convert(String data) throws RuntimeException {
        try {
            Person person = (Person) JAXBContext
                    .newInstance(Person.class)
                    .createUnmarshaller()
                    .unmarshal(new File(data));
            return ServiceMessage.<Person>builder()
                    .payload(person)
                    .build();
        } catch (JAXBException e) {
            log.error("Request parsing exception", e);
            throw new RuntimeException(e);
        }
    }
}
