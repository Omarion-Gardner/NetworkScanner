package models;

public class Packet {
    private String packetID;
    private String sourceIP;
    private String destIP;
    private int sourcePort;
    private int destPort;
    private String protocol;
    private String payload;
    private long timestamp;

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

    public String getPacketID() { return packetID; }
    public String getSourceIP() { return sourceIP; }
    public String getDestIP() { return destIP; }
    public int getSourcePort() { return sourcePort; }
    public int getDestPort() { return destPort; }
    public String getProtocol() { return protocol; }
    public String getPayload() { return payload; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + packetID + "] " + sourceIP + ":" + sourcePort + " -> " + destIP + ":" + destPort + " | " + protocol + " | " + payload;
    }
}