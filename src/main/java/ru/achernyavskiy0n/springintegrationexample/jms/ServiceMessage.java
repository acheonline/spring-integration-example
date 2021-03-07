package ru.achernyavskiy0n.springintegrationexample.jms;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */
@Builder
@Data
public class ServiceMessage <T> {
    Map<String, String> headers;
    T payload;
}
