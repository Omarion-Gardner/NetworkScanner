# NetGuard — Network Log Scanner

A network log analysis tool that scans simulated network traffic logs for threats using signature-based detection, IP tracking, and prioritized alert generation.

## Features
- **Log Parsing** — Reads network log files and builds Packet objects from each entry
- **Signature-Based Detection** — Matches packet payloads against a database of known threat patterns
- **Alert Generation** — Generates alerts when threats are detected and ranks them by severity
- **IP Tracking** — Tracks all IP activity and flags IPs that appear to be port scanning
- **Event Logging** — Maintains a full audit trail of every event processed

## Data Structures Used
- `ArrayList` — Stores packets, signatures, and alert history
- `HashMap` — Maps signatures and IP addresses for fast lookup
- `LinkedList` — Packet processing queue (FIFO)
- `Stack` — Audit trail (LIFO)
- `PriorityQueue` — Alert triage by severity

## Custom Objects
- `Packet` — Represents a single log entry
- `Signature` — Represents a threat detection rule
- `Alert` — Represents a detected threat, implements Comparable for severity sorting
- `IPRecord` — Tracks activity for a single IP address
- `LogEntry` — Represents a single audit trail entry

## How to Run
1. Clone the repository
2. Open in VS Code
3. Run `Main.java`

## Sample Output