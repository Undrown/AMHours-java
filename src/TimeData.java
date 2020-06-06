import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TimeData{
    String webApi = "https://anmedia-server.000webhostapp.com/include/api.php?";
    public String uid;
    public ArrayList<Entry> data;

    public TimeData(String uid){
        this.data = new ArrayList<>();
        this.uid = uid;
    }

    public void loadData() throws Exception{
        //get data from server by uid
        String request =webApi+"action=get_data&id="+this.uid;
        Scanner sc = new Scanner(new URL(request).openStream());
        StringBuilder result = new StringBuilder();
        while (sc.hasNext())
            result.append(sc.nextLine());
        JSONParser parser = new JSONParser();
        Object json = parser.parse(result.toString());
        JSONArray array = (JSONArray)json;
        for (Object obj:array){
            //для каждой записи
            JSONObject jsonObj = (JSONObject)obj;
            jsonObj.put("uid", this.uid);
            System.out.println(jsonObj);
            data.add(new Entry(jsonObj.toString()));
        }
        System.out.println(data);
    }

    public void saveData() throws Exception{
        for (Entry entry:data){
            String request = webApi + "action=add_data&id="+uid+
                    "time_start="+ entry.timeStart +
                    "time_end="+ entry.timeEnd +
                    "comment="+ entry.comment;
        }
    }
}
