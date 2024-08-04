public class CatTestClass {
  public static void main(String[] args) {
    Cat myCat = new Cat("Fluffy"); // make a Cat
    // myCat.name = "Fluffy"; // giving the cat a name
    System.out.print(myCat.name + " ");
    myCat.meow();          // making the cat meow

    Cat anotherCat = new Cat("Catty"); // make another Cat
    // anotherCat.name = "Catty"; // giving the cat a name
    System.out.print(anotherCat.name + " ");
    anotherCat.eat();          // making the cat eat

    myCat.age = 5;         // give the cat an age
    // System.out.println("Cat's age = " + myCat.age);
  }
}  //end class CatTestClass