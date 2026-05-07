package models;

import java.util.ArrayList;

/**
 * Tracks all network activity associated with a single IP address.
 * Used to detect suspicious behavior such as port scanning.
 */
public class IPRecord {
    private String ipAddress;
    private int packetCount;
    private ArrayList<Integer> portsContacted;
    private long lastSeen;
    private boolean flagged;

    /**
     * Constructs a new IPRecord for the given IP address.
     *
     * @param ipAddress The IP address being tracked
     */
    public IPRecord(String ipAddress) {
        this.ipAddress = ipAddress;
        this.packetCount = 0;
        this.portsContacted = new ArrayList<>();
        this.lastSeen = 0;
        this.flagged = false;
    }

    /**
     * Returns the IP address.
     * @return ipAddress
     */
    public String getIPAddress() { return ipAddress; }

    /**
     * Returns the total number of packets sent from this IP.
     * @return packetCount
     */
    public int getPacketCount() { return packetCount; }

    /**
     * Returns the list of ports contacted by this IP.
     * @return portsContacted
     */
    public ArrayList<Integer> getPortsContacted() { return portsContacted; }

    /**
     * Returns the timestamp of the most recent packet from this IP.
     * @return lastSeen
     */
    public long getLastSeen() { return lastSeen; }

    /**
     * Returns whether this IP has been flagged as suspicious.
     * @return flagged
     */
    public boolean isFlagged() { return flagged; }

    /**
     * Increments the packet count by one.
     */
    public void incrementPacketCount() { packetCount++; }

    /**
     * Adds a port to the list if it has not already been recorded.
     * @param port The destination port to add
     */
    public void addPort(int port) { if (!portsContacted.contains(port)) portsContacted.add(port); }

    /**
     * Updates the last seen timestamp.
     * @param timestamp The timestamp of the most recent packet
     */
    public void setLastSeen(long timestamp) { lastSeen = timestamp; }

    /**
     * Sets the flagged status of this IP.
     * @param flagged True if the IP should be flagged as suspicious
     */
    public void setFlagged(boolean flagged) { this.flagged = flagged; }

    /**
     * Returns a formatted string representation of the IP record.
     * @return String summary of the IP record
     */
    @Override
    public String toString() {
        return "[IP: " + ipAddress + "] Packets: " + packetCount + " | Ports: " + portsContacted + " | Flagged: " + flagged;
    }
}