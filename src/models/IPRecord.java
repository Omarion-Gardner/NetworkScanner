package models;

import java.util.ArrayList;

public class IPRecord {
    private String ipAddress;
    private int packetCount;
    private ArrayList<Integer> portsContacted;
    private long lastSeen;
    private boolean flagged;

    public IPRecord(String ipAddress) {
        this.ipAddress = ipAddress;
        this.packetCount = 0;
        this.portsContacted = new ArrayList<>();
        this.lastSeen = 0;
        this.flagged = false;
    }

    public String getIPAddress() { return ipAddress; }
    public int getPacketCount() { return packetCount; }
    public ArrayList<Integer> getPortsContacted() { return portsContacted; }
    public long getLastSeen() { return lastSeen; }
    public boolean isFlagged() { return flagged; }

    public void incrementPacketCount() { packetCount++; }
    public void addPort(int port) { if (!portsContacted.contains(port)) portsContacted.add(port); }
    public void setLastSeen(long timestamp) { lastSeen = timestamp; }
    public void setFlagged(boolean flagged) { this.flagged = flagged; }

    @Override
    public String toString() {
        return "[IP: " + ipAddress + "] Packets: " + packetCount + " | Ports: " + portsContacted + " | Flagged: " + flagged;
    }
}