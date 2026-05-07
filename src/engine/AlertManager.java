package engine;

import models.Alert;
import models.Packet;
import models.Signature;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AlertManager {
    private PriorityQueue<Alert> alertQueue;
    private ArrayList<Alert> alertHistory;
    private int alertCount;

    public AlertManager() {
        alertQueue = new PriorityQueue<>();
        alertHistory = new ArrayList<>();
        alertCount = 0;
    }

    public Alert generateAlert(Packet p, Signature s) {
        String alertID = "ALT" + (++alertCount);
        long timestamp = System.currentTimeMillis();
        return new Alert(alertID, p, s, timestamp);
    }

    public void enqueueAlert(Alert a) {
        alertQueue.add(a);
        alertHistory.add(a);
    }

    public Alert processNextAlert() {
        return alertQueue.poll();
    }

    public void sortAlerts(ArrayList<Alert> alerts) {
        int n = alerts.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (alerts.get(j).compareTo(alerts.get(maxIdx)) < 0) {
                    maxIdx = j;
                }
            }
            Alert temp = alerts.get(maxIdx);
            alerts.set(maxIdx, alerts.get(i));
            alerts.set(i, temp);
        }
    }

    public void printAlertHistory() {
        sortAlerts(alertHistory);
        System.out.println("\n===== ALERT HISTORY =====");
        for (Alert a : alertHistory) {
            System.out.println(a);
        }
    }

    public ArrayList<Alert> getAlertHistory() {
        return alertHistory;
    }
}