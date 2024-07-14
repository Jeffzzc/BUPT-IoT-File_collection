public class WeekDayConverter{
    public static void main(String[] args){
        int weekDay = Integer.parseInt(args[0]);
        switch(weekDay){
            case 1:
                System.out.println("The 1st day of the week is Monday");
                break;
            case 2:
                System.out.println("The 2nd day of the week is Tuesday");
                break;
            case 3:
                System.out.println("The 3rd day of the week is Wednesday");
                break;
            case 4:
                System.out.println("The 4th day of the week is Thursday");
                break;
            case 5:
                System.out.println("The 5th day of the week is Friday");
                break;
            case 6:
                System.out.println("The 6th day of the week is Saturday");
                break;
            case 7:
                System.out.println("The 7th day of the week is Sunday");
                break;
            }
        }
}