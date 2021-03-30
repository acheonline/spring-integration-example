package ru.achernyavskiy0n.springintegrationexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.integration.dsl.IntegrationFlow;
import ru.achernyavskiy0n.springintegrationexample.utils.annotations.ConfigurationIntegrationLayer;

/**
 * 07.03.2021
 *
 * Example of custom flow configuration
 * Firstly - flow is reading XML file from source
 * Secondly - flow is writing separately to 2 different sources
 *
 * @author a.chernyavskiy0n
 */

@ConfigurationIntegrationLayer
@Import(value = {ReadFromXmlFlowConfiguration.class, WriteJsonFlowConfiguration.class})
public class MainFlowConfiguration {

    @Autowired
    ReadFromXmlFlowConfiguration xmlFlow;

    @Autowired
    WriteJsonFlowConfiguration jsonFlow;

    @Bean
    IntegrationFlow mainFlow() {
        return f -> f
                .log("Start main flow")
                .gateway(xmlFlow.readXmlFromDiskFlow)
                .log("finish of writing XML on disk and switch to converting to JSON flow")
                .gateway(jsonFlow.writeJsonFlow)
                .log("finish flow")
                .bridge();
    }
}
