package net.adam85w.ddd.boundedcontextcanvas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApplicationContextInitializer  {

    @Bean
    ApplicationContext createApplicationContext(@Value("${application.name}") String applicationName, @Value("${application.version}") String applicationVersion) {
        return new ApplicationContext(applicationName, applicationVersion);
    }
}
