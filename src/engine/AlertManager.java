package engine;

import models.Alert;
import models.Packet;
import models.Signature;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Manages the generation, queuing, sorting, and reporting of security alerts.
 * Uses a PriorityQueue to triage alerts by severity and an ArrayList to maintain history.
 * Implements a custom selection sort algorithm using the Alert Comparable interface.
 */
public class AlertManager {
    private PriorityQueue<Alert> alertQueue;
    private ArrayList<Alert> alertHistory;
    private int alertCount;

    /**
     * Constructs a new AlertManager with empty queue and history.
     */
    public AlertManager() {
        alertQueue = new PriorityQueue<>();
        alertHistory = new ArrayList<>();
        alertCount = 0;
    }

    /**
     * Creates and returns a new Alert from a packet and matched signature.
     *
     * @param p The packet that triggered the alert
     * @param s The signature that was matched
     * @return A new Alert object
     */
    public Alert generateAlert(Packet p, Signature s) {
        String alertID = "ALT" + (++alertCount);
        long timestamp = System.currentTimeMillis();
        return new Alert(alertID, p, s, timestamp);
    }

    /**
     * Adds an alert to both the priority queue and the alert history list.
     *
     * @param a The alert to enqueue
     */
    public void enqueueAlert(Alert a) {
        alertQueue.add(a);
        alertHistory.add(a);
    }

    /**
     * Retrieves and removes the highest severity alert from the queue.
     *
     * @return The highest priority Alert, or null if the queue is empty
     */
    public Alert processNextAlert() {
        return alertQueue.poll();
    }

    /**
     * Custom selection sort implementation that sorts alerts by severity in descending order.
     * Uses the Comparable interface defined in the Alert class.
     *
     * @param alerts The list of alerts to sort
     */
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

    /**
     * Sorts and prints all alerts in the history list by severity.
     */
    public void printAlertHistory() {
        sortAlerts(alertHistory);
        System.out.println("\n===== ALERT HISTORY =====");
        for (Alert a : alertHistory) {
            System.out.println(a);
        }
    }

    /**
     * Returns the full alert history list.
     *
     * @return ArrayList of all alerts
     */
    public ArrayList<Alert> getAlertHistory() {
        return alertHistory;
    }
}