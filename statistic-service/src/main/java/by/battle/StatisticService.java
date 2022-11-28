package by.battle;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StatisticService {

    public static void main(String[] args) {
        SpringApplication.run(StatisticService.class, args);
    }
}
