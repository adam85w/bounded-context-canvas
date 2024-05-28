package net.adam85w.ddd.boundedcontextcanvas.template.image;

import net.adam85w.ddd.boundedcontextcanvas.template.*;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
class ImageTemplateService implements TemplateService<ByteArrayOutputStream> {

    private final TemplateService<String> htmlTemplateService;

    private final Tidy tidy;

    private final int width;

    private final String extension;

    ImageTemplateService(TemplateService<String> htmlTemplateService, Tidy tidy, ImageConfiguration configuration) {
        this.htmlTemplateService = htmlTemplateService;
        this.tidy = tidy;
        this.width = configuration.width();
        this.extension = configuration.extension();
    }

    @Override
    public Template<ByteArrayOutputStream> generate(String name, BoundedContext boundedContext)  {
        try {
            Template<String> template = htmlTemplateService.generate(name, boundedContext);
            InputStream htmlStream = new ByteArrayInputStream(template.content().getBytes(StandardCharsets.UTF_8));
            org.w3c.dom.Document doc = tidy.parseDOM(new InputStreamReader(htmlStream, StandardCharsets.UTF_8), null);
            Java2DRenderer renderer = new Java2DRenderer(doc, width);
            BufferedImage img = renderer.getImage();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, extension, outputStream);
            return new Template<>(name, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
