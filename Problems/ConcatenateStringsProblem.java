/* 
Implement a method to concatenate all strings from the given array to a single long string. You must skip all digits inside the input strings.
Use StringBuilder to solve the problem, because the input array can contain a huge number of strings.

Sample Input:
T7est i1nput

Sample Output:
Testinput 
*/

import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        // write your code with StringBuilder here
        return String.join("", strings).replaceAll("[0-9]", "");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}
