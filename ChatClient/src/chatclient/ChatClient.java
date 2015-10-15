/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
    public static void main(String[] args) {

        System.out.println("Welcome in chat client.\n"
                + "What's your name??\n");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        System.out.println("Enter command(for help user \"help\"):\n");
        while (true) {
            String command = sc.nextLine();
            command = command.toLowerCase();
            switch (command) {

                case "login":
                    boolean login = login(name);
                    if (login) {
                        System.out.println("You are logged sucessfully");
                    } else {
                        System.out.println("Sorry cant log in.");
                    }
                    break;
                case "logout":
                    boolean logout = logout(name);
                    if (logout) {
                        System.out.println("You are logged sucessfully");
                    } else {
                        System.out.println("Sorry cant log in.");
                    }
                    break;
                case "send":
                    System.out.println("Enter message:\n");
                    boolean isSent = send(name, sc.nextLine());
                    if (isSent) {
                        String msgs = getMessages(name);
                        System.out.println(msgs);
                    } else {
                        System.out.println("Sorry cant send message");
                    }
                    break;
                case "":
                    String msgs = getMessages(name);
                    System.out.println(msgs);
                    break;
                case "exit":
                    System.out.println("Bye bye");
                    System.exit(0);
            }
        }

//        boolean login = login("radek");
//        System.out.println(login);
//
//        boolean msgSend = send("radek", "Toto je testovaci message");
//        System.out.println(msgSend);
//
//        String messages = getMessages("radek");
//        System.out.println(messages);
//
//        boolean logout = logout("radek");
//        System.out.println(logout);
    }

    private static boolean isResponseOk(String response) {
        return response.startsWith("OK");
    }

    private static boolean login(String user) {
        String response = doLogin(user);
        return isResponseOk(response);
    }

    private static String doLogin(String user) {
        String output = doCommand(LOGIN, user, null,
                "Nemohu se zalogovat");
        return output;
    }

    private static boolean logout(String user) {
        String response = doLogout(user);
        return isResponseOk(response);
    }

    private static String doLogout(String user) {
        String output = doCommand(LOGOUT, user, null,
                "Nemohu se odlogovat");
        return output;
    }

    private static boolean send(String user, String message) {
        String response = doSend(user, message);
        System.out.println(response);
        return isResponseOk(response);
    }

    private static String doSend(String user, String message) {
        String errMessage = "Nemohu odeslat message";
        String output;
        try {
            message = URLEncoder.encode(message, "UTF-8");
            output = doCommand(SEND, user, message,
                    errMessage);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            output = errMessage;
        }
        return output;
    }

    private static String messages(String user) {
        String response = doGetMessages(user);
        return response;
    }

    private static String doGetMessages(String user) {
        String response = doCommand(GETALL,
                user,
                null,
                "Nepodarilo se ziskat messages");
        if (isResponseOk(response)) {
            response = cleanResponse(response);
        }
        return response;
    }

    /**
     *
     * @param command
     * @param user
     * @param message
     * @param errorMessage
     * @return
     * @throws IllegalArgumentException
     */
    private static String doCommand(String command,
            String user,
            String message,
            String errorMessage)
            throws IllegalArgumentException {

        String url = "";
        switch (command) {
            case LOGIN:
            case LOGOUT:
            case GETALL:
                url = String.format(command, user);
                break;
            case SEND:
                url = String.format(command, user, message);
                break;
            default:
                throw new IllegalArgumentException("Unknown command");
        }
        String response;
        try {
            response = doRequest(url);
        } catch (IOException ex) {
            ex.printStackTrace();
            response = errorMessage;
        }
        return response;
    }

    private static String cleanResponse(String response) {
        return response
                .replaceAll("OK[\\n]", "")
                .trim();
    }

    private static boolean sendMessage(String user, String message) {
        try {
            String response = doSendMessage(user, message);
            return isResponseOk(response);
        } catch (Exception e) {
            return false;
        }
    }

    private static String getMessages(String user) {
        String response = doGetMessages(user);
        if (isResponseOk(response)) {
            response = cleanResponse(response);
        }
        return response;
    }

    private static String doSendMessage(String user, String message)
            throws UnsupportedEncodingException {
        message = URLEncoder.encode(message, "UTF-8");
        String url = String.format(SEND, user, message);
        String response;
        try {
            response = doRequest(url);
        } catch (IOException ex) {
            response = "Nemohu odeslat message";
        }
        return response;
    }

//    private static String doGetMessages(String user) {
//        String url = String.format(GETALL, user);
//        String response;
//        try {
//            response = doRequest(url);
//        } catch (IOException ex) {
//            response = "Nemohu ziskat messages";
//        }
//        return response;
//    }
//    private static String doLogin(String user) {
//        String url = String.format(LOGIN, user);
//        String response;
//        try {
//            response = doRequest(url);
//        } catch (IOException ex) {
//            response = "Nemohu se zalogovat";
//        }
//        return response;
//    }
    private static String doRequest(String url) throws IOException {

        URL cmd = new URL(url);
        URLConnection connection = cmd.openConnection();
        try (InputStream io = connection.getInputStream()) {
            String inputStreamString = new Scanner(io, "UTF-8").useDelimiter("\\A").next();
            return inputStreamString;
        }
    }

}
