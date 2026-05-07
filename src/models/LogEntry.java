package models;

public class LogEntry {
    private String entryID;
    private long timestamp;
    private String eventType;
    private String description;
    private Packet relatedPacket;

    public LogEntry(String entryID, long timestamp, String eventType, String description, Packet relatedPacket) {
        this.entryID = entryID;
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.description = description;
        this.relatedPacket = relatedPacket;
    }

    public String getEntryID() { return entryID; }
    public long getTimestamp() { return timestamp; }
    public String getEventType() { return eventType; }
    public String getDescription() { return description; }
    public Packet getRelatedPacket() { return relatedPacket; }

    @Override
    public String toString() {
        return "[LOG " + entryID + "] " + eventType + " | " + description;
    }
}