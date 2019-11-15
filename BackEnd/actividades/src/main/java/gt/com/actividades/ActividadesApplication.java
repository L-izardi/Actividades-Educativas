package gt.com.actividades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import gt.com.actividades.utils.AppProperty;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties({AppProperty.class})
public class ActividadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActividadesApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ActividadesApplication.class);
	}
}
