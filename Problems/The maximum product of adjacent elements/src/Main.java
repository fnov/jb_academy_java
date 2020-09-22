import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int multi = 0;
        int size = Integer.parseInt(scanner.nextLine().trim());
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

        for (int i = size - 1; i > 0; i--) {
            multi = Math.max(multi, numbers[i] * numbers[i - 1]);
        }

        System.out.println(multi);
    }
}