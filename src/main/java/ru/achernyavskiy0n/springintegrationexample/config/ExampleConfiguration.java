package ru.achernyavskiy0n.springintegrationexample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
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
        "ru.achernyavskiy0n.springintegrationexample.domain",
        "ru.achernyavskiy0n.springintegrationexample.jms"})
public class ExampleConfiguration {
}
