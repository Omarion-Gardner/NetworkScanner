package models;

/**
 * Represents a threat detection rule used by the detection engine.
 * Each signature contains a pattern to match against packet payloads
 * and a severity score indicating the threat level.
 */
public class Signature {
    private String signatureID;
    private String pattern;
    private String threatType;
    private int severity;

    /**
     * Constructs a new Signature with the given details.
     *
     * @param signatureID Unique identifier for the signature
     * @param pattern     Keyword or string to match in packet payload
     * @param threatType  Category of threat (e.g. SQL Injection, XSS)
     * @param severity    Severity score from 1-10
     */
    public Signature(String signatureID, String pattern, String threatType, int severity) {
        this.signatureID = signatureID;
        this.pattern = pattern;
        this.threatType = threatType;
        this.severity = severity;
    }

    /**
     * Returns the signature ID.
     * @return signatureID
     */
    public String getSignatureID() { return signatureID; }

    /**
     * Returns the pattern used for matching.
     * @return pattern
     */
    public String getPattern() { return pattern; }

    /**
     * Returns the threat type category.
     * @return threatType
     */
    public String getThreatType() { return threatType; }

    /**
     * Returns the severity score.
     * @return severity
     */
    public int getSeverity() { return severity; }

    /**
     * Returns a formatted string representation of the signature.
     * @return String summary of the signature
     */
    @Override
    public String toString() {
        return "[" + signatureID + "] " + threatType + " | Pattern: " + pattern + " | Severity: " + severity;
    }
}