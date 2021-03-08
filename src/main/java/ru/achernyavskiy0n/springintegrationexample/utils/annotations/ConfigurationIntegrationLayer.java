package ru.achernyavskiy0n.springintegrationexample.utils.annotations;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 08.03.2021
 *
 * @author a.chernyavskiy0n
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Configuration
@EnableIntegration
public @interface ConfigurationIntegrationLayer {
}
