package net.adam85w.ddd.boundedcontextcanvas.management;

import net.adam85w.ddd.boundedcontextcanvas.ApplicationContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateContext;

import java.util.Set;

record ContextInfo(ApplicationContext applicationContext, Set<TemplateContext> templates) {
}
