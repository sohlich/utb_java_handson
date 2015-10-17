package cz.utb.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 *
 * @author radek
 */
public class Client {

    private static final String LOGIN
            = "/login?user=%s";
    private static final String SEND
            = "/send?user=%s&message=%s";
    private static final String GETALL
            = "/getall?user=%s";
    private static final String LOGOUT
            = "/logout?user=%s";

    private final String user;
    private final String baseUrl;

    public Client(String name) {
        this.user = name;
        this.baseUrl = "http://10.5.11.72:7777";
    }

    public boolean login() {
        try {
            String response = doLogin(user);
            return isResponseOk(response);
        } catch (IOException | IllegalArgumentException ex) {
            return false;
        }
    }

    private String doLogin(String user)
            throws IllegalArgumentException, IOException {
        String output = doCommand(LOGIN, user, null);
        return output;
    }

    public boolean logout() {
        try {
            String response = doLogout(user);
            return isResponseOk(response);
        } catch (IOException | IllegalArgumentException ex) {
            return false;
        }
    }

    private String doLogout(String user)
            throws IllegalArgumentException,
            IOException {
        String output = doCommand(LOGOUT, user, null);
        return output;
    }

    public boolean send(String message) {
        try {
            String response = doSend(user, message);
            return isResponseOk(response);
        } catch (IOException | IllegalArgumentException ex) {
            return false;
        }
    }

    private String doSend(String user, String message)
            throws IllegalArgumentException, IOException {
        String errMessage = "Nemohu odeslat message";
        String output;
        try {
            message = URLEncoder.encode(message, "UTF-8");
            output = doCommand(SEND, user, message);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
        return output;
    }

    public String messages() throws IllegalArgumentException, IOException {
        String response = doGetMessages(user);
        return response;
    }

    private String doGetMessages(String user)
            throws IllegalArgumentException,
            IOException {
        String response = doCommand(GETALL,
                user,
                null);
        if (isResponseOk(response)) {
            response = cleanResponse(response);
        }
        return response;
    }

    private String doCommand(String command,
            String user,
            String message)
            throws IllegalArgumentException, IOException {

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
        String response = doRequest(baseUrl + url);
        return response;
    }

    private String doRequest(String url) throws IOException {

        URL cmd = new URL(url);
        URLConnection connection = cmd.openConnection();
        try (InputStream io = connection.getInputStream()) {
            String inputStreamString = new Scanner(io, "UTF-8")
                    .useDelimiter("\\A")
                    .next();
            return inputStreamString;
        }
    }

    private String cleanResponse(String response) {
        return response
                .replaceAll("OK[\\n]", "")
                .trim();
    }

    private boolean isResponseOk(String response) {
        return response.startsWith("OK");
    }

}
