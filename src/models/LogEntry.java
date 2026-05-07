package models;

/**
 * Represents a single entry in the system audit trail.
 * Each log entry captures an event type, description, timestamp,
 * and the packet associated with the event.
 */
public class LogEntry {
    private String entryID;
    private long timestamp;
    private String eventType;
    private String description;
    private Packet relatedPacket;

    /**
     * Constructs a new LogEntry with the given details.
     *
     * @param entryID       Unique identifier for the log entry
     * @param timestamp     Time the event was recorded
     * @param eventType     Type of event (PACKET, ALERT, FLAG, SCAN)
     * @param description   Human-readable description of the event
     * @param relatedPacket The packet associated with this event (nullable)
     */
    public LogEntry(String entryID, long timestamp, String eventType, String description, Packet relatedPacket) {
        this.entryID = entryID;
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.description = description;
        this.relatedPacket = relatedPacket;
    }

    /**
     * Returns the entry ID.
     * @return entryID
     */
    public String getEntryID() { return entryID; }

    /**
     * Returns the timestamp of the log entry.
     * @return timestamp
     */
    public long getTimestamp() { return timestamp; }

    /**
     * Returns the event type.
     * @return eventType
     */
    public String getEventType() { return eventType; }

    /**
     * Returns the description of the event.
     * @return description
     */
    public String getDescription() { return description; }

    /**
     * Returns the packet related to this log entry.
     * @return relatedPacket
     */
    public Packet getRelatedPacket() { return relatedPacket; }

    /**
     * Returns a formatted string representation of the log entry.
     * @return String summary of the log entry
     */
    @Override
    public String toString() {
        return "[LOG " + entryID + "] " + eventType + " | " + description;
    }
}