package net.adam85w.ddd.boundedcontextcanvas.api;

import net.adam85w.ddd.boundedcontextcanvas.ApplicationContext;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime time;

    private final String appName;

    private final String appVersion;

    private final String brand;

    private final String path;

    private final String message;


    public ErrorResponse(ApplicationContext applicationContext, String path, String message) {
        this.appName = applicationContext.name();
        this.appVersion = applicationContext.version();
        this.brand = applicationContext.brand();
        this.message = message;
        this.path = path;
        this.time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getBrand() {
        return brand;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}
