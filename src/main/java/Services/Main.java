package Services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
@SpringBootApplication
public class Main
{
    public static void main(String[] args) throws Exception
    {

        SpringApplication.run(Main.class,args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx)
    {
        return args ->
        {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames)
                System.out.println(beanName);
        };
    }
}

