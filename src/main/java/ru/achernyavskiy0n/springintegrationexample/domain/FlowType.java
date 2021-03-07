package ru.achernyavskiy0n.springintegrationexample.domain;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */
@Getter
@Setter
@XmlRootElement(name = "person")
@XmlType(propOrder = {"firstName", "lastName"})
public class FlowType {
    String firstName;
    String lastName;
}
