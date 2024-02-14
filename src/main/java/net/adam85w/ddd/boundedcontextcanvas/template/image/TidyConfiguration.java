package net.adam85w.ddd.boundedcontextcanvas.template.image;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.tidy.Tidy;

@Configuration
class TidyConfiguration {

    @Bean
    Tidy tidy() {
        Tidy tidy = new Tidy();
        tidy.setQuiet(true);
        tidy.setOnlyErrors(true);
        return tidy;
    }
}
