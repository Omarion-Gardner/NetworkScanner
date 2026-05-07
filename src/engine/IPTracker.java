package engine;

import models.IPRecord;
import models.Packet;
import java.util.HashMap;

public class IPTracker {
    private HashMap<String, IPRecord> ipTracker;

    public IPTracker() {
        ipTracker = new HashMap<>();
    }

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

    public boolean detectPortScan(IPRecord record, int threshold) {
        return record.getPortsContacted().size() >= threshold;
    }

    public void flagIP(String ip) {
        if (ipTracker.containsKey(ip)) {
            ipTracker.get(ip).setFlagged(true);
        }
    }

    public IPRecord getIPRecord(String ip) {
        return ipTracker.get(ip);
    }

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