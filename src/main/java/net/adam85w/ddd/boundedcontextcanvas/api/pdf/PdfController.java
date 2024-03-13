package net.adam85w.ddd.boundedcontextcanvas.api.pdf;

import jakarta.validation.Valid;
import net.adam85w.ddd.boundedcontextcanvas.api.TemplateNameConstraint;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateType;
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
@RequestMapping("/api/v2/bounded-context-canvas/pdf")
@ConditionalOnProperty(value = "application.brand", havingValue = "default")
class PdfController {

    private static final String PDF_NAME = "bounded_context";

    private static final String PDF_EXTENSION = ".pdf";

    private final TemplateService<ByteArrayOutputStream> pdfTemplateService;

    PdfController(TemplateService<ByteArrayOutputStream> pdfTemplateService) {
        this.pdfTemplateService = pdfTemplateService;
    }

    @PostMapping
    public ResponseEntity<byte[]> generate(@RequestParam(name = "templateName", defaultValue = "modern")
                                               @TemplateNameConstraint(templateType = TemplateType.PDF) final String templateName,
                                           @RequestBody @Valid final BoundedContext boundedContext)  {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(PDF_NAME, PDF_NAME + PDF_EXTENSION);
        return new ResponseEntity<>(pdfTemplateService.generate(templateName, boundedContext).content().toByteArray(), headers, HttpStatus.OK);
    }
}
