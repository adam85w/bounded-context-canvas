package net.adam85w.ddd.boundedcontextcanvas.management;

import net.adam85w.ddd.boundedcontextcanvas.ApplicationContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateContextCollection;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id = "context")
@Component
public class ContextInfoEndpoint {

    private final ContextInfo contextInfo;

    ContextInfoEndpoint(ApplicationContext applicationContext, TemplateContextCollection templateContextCollection) {
        contextInfo = new ContextInfo(applicationContext, templateContextCollection.templates());
    }

    @ReadOperation
    ContextInfo obtainContextInfo() {
        return contextInfo;
    }
}
