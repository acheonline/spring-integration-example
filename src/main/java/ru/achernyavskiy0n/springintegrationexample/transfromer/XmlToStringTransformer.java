package ru.achernyavskiy0n.springintegrationexample.transfromer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.achernyavskiy0n.springintegrationexample.domain.FlowType;
import ru.achernyavskiy0n.springintegrationexample.domain.Person;
import ru.achernyavskiy0n.springintegrationexample.jms.ServiceMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */
@Slf4j
@Component
public class XmlToStringTransformer {

    private JAXBContext jaxbContext;

    public ServiceMessage<FlowType> convert(String data) throws RuntimeException{
        try {
            StringReader reader = new StringReader(data);
            jaxbContext = JAXBContext.newInstance(Person.class);
            return ServiceMessage.<FlowType> builder()
                    .payload((FlowType) jaxbContext.createUnmarshaller().unmarshal(reader))
                    .build();
        } catch (JAXBException e) {
            log.error("Request parsing exception", e);
            throw new RuntimeException(e);
        }
    }
}
