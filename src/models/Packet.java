package models;

/**
 * Represents a single network log entry (packet).
 * Stores all relevant information about a packet captured from the network log.
 */
public class Packet {
    private String packetID;
    private String sourceIP;
    private String destIP;
    private int sourcePort;
    private int destPort;
    private String protocol;
    private String payload;
    private long timestamp;

    /**
     * Constructs a new Packet with the given details.
     *
     * @param packetID   Unique identifier for the packet
     * @param sourceIP   IP address of the sender
     * @param destIP     IP address of the receiver
     * @param sourcePort Port the packet was sent from
     * @param destPort   Port the packet was directed to
     * @param protocol   Protocol used (TCP, UDP, ICMP, etc.)
     * @param payload    Content/body of the packet
     * @param timestamp  Time the packet was captured
     */
    public Packet(String packetID, String sourceIP, String destIP, int sourcePort, int destPort, String protocol, String payload, long timestamp) {
        this.packetID = packetID;
        this.sourceIP = sourceIP;
        this.destIP = destIP;
        this.sourcePort = sourcePort;
        this.destPort = destPort;
        this.protocol = protocol;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    /**
     * Returns the packet ID.
     * @return packetID
     */
    public String getPacketID() { return packetID; }

    /**
     * Returns the source IP address.
     * @return sourceIP
     */
    public String getSourceIP() { return sourceIP; }

    /**
     * Returns the destination IP address.
     * @return destIP
     */
    public String getDestIP() { return destIP; }

    /**
     * Returns the source port.
     * @return sourcePort
     */
    public int getSourcePort() { return sourcePort; }

    /**
     * Returns the destination port.
     * @return destPort
     */
    public int getDestPort() { return destPort; }

    /**
     * Returns the protocol used.
     * @return protocol
     */
    public String getProtocol() { return protocol; }

    /**
     * Returns the packet payload.
     * @return payload
     */
    public String getPayload() { return payload; }

    /**
     * Returns the timestamp of when the packet was captured.
     * @return timestamp
     */
    public long getTimestamp() { return timestamp; }

    /**
     * Returns a formatted string representation of the packet.
     * @return String summary of the packet
     */
    @Override
    public String toString() {
        return "[" + packetID + "] " + sourceIP + ":" + sourcePort + " -> " + destIP + ":" + destPort + " | " + protocol + " | " + payload;
    }
}