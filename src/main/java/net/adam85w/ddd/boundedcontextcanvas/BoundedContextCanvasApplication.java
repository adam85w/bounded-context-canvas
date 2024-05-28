package net.adam85w.ddd.boundedcontextcanvas;

import net.adam85w.ddd.boundedcontextcanvas.template.image.ImageConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Bounded Context Canvas Application
 *
 * @author Adam Woźniak <adam85.w@gmail.com>
 */
@SpringBootApplication(scanBasePackages = {"net.adam85w.ddd.boundedcontextcanvas"})
@EnableConfigurationProperties({ ApplicationContext.class, ImageConfiguration.class })
public class BoundedContextCanvasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoundedContextCanvasApplication.class, args);
	}
}
