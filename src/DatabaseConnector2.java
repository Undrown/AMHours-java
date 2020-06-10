import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;

public class DatabaseConnector2 {
    private final String webApi = "https://anmedia-server.000webhostapp.com/include/api.php";
    private String uid = "0";
    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    public DatabaseConnector2(Long phone_num){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(webApi))
                .POST(HttpRequest.BodyPublishers.ofString("action=login&phone=" + phone_num.toString()))
                .timeout(Duration.ofSeconds(20))
                .build();
        try {
            HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
