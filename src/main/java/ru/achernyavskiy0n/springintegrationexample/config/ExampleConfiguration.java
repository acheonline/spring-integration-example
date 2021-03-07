package ru.achernyavskiy0n.springintegrationexample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 26.02.2021
 * Main configuration
 *
 * @author a.chernyavskiy0n
 */
@Configuration
@Import(value = {MainFlowConfiguration.class})
@ComponentScan(basePackages = {
        "ru.achernyavskiy0n.springintegrationexample.transfromer",
        "ru.achernyavskiy0n.springintegrationexample.domain"})
public class ExampleConfiguration {
}
