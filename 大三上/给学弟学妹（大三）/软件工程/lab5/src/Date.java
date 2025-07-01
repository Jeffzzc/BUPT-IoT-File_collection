public class Date {
    private int year;
    private int month;
    private int day;
    
    public void setYear(int year) {
        this.year = year;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setDay(int day) {
        this.day = day;
    }


    public int getYear() {
        return year;
    }
    public Date () {

    }
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "DateOfBirth [year=" + year + ", month=" + month + ", day=" + day + "]";
    }
}
