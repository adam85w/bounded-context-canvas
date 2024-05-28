package net.adam85w.ddd.boundedcontextcanvas.template.image;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "template.image")
public record ImageConfiguration(String extension, int width) {
}
