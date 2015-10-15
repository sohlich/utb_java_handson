/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
        String output = doLogin("radek");
        System.out.println("Response: " + output);
    }

    private static boolean isResponseOk(String response) {
        return response.matches("^OK[\\n].*");
    }

    private static boolean login(String user) {
        String response = doLogin(user);
        return isResponseOk(response);
    }

    private static String doLogin(String user) {
        String url = String.format(LOGIN, user);
        String response;
        try {
            response = doRequest(url);
        } catch (IOException ex) {
            response = "Nemohu se zalogovat";
        }
        return response;
    }

    private static String doRequest(String url) throws IOException {
        URL cmd = new URL(url);
        URLConnection connection = cmd.openConnection();
        try (InputStream io = connection.getInputStream()) {
            String inputStreamString = new Scanner(io, "UTF-8").useDelimiter("\\A").next();
            return inputStreamString;
        }
    }

}
