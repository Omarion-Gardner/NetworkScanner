import engine.AlertManager;
import engine.DetectionEngine;
import engine.EventLogger;
import engine.IPTracker;
import engine.LogParser;
import models.Alert;
import models.Packet;
import models.Signature;

/**
 * Entry point for the NetGuard Network Log Scanner.
 * Orchestrates the full pipeline: parsing, detection, alerting, IP tracking, and logging.
 */
public class Main {

    /**
     * Main method that runs the NetGuard scanner.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {

        // Initialize all engines
        LogParser parser = new LogParser();
        DetectionEngine detection = new DetectionEngine();
        AlertManager alertManager = new AlertManager();
        IPTracker ipTracker = new IPTracker();
        EventLogger logger = new EventLogger();

        // Load signatures
        detection.loadSignatures();
        System.out.println("===== LOADED SIGNATURES =====");
        detection.printSignatures();

        // Parse the log file
        parser.parseLog("logs/sample_log.txt");
        System.out.println("\n===== PARSED PACKETS =====");
        for (Packet p : parser.getPacketLog()) {
            System.out.println(p);
            logger.enqueuePacket(p);
        }

        // Process each packet
        System.out.println("\n===== PROCESSING PACKETS =====");
        while (logger.hasPackets()) {
            Packet p = logger.processNextPacket();

            // Track IP activity
            ipTracker.trackIP(p);

            // Check for port scan
            if (ipTracker.detectPortScan(ipTracker.getIPRecord(p.getSourceIP()), 3)) {
                ipTracker.flagIP(p.getSourceIP());
                logger.logEvent("FLAG", "Port scan detected from " + p.getSourceIP(), p);
            }

            // Match against signatures
            Signature match = detection.matchSignature(p);
            if (match != null) {
                Alert alert = alertManager.generateAlert(p, match);
                alertManager.enqueueAlert(alert);
                logger.logEvent("ALERT", "Threat detected: " + match.getThreatType(), p);
                System.out.println("Threat detected: " + alert);
            } else {
                logger.logEvent("PACKET", "Packet processed: " + p.getSourceIP(), p);
            }
        }

        // Print results
        alertManager.printAlertHistory();
        ipTracker.printIPRecords();
        logger.printAuditTrail();
    }
}