package net.adam85w.ddd.boundedcontextcanvas.api.image;

import jakarta.validation.Valid;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/v1/bounded-context-canvas/image")
class ImageController {

    private static final String TEMPLATE_NAME = "basic";

    private static final String IMAGE_NAME = "bounded_context";

    private static final String IMAGE_EXTENSION = ".png";

    private final TemplateService<ByteArrayOutputStream> imageTemplateService;

    ImageController(TemplateService<ByteArrayOutputStream> imageTemplateService) {
        this.imageTemplateService = imageTemplateService;
    }

    @PostMapping
    public ResponseEntity<byte[]> generate(@RequestBody @Valid final BoundedContext boundedContext)  {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentDispositionFormData(IMAGE_NAME, IMAGE_NAME + IMAGE_EXTENSION);
        return new ResponseEntity<>(imageTemplateService.generate(TEMPLATE_NAME, boundedContext).content().toByteArray(), headers, HttpStatus.OK);
    }
}
