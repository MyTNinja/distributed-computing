import java.util.*;

class ProcessVectorClock {
    int pid;
    int size;
    int[] vectorClock;

    ProcessVectorClock(int pid, int size) {
        this.pid = pid;
        this.size = size;

        vectorClock = new int[size];
    }

    void send(ProcessVectorClock ignoredP) {
        vectorClock[pid-1]++;

        displayVectorClock();
    }

    void receive(ProcessVectorClock p) {
        vectorClock[pid-1]++;

        for(int i = 0; i<size; i++) {
            vectorClock[i] = Math.max(vectorClock[i], p.vectorClock[i]);
        }

        displayVectorClock();
    }

    void displayVectorClock() {
        System.out.print("Vector clock of P" + pid + ":\t");
        for(int i = 0; i<size; i++) {
            System.out.print(vectorClock[i] + "\t");
        }
        System.out.println();
    }
}

public class VectorClockSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int size = sc.nextInt();
        System.out.println();

        ProcessVectorClock p1 = new ProcessVectorClock(1, size);
        ProcessVectorClock p2 = new ProcessVectorClock(2, size);
        ProcessVectorClock p3 = new ProcessVectorClock(3, size);

        p1.send(p2);

        p2.receive(p1);

        p3.send(p1);
        p3.send(p2);

        p1.receive(p3);

        p2.receive(p3);

        // p2.send(p3);

        // p3.receive(p2);

        System.out.println("\nFinal vector clocks:");
        p1.displayVectorClock();
        p2.displayVectorClock();
        p3.displayVectorClock();
    }
}