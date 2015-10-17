/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import cz.utb.chat.Client;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Radomir Sohlich
 */
public class ChatClient {

    private static final String LOGIN
            = "http://10.5.11.72:7777/login?user=%s";
    private static final String SEND
            = "http://10.5.11.72:7777/send?user=%s&message=%s";
    private static final String GETALL
            = "http://10.5.11.72:7777/getall?user=%s";
    private static final String LOGOUT
            = "http://10.5.11.72:7777/logout?user=%s";

    private static String name = "";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalArgumentException, IOException {

        System.out.println("Welcome in chat client.\n"
                + "What's your name??\n");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        Client client = new Client(name);
        System.out.println("Enter command(for help user \"help\"):\n");
        while (true) {
            String command = sc.nextLine();
            command = command.toLowerCase();
            switch (command) {

                case "login":
                    boolean login = client.login();
                    if (login) {
                        System.out.println("You are logged sucessfully");
                    } else {
                        System.out.println("Sorry cant log in.");
                    }
                    break;
                case "logout":
                    boolean logout = client.logout();
                    if (logout) {
                        System.out.println("You are logged sucessfully");
                    } else {
                        System.out.println("Sorry cant log in.");
                    }
                    break;
                case "send":
                    System.out.println("Enter message:\n");
                    boolean isSent = client.send(sc.nextLine());
                    if (isSent) {
                        String msgs = client.messages();
                        System.out.println(msgs);
                    } else {
                        System.out.println("Sorry cant send message");
                    }
                    break;
                case "":
                    String msgs = client.messages();
                    System.out.println(msgs);
                    break;
                case "exit":
                    System.out.println("Bye bye");
                    System.exit(0);
            }
        }
    }

}
