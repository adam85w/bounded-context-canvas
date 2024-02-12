package net.adam85w.ddd.boundedcontextcanvas.model.communication;

public enum MessageType {
    REQUEST("Request"),
    QUERY("Query"),
    EVENT("Event");

    private final String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
