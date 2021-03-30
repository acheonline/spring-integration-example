package ru.achernyavskiy0n.springintegrationexample.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
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
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@JsonRootName(value = "person")
public class Person {

    @XmlElement private final String firstName;
    @XmlElement private final String lastName;
}
