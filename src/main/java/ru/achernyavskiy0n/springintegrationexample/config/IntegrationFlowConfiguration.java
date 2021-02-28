package ru.achernyavskiy0n.springintegrationexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

/**
 * 26.02.2021
 * Spring Integration realization configuration
 *
 * @author a.chernyavskiy0n
 */
@Configuration
@EnableIntegration
public class IntegrationFlowConfiguration {

    public static final String INPUT_DIR = "the_source_dir";
    public static final String OUTPUT_DIR = "the_dest_dir";
    public static final String FILE_PATTERN = "*.mpeg";

    /**
     * p2s channel for messaging with bridge pattern abstraction realization for message redirection from @pubSubFileChannel
     */
    @Bean
    @BridgeFrom(value = "pubSubFileChannel")
    public MessageChannel fileChannel1() {
        return new PublishSubscribeChannel();
    }

    /**
     * p2s channel for main messaging
     */
    @Bean
    public MessageChannel pubSubFileChannel() {
        return new PublishSubscribeChannel();
    }

    /**
     * Inbound adapter converts files from input directory to message via MessageSource and publish it to @pubSubFileChannel
     */
    @Bean
    @InboundChannelAdapter(value = "pubSubFileChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader= new FileReadingMessageSource();
        sourceReader.setDirectory(new File(INPUT_DIR));
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        return sourceReader;
    }

    /**
     * Service of writing file from received message to output dir
     */
    @Bean
    @ServiceActivator(inputChannel= "fileChannel1")
    public MessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }

}
