import java.util.Scanner;
public class week3 {

    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke!\n" + "How may I help you today?");
        Scanner response = new Scanner (System.in);
        String line = response.nextLine();

        while (!(line.equals("bye"))) {
            System.out.println(line);
            line = response.nextLine();
        }
        System.out.println("Bye! Have a nice day:D");
    }
}
