package engine;

import models.LogEntry;
import models.Packet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Manages the packet processing pipeline and maintains a full audit trail of system events.
 * Uses a LinkedList as a FIFO queue for incoming packets and a Stack as a LIFO audit trail.
 */
public class EventLogger {
    private LinkedList<Packet> packetQueue;
    private Stack<LogEntry> auditTrail;
    private int logCount;

    /**
     * Constructs a new EventLogger with an empty queue and audit trail.
     */
    public EventLogger() {
        packetQueue = new LinkedList<>();
        auditTrail = new Stack<>();
        logCount = 0;
    }

    /**
     * Adds a packet to the end of the processing queue.
     *
     * @param p The packet to enqueue
     */
    public void enqueuePacket(Packet p) {
        packetQueue.add(p);
    }

    /**
     * Retrieves and removes the next packet from the front of the queue.
     *
     * @return The next Packet, or null if the queue is empty
     */
    public Packet processNextPacket() {
        return packetQueue.poll();
    }

    /**
     * Creates a new LogEntry and pushes it onto the audit trail stack.
     *
     * @param eventType   The type of event (PACKET, ALERT, FLAG, SCAN)
     * @param description A description of the event
     * @param p           The packet associated with the event
     */
    public void logEvent(String eventType, String description, Packet p) {
        String entryID = "LOG" + (++logCount);
        long timestamp = System.currentTimeMillis();
        LogEntry entry = new LogEntry(entryID, timestamp, eventType, description, p);
        auditTrail.push(entry);
    }

    /**
     * Returns the most recent log entry without removing it from the stack.
     *
     * @return The top LogEntry, or null if the stack is empty
     */
    public LogEntry peekLastEvent() {
        if (!auditTrail.isEmpty()) {
            return auditTrail.peek();
        }
        return null;
    }

    /**
     * Removes and returns the most recent log entry from the stack.
     *
     * @return The top LogEntry, or null if the stack is empty
     */
    public LogEntry undoLastEvent() {
        if (!auditTrail.isEmpty()) {
            return auditTrail.pop();
        }
        return null;
    }

    /**
     * Prints all log entries from most recent to oldest.
     */
    public void printAuditTrail() {
        System.out.println("\n===== AUDIT TRAIL =====");
        Stack<LogEntry> temp = new Stack<>();
        temp.addAll(auditTrail);
        while (!temp.isEmpty()) {
            System.out.println(temp.pop());
        }
    }

    /**
     * Returns true if there are packets waiting in the queue.
     *
     * @return True if the queue is not empty
     */
    public boolean hasPackets() {
        return !packetQueue.isEmpty();
    }
}