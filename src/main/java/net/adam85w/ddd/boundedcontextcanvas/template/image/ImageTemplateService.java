package net.adam85w.ddd.boundedcontextcanvas.template.image;

import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.Template;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.stereotype.Service;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
class ImageTemplateService implements TemplateService<ByteArrayOutputStream> {

    private static final int WIDTH = 1600;

    private final TemplateService<String> htmlTemplateService;

    private final Tidy tidy;

    ImageTemplateService(TemplateService<String> htmlTemplateService, Tidy tidy) {
        this.htmlTemplateService = htmlTemplateService;
        this.tidy = tidy;
    }

    @Override
    public Template<ByteArrayOutputStream> generate(String name, BoundedContext boundedContext)  {
        try {
            Template<String> template = htmlTemplateService.generate(name, boundedContext);
            InputStream htmlStream = new ByteArrayInputStream(template.content().getBytes(StandardCharsets.UTF_8));
            org.w3c.dom.Document doc = tidy.parseDOM(new InputStreamReader(htmlStream, StandardCharsets.UTF_8), null);
            Java2DRenderer renderer = new Java2DRenderer(doc, WIDTH);
            BufferedImage img = renderer.getImage();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, "png", outputStream);
            return new Template<>(name, outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
