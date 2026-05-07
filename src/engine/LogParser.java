package engine;

import models.Packet;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogParser {
    private ArrayList<Packet> packetLog;

    public LogParser() {
        packetLog = new ArrayList<>();
    }

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

    public Packet simulatePacket(String sourceIP, String destIP, int sourcePort, int destPort, String protocol, String payload) {
        String packetID = "PKT" + (packetLog.size() + 1);
        long timestamp = System.currentTimeMillis();
        Packet p = new Packet(packetID, sourceIP, destIP, sourcePort, destPort, protocol, payload, timestamp);
        packetLog.add(p);
        return p;
    }

    public void logPacket(Packet p) {
        packetLog.add(p);
    }

    public ArrayList<Packet> getPacketLog() {
        return packetLog;
    }
}