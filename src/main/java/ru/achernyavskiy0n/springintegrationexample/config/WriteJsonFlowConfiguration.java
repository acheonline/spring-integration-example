package ru.achernyavskiy0n.springintegrationexample.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import ru.achernyavskiy0n.springintegrationexample.transfromer.StringToJsonTransformer;
import ru.achernyavskiy0n.springintegrationexample.utils.annotations.ConfigurationIntegrationLayer;

import java.io.File;
import java.util.Objects;

/**
 * 07.03.2021
 *
 * @author a.chernyavskiy0n
 */
@ConfigurationIntegrationLayer
@RequiredArgsConstructor
public class WriteJsonFlowConfiguration {

    private static final String OUTPUT_DIR_1 = "the_dest1_dir";

    @Autowired
    @Qualifier("stringToJsonTransformer")
    protected StringToJsonTransformer stringToJsonTransformer;

    @Autowired
    @Qualifier("writeJsonFlow")
    protected IntegrationFlow writeJsonFlow;

    @Bean
    protected IntegrationFlow writeJsonFlow() {
        return IntegrationFlows.from("jsonWriteChannel")
                .transform(stringToJsonTransformer::convert)
                .log("end of converting to JSON and start wrtite down file on disk")
                .gateway("writeJsonChannel")
                .log("finish writing flow")
                .get();
    }

    @Bean
    protected MessageChannel writeJsonChannel() {
        return new DirectChannel();
    }


    @Bean
    @ServiceActivator(inputChannel = "writeJsonChannel")
    protected MessageHandler writeJsonFileMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR_1));
        handler.setFileNameGenerator(message -> {
            Object obj = message.getHeaders().get("file_name");
            String str = ((String) Objects.requireNonNull(obj)).split(".xml")[0];
            return str.concat(".json");
        });
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }
}
