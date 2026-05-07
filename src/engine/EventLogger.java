package engine;

import models.LogEntry;
import models.Packet;
import java.util.LinkedList;
import java.util.Stack;

public class EventLogger {
    private LinkedList<Packet> packetQueue;
    private Stack<LogEntry> auditTrail;
    private int logCount;

    public EventLogger() {
        packetQueue = new LinkedList<>();
        auditTrail = new Stack<>();
        logCount = 0;
    }

    public void enqueuePacket(Packet p) {
        packetQueue.add(p);
    }

    public Packet processNextPacket() {
        return packetQueue.poll();
    }

    public void logEvent(String eventType, String description, Packet p) {
        String entryID = "LOG" + (++logCount);
        long timestamp = System.currentTimeMillis();
        LogEntry entry = new LogEntry(entryID, timestamp, eventType, description, p);
        auditTrail.push(entry);
    }

    public LogEntry peekLastEvent() {
        if (!auditTrail.isEmpty()) {
            return auditTrail.peek();
        }
        return null;
    }

    public LogEntry undoLastEvent() {
        if (!auditTrail.isEmpty()) {
            return auditTrail.pop();
        }
        return null;
    }

    public void printAuditTrail() {
        System.out.println("\n===== AUDIT TRAIL =====");
        Stack<LogEntry> temp = new Stack<>();
        temp.addAll(auditTrail);
        while (!temp.isEmpty()) {
            System.out.println(temp.pop());
        }
    }

    public boolean hasPackets() {
        return !packetQueue.isEmpty();
    }
}