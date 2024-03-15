package net.adam85w.ddd.boundedcontextcanvas.api.markdown;

import jakarta.validation.Valid;
import net.adam85w.ddd.boundedcontextcanvas.api.TemplateNameConstraint;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bounded-context-canvas/markdown")
@ConditionalOnProperty(value = "application.brand", havingValue = "default")
class MarkdownController {

    private final TemplateService<String> markdownTemplateService;

    MarkdownController(TemplateService<String> markdownTemplateService) {
        this.markdownTemplateService = markdownTemplateService;
    }

    @PostMapping
    public ResponseEntity<String> generate(@RequestParam(name = "templateName", defaultValue = "document")
                                           @TemplateNameConstraint(templateType = TemplateType.MARKDOWN) final String templateName,
                                           @RequestBody @Valid final BoundedContext boundedContext)  {
        return ResponseEntity.ok(markdownTemplateService.generate(templateName, boundedContext).content());
    }
}
