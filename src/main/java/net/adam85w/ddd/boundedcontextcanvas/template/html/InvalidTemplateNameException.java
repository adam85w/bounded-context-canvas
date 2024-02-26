package net.adam85w.ddd.boundedcontextcanvas.template.html;

public class InvalidTemplateNameException extends RuntimeException {

    private static final String MESSAGE = "Template %s might not exist or might not be accessible.";

    InvalidTemplateNameException(String templateName, Throwable cause) {
        super(String.format(MESSAGE, templateName), cause);
    }
}
