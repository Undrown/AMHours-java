import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Represents one entry of server DB
 */
public class Entry {
    public String uid;
    public Integer eid;
    public Long timeStart;
    public Long timeEnd;
    public String comment;

    /**
     * Construct Entry from JSON-string
     *
     * @param json JSON-string
     *             <br>
     *             -- ex:<br>
     *             """<br>
     *             {<br>
     *              "uid":"5ec6a7148c87a",<br>
     *              "time_start":"1",<br>
     *              "comment":"test",<br>
     *              "time_end":"100",<br>
     *              "time_add":"1590078562"<br>
     *              }<br>
     *              """
     */
    public Entry(String json){
        JSONParser parser = new JSONParser();
        Object obj = new Object();
        try {
            obj = parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObj = (JSONObject)obj;
        this.uid = jsonObj.get("uid").toString();
        this.comment = jsonObj.get("comment").toString();
        try{
            this.timeStart = Long.parseLong(jsonObj.get("time_start").toString());
            this.timeEnd = Long.parseLong(jsonObj.get("time_end").toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.timeStart = 0L;
            this.timeEnd = 0L;
        }
    }

    public Entry(String uid, Long timeStart, Long timeEnd, String comment){
        this.uid = uid;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "\nEntry{" +
                "uid='" + uid + '\'' +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", comment='" + comment + '\'' +
                '}';
    }
}
