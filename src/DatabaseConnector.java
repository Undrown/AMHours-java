import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseConnector {
    private final String webApi = "https://anmedia-server.000webhostapp.com/include/api.php";
    private String uid;
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public DatabaseConnector(String user_id){
        uid = user_id;
    }

    public DatabaseConnector(Long phone_num){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(webApi))
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    /**
     *  Get user id from server by phone
     * @return JSON-string of user data
     */
    private String performLoginRequest(Long phone_num){
        String request =webApi+"action=login&phone="+phone_num.toString();
        StringBuilder result = new StringBuilder();
        Scanner sc = null;
        try {
            sc = new Scanner(new URL(request).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) result.append(sc.nextLine());
        return result.toString();
    }

    /**
     *  Get entries from server by uid
     * @return JSON-string of server data for specified uid
     * @throws IOException if HTTP-request failed
     */
    private String performPullRequest() throws IOException{
        String request =webApi+"action=get_data&id="+this.uid;
        StringBuilder result = new StringBuilder();
        Scanner sc = new Scanner(new URL(request).openStream());
        while (sc.hasNext()) result.append(sc.nextLine());
        return result.toString();
    }

    /**
     *  Push entry to server DB
     * @param entry Entry to save into DB
     * @return true on success
     * @throws IOException if HTTP-request failed
     */
    public boolean performPushRequest(Entry entry) throws IOException {
        return true;
    }

    /**
     * Update entry into server DB by time_add
     * @param entry
     * @throws IOException if HTTP-request failed
     */
    public boolean performUpdateRequest(Entry entry) throws IOException{
        String request = webApi + "action=update_data&time_add=" + entry.timeAdd +
                "&time_start=" + entry.timeStart +
                "&time_end=" + entry.timeEnd +
                "&comment=" + entry.comment;
        return true;
    }

    /**
     * Push data to server
     * @param data ArrayList of Entries
     * @return true if all queries successed
     * @throws InterruptedException
     */
    public boolean performAsyncPush(ArrayList<Entry> data) throws InterruptedException {
        data.forEach(entry -> {
            String url = webApi + "action=update_data&time_add=" + entry.timeAdd +
                    "&time_start=" + entry.timeStart +
                    "&time_end=" + entry.timeEnd;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(5))
                    .header("Content-Type", "application/json")
                    .build();
            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println);
        });
        Thread.sleep(2000);
        System.out.println("END");
        return true;
    }
}
