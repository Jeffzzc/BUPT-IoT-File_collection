class car{
    private String name;
    private int price;
    protected String belongs;
    public car(){

    }
    public car(String name,int price){
        this.name = name;
        this.price = price;
    }
    protected void run(){
        System.out.println(this.name + "Run at a price of " + this.price);
    }
}

public class carTest{
    public static void main(String[] args){
        car MyOwnCar = new car("Jeff",10000);
        MyOwnCar.run();
    }
}