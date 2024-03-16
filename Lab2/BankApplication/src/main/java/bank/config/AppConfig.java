package bank.config;

import bank.aop.BankAOP;
import bank.integration.jms.JMSSender;
import bank.integration.jms.JMSSenderImpl;
import bank.integration.logging.Logger;
import bank.integration.logging.LoggerImpl;
import bank.repository.AccountRepository;
import bank.repository.AccountRepositoryImpl;
import bank.service.AccountService;
import bank.service.AccountServiceImpl;
import bank.service.CurrencyConverter;
import bank.service.CurrencyConverterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public BankAOP bankAOP(Logger logger){
        return new BankAOP(logger);
    }
    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl();
    }

    @Bean
    public AccountRepository accountRepository(){
        return new AccountRepositoryImpl();
    }

    @Bean
    public CurrencyConverter currencyConverter() {
        return new CurrencyConverterImpl();
    }

    @Bean
    public JMSSender jmsSender(){
        return new JMSSenderImpl();
    }

    @Bean
    public Logger logger(){
        return new LoggerImpl();
    }
}
