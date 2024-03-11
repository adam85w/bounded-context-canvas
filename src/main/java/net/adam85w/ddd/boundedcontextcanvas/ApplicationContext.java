package net.adam85w.ddd.boundedcontextcanvas;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record ApplicationContext(String name, String version, String brand)  {
}
