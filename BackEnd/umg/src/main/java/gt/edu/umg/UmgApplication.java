package gt.edu.umg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import gt.edu.umg.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class UmgApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmgApplication.class, args);
	}
}
