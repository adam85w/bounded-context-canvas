package net.adam85w.ddd.boundedcontextcanvas.template;

import java.util.Set;

public record TemplateContext(String name, String author, Set<TemplateType> support) {

    public boolean isSupported(TemplateType type) {
        return support() != null && support.contains(type);
    }
}
