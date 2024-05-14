class ProcessRA {
    private final String name;
    private final int timestamp;
    private final boolean[] requests;
    private final boolean[] replies;

    public ProcessRA(String name, int timestamp, int criticalSectionTime) {
        this.name = name;
        this.timestamp = timestamp;
        this.requests = new boolean[3];
        this.replies = new boolean[3];
    }

    public void sendRequest() {
        System.out.println(name + " sends request at timestamp " + timestamp + " to other processes.");
        for (int i = 0; i < RicartAggarwalaSimulation.processes.length; i++) {
            if (i != Integer.parseInt(name.substring(1))) {
                RicartAggarwalaSimulation.processes[i].receiveRequest(Integer.parseInt(name.substring(1)));
            }
        }
    }

    public void receiveRequest(int requestingProcess) {
        requests[requestingProcess] = true;
    }

    public void enterCriticalSection() {
        System.out.println(name + " enters critical section.");
    }

    public void sendReply(int requestingProcess) {
        System.out.println(name + " sends OK to P" + requestingProcess + ".");
        replies[requestingProcess] = true;
    }

}

public class RicartAggarwalaSimulation {
    public static ProcessRA[] processes;

    public static void main(String[] args) {

        processes = new ProcessRA[]{
                new ProcessRA("P0", 8, 2),
                new ProcessRA("P1", 10, 1),
                new ProcessRA("P2", 12, 3)
        };

        processes[0].sendRequest(); // P0 sends request at timestamp 8
        processes[2].sendRequest(); // P2 sends request at timestamp 12

        processes[1].sendReply(0); // P1 sends OK to P0
        processes[2].sendReply(0); // P2 sends OK to P0

        processes[0].enterCriticalSection(); // P0 enters CS
        processes[0].sendReply(2); // P0 sends OK to P2

        processes[2].enterCriticalSection(); // P2 enters CS
    }
}
