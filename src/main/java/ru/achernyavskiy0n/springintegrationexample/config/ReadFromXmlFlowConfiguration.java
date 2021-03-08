package ru.achernyavskiy0n.springintegrationexample.config;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import ru.achernyavskiy0n.springintegrationexample.transfromer.XmlToPojoTransformer;
import ru.achernyavskiy0n.springintegrationexample.utils.annotations.ConfigurationIntegrationLayer;

import java.io.File;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */

@ConfigurationIntegrationLayer
public class ReadFromXmlFlowConfiguration {

    public static final String INPUT_DIR = "the_source_dir";
    public static final String FILE_PATTERN = "request.xml";

    @Autowired
    XmlToPojoTransformer xmlToPojoTransformer;

    @Autowired
    @Qualifier("readXmlFromDiskFlow")
    IntegrationFlow readXmlFromDiskFlow;

    @Autowired
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader= new FileReadingMessageSource();
        sourceReader.setDirectory(new File(INPUT_DIR));
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        return sourceReader;
    }

    @Bean
    IntegrationFlow readXmlFromDiskFlow() {
        return IntegrationFlows.from(fileReadingMessageSource()
                , configurer -> configurer.poller(Pollers.fixedDelay(10000)))
                .handle((p, h) -> {
                    MDC.put("rqid", String.valueOf(h.getId()));
                    return p;
                })
                .log("start flow of reading from source")
                .transform(String.class, xmlToPojoTransformer::convert)
                .log("end converting and finish converting flow")
                .get(); //todo - need bridge to mainFlow, instead of nullChannel
    }
}
