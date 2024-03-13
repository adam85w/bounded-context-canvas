package net.adam85w.ddd.boundedcontextcanvas.api.confleunce;

import jakarta.validation.Valid;
import net.adam85w.ddd.boundedcontextcanvas.api.TemplateNameConstraint;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bounded-context-canvas/confluence")
@ConditionalOnProperty(value = "application.brand", havingValue = "default")
class ConfluenceController {

    private final TemplateService<String> confluenceTemplateService;

    ConfluenceController(TemplateService<String> confluenceTemplateService) {
        this.confluenceTemplateService = confluenceTemplateService;
    }

    @PostMapping
    public ResponseEntity<String> generate(@RequestParam(name = "templateName", defaultValue = "modern")
                                           @TemplateNameConstraint(templateType = TemplateType.CONFLUENCE) final String templateName,
                                           @RequestBody @Valid final BoundedContext boundedContext)  {
        return ResponseEntity.ok(confluenceTemplateService.generate(templateName, boundedContext).content());
    }
}
