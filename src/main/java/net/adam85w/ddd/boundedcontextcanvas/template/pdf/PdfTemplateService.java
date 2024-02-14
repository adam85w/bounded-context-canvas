package net.adam85w.ddd.boundedcontextcanvas.template.pdf;

import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.Template;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
class PdfTemplateService implements TemplateService<ByteArrayOutputStream> {

    private final TemplateService<String> htmlTemplateService;

    PdfTemplateService(TemplateService<String> htmlTemplateService) {
        this.htmlTemplateService = htmlTemplateService;
    }

    @Override
    public Template<ByteArrayOutputStream> generate(String name, BoundedContext boundedContext) {
        Template<String> template = htmlTemplateService.generate(name, boundedContext);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(template.content());
        renderer.layout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        renderer.createPDF(outputStream);
        return new Template<>(name, outputStream);
    }
}
