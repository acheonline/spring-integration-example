package ru.achernyavskiy0n.springintegrationexample.domain;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */
@XmlRootElement(name = "person")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @XmlElement(name = "firstName")
    String firstName;
    @XmlElement(name = "lastName")
    String lastName;
}
