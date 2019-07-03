package v.systems.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class HttpClient {

    public static String get(String url) throws IOException {

        HttpURLConnection con = null;

        try {
            URL webURL = new URL(url);
            if (webURL.getProtocol().equals("https")) {
                con = (HttpsURLConnection) webURL.openConnection();
            } else {
                con = (HttpURLConnection) webURL.openConnection();
            }
            con.setRequestMethod("GET");
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            return content.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    public static String post(String url, String json) throws IOException {

        HttpURLConnection con = null;
        byte[] postData = json.getBytes(StandardCharsets.UTF_8);

        try {
            URL webURL = new URL(url);
            con = (HttpURLConnection) webURL.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            return content.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
