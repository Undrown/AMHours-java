import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Calendar;

/**
 * Represents one entry of server DB
 */
public class Entry {
    public String uid;
    public Integer eid;
    public Long timeAdd;
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
     *              "time_add":"1",<br>
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
            this.timeAdd = Long.parseLong(jsonObj.get("time_add").toString());
            this.timeStart = Long.parseLong(jsonObj.get("time_start").toString());
            this.timeEnd = Long.parseLong(jsonObj.get("time_end").toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.timeStart = 0L;
            this.timeEnd = 0L;
            this.timeAdd = 0L;
        }
    }

    /**
     * Construct Entry from values
     * @param uid           user id
     * @param timeStart     interval start
     * @param timeEnd       interval end
     * @param comment       comment
     */
    public Entry(String uid, Long timeStart, Long timeEnd, String comment){
        this.uid = uid;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.comment = comment;
    }

    /**
     * Calculate salary for this interval
     * using rules
     * @param rate rate per hour
     * @return salary
     */
    public Double getSalary(Double rate){
        Double result = 0.0;
        Calendar calendar = Calendar.getInstance();
        return 0.0;
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
