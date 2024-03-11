package net.adam85w.ddd.boundedcontextcanvas.template;

import net.adam85w.ddd.boundedcontextcanvas.template.html.InvalidTemplateFileNameException;

import java.util.Collections;
import java.util.Set;

public record TemplateContextCollection(Set<TemplateContext> templates) {

    public Set<TemplateContext> templates() {
        return Collections.unmodifiableSet(templates);
    }

    public TemplateContext getTemplateContext(String name) {
        var canvasContextOptional = templates.stream().filter(templateContext -> templateContext.name().equals(name)).findFirst();
        if (canvasContextOptional.isEmpty()) {
            throw new InvalidTemplateFileNameException(name);
        }
        return canvasContextOptional.get();
    }

    public boolean templateExists(String name) {
        return templates.stream().anyMatch(templateContext -> templateContext.name().equals(name));
    }
}
