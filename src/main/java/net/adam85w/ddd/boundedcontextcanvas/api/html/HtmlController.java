package net.adam85w.ddd.boundedcontextcanvas.api.html;

import jakarta.validation.Valid;
import net.adam85w.ddd.boundedcontextcanvas.model.BoundedContext;
import net.adam85w.ddd.boundedcontextcanvas.template.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bounded-context-canvas/html")
class HtmlController {

    private final TemplateService<String> htmlTemplateService;

    HtmlController(TemplateService<String> htmlTemplateService) {
        this.htmlTemplateService = htmlTemplateService;
    }

    @PostMapping
    public ResponseEntity<String> generate(@RequestParam(name = "templateName", defaultValue = "default") final String templateName, @RequestBody @Valid final BoundedContext boundedContext)  {
        return ResponseEntity.ok(htmlTemplateService.generate(templateName, boundedContext).content());
    }
}
