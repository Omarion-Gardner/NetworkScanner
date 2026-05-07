package models;

/**
 * Represents a security alert generated when a packet matches a known threat signature.
 * Implements Comparable to allow alerts to be sorted and prioritized by severity score.
 */
public class Alert implements Comparable<Alert> {
    private String alertID;
    private Packet packet;
    private Signature matchedSignature;
    private int severityScore;
    private long timestamp;

    /**
     * Constructs a new Alert with the given details.
     *
     * @param alertID          Unique identifier for the alert
     * @param packet           The packet that triggered the alert
     * @param matchedSignature The signature rule that was matched
     * @param timestamp        Time the alert was generated
     */
    public Alert(String alertID, Packet packet, Signature matchedSignature, long timestamp) {
        this.alertID = alertID;
        this.packet = packet;
        this.matchedSignature = matchedSignature;
        this.severityScore = matchedSignature.getSeverity();
        this.timestamp = timestamp;
    }

    /**
     * Returns the alert ID.
     * @return alertID
     */
    public String getAlertID() { return alertID; }

    /**
     * Returns the packet that triggered the alert.
     * @return packet
     */
    public Packet getPacket() { return packet; }

    /**
     * Returns the matched signature.
     * @return matchedSignature
     */
    public Signature getMatchedSignature() { return matchedSignature; }

    /**
     * Returns the severity score of the alert.
     * @return severityScore
     */
    public int getSeverityScore() { return severityScore; }

    /**
     * Returns the timestamp of when the alert was generated.
     * @return timestamp
     */
    public long getTimestamp() { return timestamp; }

    /**
     * Compares this alert to another alert by severity score in descending order.
     * Used by PriorityQueue and the custom sorting algorithm.
     *
     * @param other The other alert to compare to
     * @return Negative if this alert has higher severity, positive if lower
     */
    @Override
    public int compareTo(Alert other) {
        return Integer.compare(other.severityScore, this.severityScore);
    }

    /**
     * Returns a formatted string representation of the alert.
     * @return String summary of the alert
     */
    @Override
    public String toString() {
        return "[ALERT " + alertID + "] Severity: " + severityScore + " | " + matchedSignature.getThreatType() + " | " + packet.getSourceIP();
    }
}