package models;

public class Signature {
    private String signatureID;
    private String pattern;
    private String threatType;
    private int severity;

    public Signature(String signatureID, String pattern, String threatType, int severity) {
        this.signatureID = signatureID;
        this.pattern = pattern;
        this.threatType = threatType;
        this.severity = severity;
    }

    public String getSignatureID() { return signatureID; }
    public String getPattern() { return pattern; }
    public String getThreatType() { return threatType; }
    public int getSeverity() { return severity; }

    @Override
    public String toString() {
        return "[" + signatureID + "] " + threatType + " | Pattern: " + pattern + " | Severity: " + severity;
    }
}