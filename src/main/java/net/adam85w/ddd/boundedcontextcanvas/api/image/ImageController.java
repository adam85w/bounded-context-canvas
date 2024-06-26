package net.adam85w.ddd.boundedcontextcanvas.api.image;

import jakarta.validation.Valid;
import net.adam85w.ddd.boundedcontextcanvas.api.TemplateNameConstraint;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateType;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import net.adam85w.ddd.boundedcontextcanvas.template.image.ImageConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/bounded-context-canvas/image")
@ConditionalOnProperty(value = "application.brand", havingValue = "default")
class ImageController {

    private static final String IMAGE_NAME = "bounded_context";

    private final TemplateService<ByteArrayOutputStream> imageTemplateService;

    private final String extension;

    ImageController(TemplateService<ByteArrayOutputStream> imageTemplateService, ImageConfiguration configuration) {
        this.imageTemplateService = imageTemplateService;
        this.extension = configuration.extension();
    }

    @PostMapping
    public ResponseEntity<byte[]> generate(@RequestParam(name = "templateName", defaultValue = "modern")
                                               @TemplateNameConstraint(templateType = TemplateType.IMAGE) final String templateName,
                                           @RequestBody @Valid final BoundedContext boundedContext)  {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(obtainContentType(extension));
        headers.setContentDispositionFormData(IMAGE_NAME,IMAGE_NAME + "." + extension);
        return new ResponseEntity<>(imageTemplateService.generate(templateName, boundedContext).content().toByteArray(), headers, HttpStatus.OK);
    }

    private MediaType obtainContentType(String extension) {
        return switch (extension) {
            case "png" -> MediaType.IMAGE_PNG;
            case "gif" -> MediaType.IMAGE_GIF;
            case "jpeg", "jpg" -> MediaType.IMAGE_JPEG;
            default -> MediaType.ALL;
        };
    }
}
