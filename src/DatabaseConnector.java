import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class DatabaseConnector {
    private final String webApi = "https://anmedia-server.000webhostapp.com/include/api.php?";
    private String uid;

    public DatabaseConnector(String user_id){
        uid = user_id;
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
     * Update entry into server DB by uid & eid
     * @param entry
     * @throws IOException if HTTP-request failed
     */
    public void performUpdateRequest(Entry entry) throws IOException{

    }
}
