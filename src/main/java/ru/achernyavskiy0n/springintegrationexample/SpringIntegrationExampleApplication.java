package ru.achernyavskiy0n.springintegrationexample;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.achernyavskiy0n.springintegrationexample.config.ExampleConfiguration;

import java.util.Scanner;

@SpringBootApplication
public class SpringIntegrationExampleApplication {

    public static void main(String[] args) {

        AbstractApplicationContext context
                = new AnnotationConfigApplicationContext(ExampleConfiguration.class);
        context.registerShutdownHook();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter q and press <enter> to exit the program: ");

        while (true) {
            String input = scanner.nextLine();
            if ("q".equals(input.trim())) {
                break;
            }
        }
        System.exit(0);
    }
}
