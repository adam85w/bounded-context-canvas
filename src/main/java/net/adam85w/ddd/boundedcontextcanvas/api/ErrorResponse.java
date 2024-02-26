package net.adam85w.ddd.boundedcontextcanvas.api;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime time;

    private final String appName;

    private final String appVersion;

    private final String path;

    private final String message;


    public ErrorResponse(String appName, String appVersion, String path, String message) {
        this.appName = appName;
        this.appVersion = appVersion;
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

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}
