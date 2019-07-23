package consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //支持服务注册与发现
public class SpringCloudConsulProducer2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsulProducer2Application.class, args);
    }

}
