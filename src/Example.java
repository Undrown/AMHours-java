public class Example {
    public static void main(String[] args) {
        TimeData data = new TimeData("5ec6a7148c87a");
        try {
            data.loadData();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
