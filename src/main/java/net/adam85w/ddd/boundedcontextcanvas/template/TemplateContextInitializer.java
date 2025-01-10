package net.adam85w.ddd.boundedcontextcanvas.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import net.adam85w.ddd.boundedcontextcanvas.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;

@Configuration
class TemplateContextInitializer {

    private final Logger LOGGER = LoggerFactory.getLogger(TemplateContextInitializer.class);
    private final ResourceLoader resourceLoader;
    private final ApplicationContext applicationContext;

    TemplateContextInitializer(ResourceLoader resourceLoader, ApplicationContext applicationContext) {
        this.resourceLoader = resourceLoader;
        this.applicationContext = applicationContext;
    }

    @Bean
    TemplateContextCollection createTemplateContextContainer() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        LOGGER.info("Start initializing templates according to the brand: {}", applicationContext.brand());
        var resource = resourceLoader.getResource("classpath:canvas/templates/" + applicationContext.brand() + "/context.yaml").getInputStream().readAllBytes();
        TemplateContextCollection templateContextCollection = objectMapper.readValue(resource, TemplateContextCollection.class);
        templateContextCollection.templates().forEach(canvasContext -> LOGGER.info("Template context: {} was loaded", canvasContext.name()));
        return templateContextCollection;
    }
}
