package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CMInstance cm = new CMInstance(400, 540, 120, 9, 550);

        while (cm.isWorking()) {
            cm.handleInput(scanner.next());
        }
    }
}
