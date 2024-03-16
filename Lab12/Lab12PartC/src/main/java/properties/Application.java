package properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import properties.domain.AppConfig;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties()
public class Application {
    @Autowired
    private AppConfig appConfig;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner printConfiguration() {
        return (args) -> {
            appConfig.printConfig();
        };
    }
}
