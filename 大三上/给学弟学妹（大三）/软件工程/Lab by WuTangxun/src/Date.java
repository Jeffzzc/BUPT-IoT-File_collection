public class Date{
    private int year;
    private int month;
    private int day;
    private String date;

    public Date(){
    }
    public Date(String date){
        this.date = date;
        year = Integer.parseInt(date.substring(0, 4));
        month = Integer.parseInt(date.substring(5, 7));
        day = Integer.parseInt(date.substring(8, 10));
    }

    public int getYear() {
        return this.year;
    }
    public int getMonth() {
        return this.month;
    }
    public int getDay() {
        return this.day;
    }
    public int getTime(){
        return (this.year*365+this.month*30+this.day*1);
    }
    public String getDate(){
        return (date); // eg. 2020.09.19
    }
}
