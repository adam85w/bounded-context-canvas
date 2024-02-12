package net.adam85w.ddd.boundedcontextcanvas.template;

import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;

@FunctionalInterface
public interface TemplateService<T> {

    Template<T> generate(String name, BoundedContext boundedContext);
}
