package net.adam85w.ddd.boundedcontextcanvas.template.markdown;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.Template;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.stereotype.Service;

@Service
class MarkdownTemplateService implements TemplateService<String> {

    private final TemplateService<String> htmlTemplateService;

    MarkdownTemplateService(TemplateService<String> htmlTemplateService) {
        this.htmlTemplateService = htmlTemplateService;
    }

    @Override
    public Template<String> generate(String name, BoundedContext boundedContext) {
        final String html = htmlTemplateService.generate(name, boundedContext).content();
        return  new Template<String>(name, FlexmarkHtmlConverter.builder().build().convert(html));
    }
}
