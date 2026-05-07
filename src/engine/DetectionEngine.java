package engine;

import models.Packet;
import models.Signature;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages the signature database and performs threat detection on packets.
 * Uses an ArrayList to store all signatures and a HashMap for fast pattern lookup.
 */
public class DetectionEngine {
    private ArrayList<Signature> signatureList;
    private HashMap<String, Signature> signatureMap;

    /**
     * Constructs a new DetectionEngine with empty signature structures.
     */
    public DetectionEngine() {
        signatureList = new ArrayList<>();
        signatureMap = new HashMap<>();
    }

    /**
     * Loads predefined threat signatures into the signature list and map.
     */
    public void loadSignatures() {
        addSignature(new Signature("SIG001", "SELECT", "SQL Injection", 9));
        addSignature(new Signature("SIG002", "DROP TABLE", "SQL Injection", 10));
        addSignature(new Signature("SIG003", "<script>", "Cross-Site Scripting", 8));
        addSignature(new Signature("SIG004", "USER anonymous", "Anonymous FTP Login", 6));
        addSignature(new Signature("SIG005", "exec", "Command Execution", 9));
    }

    /**
     * Adds a new signature to both the list and the map.
     *
     * @param s The Signature to add
     */
    public void addSignature(Signature s) {
        signatureList.add(s);
        signatureMap.put(s.getPattern(), s);
    }

    /**
     * Checks a packet's payload against all loaded signatures.
     *
     * @param p The packet to inspect
     * @return The matched Signature, or null if no match is found
     */
    public Signature matchSignature(Packet p) {
        for (String pattern : signatureMap.keySet()) {
            if (p.getPayload().contains(pattern)) {
                return signatureMap.get(pattern);
            }
        }
        return null;
    }

    /**
     * Prints all loaded signatures to the console.
     */
    public void printSignatures() {
        for (Signature s : signatureList) {
            System.out.println(s);
        }
    }
}