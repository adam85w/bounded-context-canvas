package net.adam85w.ddd.boundedcontextcanvas.template.conflunece;

import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.Template;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.stereotype.Service;

@Service
class ConfluenceTemplateService implements TemplateService<String> {

    private final static String BODY_TAG_START = "<body>";

    private final static String BODY_TAG_END = "</body>";

    private final TemplateService<String> htmlTemplateService;

    ConfluenceTemplateService(TemplateService<String> htmlTemplateService) {
        this.htmlTemplateService = htmlTemplateService;
    }

    @Override
    public Template<String> generate(String name, BoundedContext boundedContext) {
        Template<String> html = htmlTemplateService.generate(name, boundedContext);
        return  new Template<String>(name, obtainBodyContent(html.content()));
    }

    private String obtainBodyContent(String content) {
        if (!content.contains(BODY_TAG_START) || !content.contains(BODY_TAG_END)) {
            throw new RuntimeException("Invalid html format or any error parsing occurred.");
        }
        return content.substring(content.indexOf(BODY_TAG_START)+BODY_TAG_START.length(), content.indexOf(BODY_TAG_END));
    }
}
