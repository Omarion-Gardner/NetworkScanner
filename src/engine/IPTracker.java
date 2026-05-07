package engine;

import models.IPRecord;
import models.Packet;
import java.util.HashMap;

/**
 * Tracks network activity for all IP addresses seen in the log.
 * Detects potential port scanning behavior based on the number of unique ports contacted.
 */
public class IPTracker {
    private HashMap<String, IPRecord> ipTracker;

    /**
     * Constructs a new IPTracker with an empty IP record map.
     */
    public IPTracker() {
        ipTracker = new HashMap<>();
    }

    /**
     * Updates or creates an IPRecord for the source IP of the given packet.
     *
     * @param p The packet to track
     */
    public void trackIP(Packet p) {
        String ip = p.getSourceIP();
        if (!ipTracker.containsKey(ip)) {
            ipTracker.put(ip, new IPRecord(ip));
        }
        IPRecord record = ipTracker.get(ip);
        record.incrementPacketCount();
        record.addPort(p.getDestPort());
        record.setLastSeen(p.getTimestamp());
    }

    /**
     * Checks if an IP has contacted more unique ports than the given threshold.
     *
     * @param record    The IPRecord to check
     * @param threshold The minimum number of ports to trigger a port scan flag
     * @return True if the number of unique ports exceeds the threshold
     */
    public boolean detectPortScan(IPRecord record, int threshold) {
        return record.getPortsContacted().size() >= threshold;
    }

    /**
     * Marks the given IP address as suspicious.
     *
     * @param ip The IP address to flag
     */
    public void flagIP(String ip) {
        if (ipTracker.containsKey(ip)) {
            ipTracker.get(ip).setFlagged(true);
        }
    }

    /**
     * Returns the IPRecord for the given IP address.
     *
     * @param ip The IP address to look up
     * @return The corresponding IPRecord, or null if not found
     */
    public IPRecord getIPRecord(String ip) {
        return ipTracker.get(ip);
    }

    /**
     * Prints all tracked IP records and flags any detected port scanners.
     */
    public void printIPRecords() {
        System.out.println("\n===== IP RECORDS =====");
        for (IPRecord record : ipTracker.values()) {
            System.out.println(record);
            if (detectPortScan(record, 3)) {
                System.out.println("  ⚠ Port scan detected from " + record.getIPAddress());
            }
        }
    }
}