package ru.achernyavskiy0n.springintegrationexample.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */
@XmlRootElement
public class Person {

    String firstName;
    String lastName;

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
