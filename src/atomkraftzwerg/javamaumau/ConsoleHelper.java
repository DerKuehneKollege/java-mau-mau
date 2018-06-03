package atomkraftzwerg.javamaumau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    public static String readLine() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter something: ");

        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to read from console");
            return "";
        }
    }

}
