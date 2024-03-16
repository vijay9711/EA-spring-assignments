package properties.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Autowired
    private ApplicationConfig applicationConfig
    ;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private UserConfig userConfig;
    @Autowired
    private CountryConfig countryConfig;

    public void printConfig() {
        System.out.println("applicationConfigName: " + applicationConfig.getName());
        System.out.println("Version: " + applicationConfig.getVersion());
        System.out.println("Server URL: " + serverConfig.getUrl());
        System.out.println("Server Name: " + serverConfig.getName());
        System.out.println("User Firstname: " + userConfig.getFirstName());
        System.out.println("User Lastname: " + userConfig.getLastName());
        System.out.println("User Username: " + userConfig.getUsername());
        System.out.println("User Password: " + userConfig.getPassword());
        System.out.println("List of Countries: " + countryConfig.getCountries());
    }
}

