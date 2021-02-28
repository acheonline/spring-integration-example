package ru.achernyavskiy0n.springintegrationexample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 26.02.2021
 *
 * @author a.chernyavskiy0n
 */
@Configuration
@Import(value = IntegrationFlowConfiguration.class)
public class ExampleConfiguration {
}
