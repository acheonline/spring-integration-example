package ru.achernyavskiy0n.springintegrationexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;
import ru.achernyavskiy0n.springintegrationexample.transfromer.StringToJsonTransformer;

import java.io.File;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */
@Component
@Configuration
@EnableIntegration
public class WriteJsonFlowConfiguration {

    public static final String OUTPUT_DIR_1 = "the_dest1_dir";
    public static final String OUTPUT_DIR_2 = "the_dest2_dir";

    @Autowired
    StringToJsonTransformer stringToJsonTransformer;

    @Autowired
    @Qualifier("writeJsonFlow")
    IntegrationFlow writeJsonFlow;

    @Bean
    IntegrationFlow writeJsonFlow() {
        return f -> f
                .transform(stringToJsonTransformer::convert)
                .log("end of converting to JSON and start writing JSON on disk")
                .log("start first directory output")
                .gateway(outPutChannel)
                .log("finish first directory output")
                .gateway(outPutChannel)
                .log("finish second directory output and finish flow")
                .bridge();

    }

    @Bean
    public MessageChannel outPutChannel() {return new DirectChannel(); }

    @Autowired @Qualifier("outPutChannel") MessageChannel outPutChannel;

    @Bean
    @Qualifier("firstOutPutChannel")
    @ServiceActivator(inputChannel = "outPutChannel")
    public MessageHandler firstFileOutPutMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR_1));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }

    @Bean
    @Qualifier("secondOutPutChannel")
    @ServiceActivator(inputChannel = "outPutChannel")
    public MessageHandler secondFileOutPutMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR_2));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }
}
