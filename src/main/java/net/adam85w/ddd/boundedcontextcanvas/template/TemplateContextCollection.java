package net.adam85w.ddd.boundedcontextcanvas.template;

import net.adam85w.ddd.boundedcontextcanvas.template.html.InvalidTemplateFileNameException;

import java.util.Collections;
import java.util.Set;

public record TemplateContextCollection(Set<TemplateContext> templates) {

    public Set<TemplateContext> templates() {
        return Collections.unmodifiableSet(templates);
    }

    public TemplateContext getTemplateContext(String name) {
        return templates.stream()
                .filter(templateContext -> templateContext.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new InvalidTemplateFileNameException(name));
    }

    public boolean templateExists(String name) {
        return templates.stream().anyMatch(templateContext -> templateContext.name().equals(name));
    }
}
