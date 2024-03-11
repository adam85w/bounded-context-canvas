package net.adam85w.ddd.boundedcontextcanvas.template.html;

public class InvalidTemplateFileNameException extends RuntimeException {

    private static final String MESSAGE = "Canvas file %s might not exist or might not be accessible.";

    public InvalidTemplateFileNameException(String canvasName, Throwable cause) {
        super(String.format(MESSAGE, canvasName), cause);
    }

    public InvalidTemplateFileNameException(String canvasName) {
        super(String.format(MESSAGE, canvasName));
    }
}
