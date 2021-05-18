package desplazao;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int op;

        Client client = new Client("192.168.22.113", 5558);

        System.out.println("1-Jugar\n2-Sortir");
        op = scanner.nextInt();

        switch (op) {
            case 1 -> client.start();
            case 2 -> System.exit(0);
        }
    }
}
