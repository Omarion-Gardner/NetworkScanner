package engine;

import models.Packet;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles reading and parsing of network log files.
 * Converts each line of the log into a Packet object and stores them in a list.
 */
public class LogParser {
    private ArrayList<Packet> packetLog;

    /**
     * Constructs a new LogParser with an empty packet log.
     */
    public LogParser() {
        packetLog = new ArrayList<>();
    }

    /**
     * Reads a log file and parses each line into a Packet object.
     * Each line should be formatted as: sourceIP | destIP | destPort | protocol | payload
     *
     * @param filename Path to the log file
     */
    public void parseLog(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int id = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String packetID = "PKT" + id++;
                    String sourceIP = parts[0].trim();
                    String destIP = parts[1].trim();
                    int destPort = Integer.parseInt(parts[2].trim());
                    String protocol = parts[3].trim();
                    String payload = parts[4].trim();
                    long timestamp = System.currentTimeMillis();
                    Packet p = new Packet(packetID, sourceIP, destIP, 0, destPort, protocol, payload, timestamp);
                    packetLog.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading log file: " + e.getMessage());
        }
    }

    /**
     * Manually creates and adds a Packet to the log for testing purposes.
     *
     * @param sourceIP  Source IP address
     * @param destIP    Destination IP address
     * @param sourcePort Source port
     * @param destPort  Destination port
     * @param protocol  Protocol used
     * @param payload   Packet payload
     * @return The created Packet object
     */
    public Packet simulatePacket(String sourceIP, String destIP, int sourcePort, int destPort, String protocol, String payload) {
        String packetID = "PKT" + (packetLog.size() + 1);
        long timestamp = System.currentTimeMillis();
        Packet p = new Packet(packetID, sourceIP, destIP, sourcePort, destPort, protocol, payload, timestamp);
        packetLog.add(p);
        return p;
    }

    /**
     * Adds an existing Packet to the packet log.
     *
     * @param p The packet to add
     */
    public void logPacket(Packet p) {
        packetLog.add(p);
    }

    /**
     * Returns the full list of parsed packets.
     *
     * @return ArrayList of Packet objects
     */
    public ArrayList<Packet> getPacketLog() {
        return packetLog;
    }
}