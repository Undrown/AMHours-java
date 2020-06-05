public class Entry {
    public String uid;
    public Long timeStart;
    public Long timeEnd;
    public String comment;

    public Entry(String uid, Long timeStart, Long timeEnd, String comment){
        this.uid = uid;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.comment = comment;
    }

    public Entry(String uid, String timeStart, String timeEnd, String comment){
        this.uid = uid;
        try {
            this.timeStart = Long.parseLong(timeStart);
            this.timeEnd = Long.parseLong(timeEnd);
        }catch (Exception e){
            this.timeStart = 0L;
            this.timeEnd = 0L;
        }
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
