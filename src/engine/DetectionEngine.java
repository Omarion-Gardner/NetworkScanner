package engine;

import models.Packet;
import models.Signature;
import java.util.ArrayList;
import java.util.HashMap;

public class DetectionEngine {
    private ArrayList<Signature> signatureList;
    private HashMap<String, Signature> signatureMap;

    public DetectionEngine() {
        signatureList = new ArrayList<>();
        signatureMap = new HashMap<>();
    }

    public void loadSignatures() {
        addSignature(new Signature("SIG001", "SELECT", "SQL Injection", 9));
        addSignature(new Signature("SIG002", "DROP TABLE", "SQL Injection", 10));
        addSignature(new Signature("SIG003", "<script>", "Cross-Site Scripting", 8));
        addSignature(new Signature("SIG004", "USER anonymous", "Anonymous FTP Login", 6));
        addSignature(new Signature("SIG005", "exec", "Command Execution", 9));
    }

    public void addSignature(Signature s) {
        signatureList.add(s);
        signatureMap.put(s.getPattern(), s);
    }

    public Signature matchSignature(Packet p) {
        for (String pattern : signatureMap.keySet()) {
            if (p.getPayload().contains(pattern)) {
                return signatureMap.get(pattern);
            }
        }
        return null;
    }

    public void printSignatures() {
        for (Signature s : signatureList) {
            System.out.println(s);
        }
    }
}