package models;

public class Alert implements Comparable<Alert> {
    private String alertID;
    private Packet packet;
    private Signature matchedSignature;
    private int severityScore;
    private long timestamp;

    public Alert(String alertID, Packet packet, Signature matchedSignature, long timestamp) {
        this.alertID = alertID;
        this.packet = packet;
        this.matchedSignature = matchedSignature;
        this.severityScore = matchedSignature.getSeverity();
        this.timestamp = timestamp;
    }

    public String getAlertID() { return alertID; }
    public Packet getPacket() { return packet; }
    public Signature getMatchedSignature() { return matchedSignature; }
    public int getSeverityScore() { return severityScore; }
    public long getTimestamp() { return timestamp; }

    @Override
    public int compareTo(Alert other) {
        return Integer.compare(other.severityScore, this.severityScore);
    }

    @Override
    public String toString() {
        return "[ALERT " + alertID + "] Severity: " + severityScore + " | " + matchedSignature.getThreatType() + " | " + packet.getSourceIP();
    }
}
