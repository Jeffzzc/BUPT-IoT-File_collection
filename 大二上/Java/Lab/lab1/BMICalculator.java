public class BMICalculator{
    public static void main(String[] args){
        double weight = Double.parseDouble(args[0]);
        double height = Double.parseDouble(args[1]);
        height /= 100;

        System.out.println("Your weight: " + weight + " kg");
		System.out.println("Your height: " + height + " m");

        double BMI = weight / (height * height);
        System.out.printf("Your BMI: %.2f\n", BMI);
        
        if(BMI < 18.5){
            System.out.println("Your are in the Underweight range");
        }
        else if (BMI < 24.9) {
			System.out.println("Your are in the Normal range");
		}
        else if (BMI < 29.9) {
			System.out.println("Your are in the Overweight range");
		}
        else {
			System.out.println("Your are in the Obese range");
		}
    }
}