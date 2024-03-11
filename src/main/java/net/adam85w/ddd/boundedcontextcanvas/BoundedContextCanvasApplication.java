package net.adam85w.ddd.boundedcontextcanvas;

import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springdoc.webmvc.ui.SwaggerConfigResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Bounded Context Canvas Application
 *
 * @author Adam Woźniak <adam85.w@gmail.com>
 */
@SpringBootApplication(scanBasePackages = {"net.adam85w.ddd.boundedcontextcanvas"})
//@ConfigurationPropertiesScan({"net.adam85w.ddd.boundedcontextcanvas"})
@EnableConfigurationProperties({ ApplicationContext.class })
public class BoundedContextCanvasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoundedContextCanvasApplication.class, args);
	}
}
