package net.adam85w.ddd.boundedcontextcanvas.api.pdf;

import jakarta.validation.Valid;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/v1/bounded-context-canvas/pdf")
@ConditionalOnProperty(value = "application.enable-api", havingValue = "original")
class PdfController {

    private static final String TEMPLATE_NAME = "original/basic";

    private static final String PDF_NAME = "bounded_context";

    private static final String PDF_EXTENSION = ".pdf";

    private final TemplateService<ByteArrayOutputStream> pdfTemplateService;

    PdfController(TemplateService<ByteArrayOutputStream> pdfTemplateService) {
        this.pdfTemplateService = pdfTemplateService;
    }

    @PostMapping
    public ResponseEntity<byte[]> generate(@RequestBody @Valid final BoundedContext boundedContext)  {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(PDF_NAME, PDF_NAME + PDF_EXTENSION);
        return new ResponseEntity<>(pdfTemplateService.generate(TEMPLATE_NAME, boundedContext).content().toByteArray(), headers, HttpStatus.OK);
    }
}
