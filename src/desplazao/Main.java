package desplazao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static BufferedReader ipServer;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int op;

        try {
            ipServer = new BufferedReader(new FileReader("config"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Client client = new Client(ipServer.readLine(), 5558);

        System.out.println("1-Jugar\n2-Sortir");
        op = scanner.nextInt();

        switch (op) {
            case 1 -> client.start();
            case 2 -> System.exit(0);
        }
    }
}
