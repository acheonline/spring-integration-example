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
     * p2s канал для передачи сообщения, реализуем абстракцию bridge для переброса сообщения из одного канала в другой
     */
    @Bean
    @BridgeFrom(value = "pubSubFileChannel")
    public MessageChannel fileChannel1() {
        return new PublishSubscribeChannel();
    }

    /**
     * Абстракция канала, по которому будут передаваться сообщения.
     * В данном случае, он publish-suscribe типа, то есть one-to-many коммуникация.
     * @return pub-sub channel
     */
    @Bean
    public MessageChannel pubSubFileChannel() {
        return new PublishSubscribeChannel();
    }

    /**
     * Адаптер для отправки сообщений в канал.
     * Внутри себя содержит бизнес-логику, конвертирой формирует сообщение (из файла в данном случае), которое уже уйдет в канал.
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
     * Сервис, который вычитывает из канала и завершает бизнес-логику записью на диск.
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
