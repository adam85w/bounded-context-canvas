package net.adam85w.ddd.boundedcontextcanvas.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import net.adam85w.ddd.boundedcontextcanvas.ApplicationContext;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final String appName;

    private final String appVersion;

    private final String brand;

    private final String path;

    private final String message;

    private final LocalDateTime time;

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public ErrorResponse(String appName, String appVersion, String brand, String path, String message, LocalDateTime time) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.brand = brand;
        this.message = message;
        this.path = path;
        this.time = time;
    }

    public ErrorResponse(ApplicationContext applicationContext, String path, String message) {
        this(applicationContext.name(), applicationContext.version(), applicationContext.brand(), path, message, LocalDateTime.now());
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

    public LocalDateTime getTime() {
        return time;
    }
}
