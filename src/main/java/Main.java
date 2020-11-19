import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class Main {

    public static void main(String[] args) throws IOException {
        makeGetRequest();
        makePostRequest();
    }

    public static void makeGetRequest(){
        URL myURL = null;
        try {
            myURL = new URL("http://week5-6eliasrabbat.herokuapp.com/patients");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) myURL.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(myURL.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine = "";
        // Read the body of the response
        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makePostRequest() throws MalformedURLException, IOException{
        String message = "Hello servlet";
        byte[] body = message.getBytes(StandardCharsets.UTF_8);
        URL myURL = new URL("http://localhost:8080/MyServlet/patients");
        HttpURLConnection conn = null;
        conn = (HttpURLConnection) myURL.openConnection();
        // Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
        conn.setDoOutput(true);
        // Write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }
        BufferedReader bufferedReader = new BufferedReader(new
                InputStreamReader(conn.getInputStream(), "utf-8"));
        String inputLine;
        // Read the body of the response
        while ((inputLine = bufferedReader.readLine()) != null) {
            System.out.println(inputLine);
        }
        bufferedReader.close();

    }

}
