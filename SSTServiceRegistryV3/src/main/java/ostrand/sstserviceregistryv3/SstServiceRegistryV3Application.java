package ostrand.sstserviceregistryv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SstServiceRegistryV3Application {

    public static void main(String[] args) {
        SpringApplication.run(SstServiceRegistryV3Application.class, args);
    }

}
