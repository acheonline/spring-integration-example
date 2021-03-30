package ru.achernyavskiy0n.springintegrationexample.transfromer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.achernyavskiy0n.springintegrationexample.domain.Person;
import ru.achernyavskiy0n.springintegrationexample.jms.ServiceMessage;

import java.util.Objects;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */

@Slf4j
@Component
public class StringToJsonTransformer {

    public String convert(ServiceMessage<Person> data) {
        String personAsString = null;
        try {
            personAsString = new ObjectMapper().writeValueAsString(data.getPayload());
            log.debug(personAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNullElse(personAsString, "");
    }
}
