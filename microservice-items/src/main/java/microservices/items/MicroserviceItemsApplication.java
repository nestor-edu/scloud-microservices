package microservices.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroserviceItemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceItemsApplication.class, args);
    }

}
