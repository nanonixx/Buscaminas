package desplazao;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int op;

        Client client = new Client(5557, "224.0.22.116");

        do {
            System.out.println("1-Jugar\n2-Sortir");
            op = scanner.nextInt();

            switch (op) {
                case 1 -> client.RunClient();
                case 2 -> System.exit(0);
            }
        }while(op != 2);
    }
}
