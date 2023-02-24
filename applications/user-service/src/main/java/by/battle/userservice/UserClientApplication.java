package by.battle.userservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableEurekaClient
@EnableJpaAuditing
@SpringBootApplication
public class UserClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserClientApplication.class, args);
    }
}
