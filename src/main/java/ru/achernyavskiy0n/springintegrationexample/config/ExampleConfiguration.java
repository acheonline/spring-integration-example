package ru.achernyavskiy0n.springintegrationexample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.achernyavskiy0n.springintegrationexample.utils.annotations.ConfigurationIntegrationLayer;

/**
 * 26.02.2021
 * Main configuration
 *
 * @author a.chernyavskiy0n
 */
@ConfigurationIntegrationLayer
@Import(value = {MainFlowConfiguration.class})
@ComponentScan(basePackages = {
        "ru.achernyavskiy0n.springintegrationexample.transfromer",
        "ru.achernyavskiy0n.springintegrationexample.domain"})
public class ExampleConfiguration {
}
