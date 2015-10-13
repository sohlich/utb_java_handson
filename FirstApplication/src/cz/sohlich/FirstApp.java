package cz.sohlich;

import java.util.Scanner;

/**
 * The application should ask for your credentials and after it obtains all
 * information, it just prints out the information in formatted output.
 *
 *
 * @author Radomir Sohlich
 */
public class FirstApp {

    private static final String FORMAT = "***********\n%s\n%d\n%s\n*************";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("Ahoj jak se jmenujes?");
        System.out.println("V kterem roce jsi se narodil?");
        int yearOfBirth = sc.nextInt();
        sc.nextLine();
        System.out.println("A kde bydlis??");
        String location = sc.nextLine();

        int age = countAge(yearOfBirth);

        System.out.println("Mam vsechn tvoje udaje");
        String output = String.format(FORMAT, name, age, location);

        System.out.println(output);

    }

    private static int countAge(int yearOfBirth) {
        return 2015 - yearOfBirth;
    }

}
