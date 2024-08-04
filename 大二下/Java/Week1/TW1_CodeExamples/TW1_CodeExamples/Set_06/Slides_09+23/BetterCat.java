public class BetterCat {
  private String name;
  private String colour;
  private int age;

  public BetterCat() { }

  public BetterCat(String name) { this.name = name; }

  /**
   *  This method gets the colour of the cat.
   *  @return String  Colour of the cat.
   */
  public String getColour() { return colour; }

  /**
   *  This method sets the name of the cat.
   *  @param name  Name that the cat should have.
   */
  public void setName(String name) { this.name = name; }

  public void meow() {
    System.out.println(name + " Meow! Meow");
  }

  public void eat() {
    System.out.println("Yummy! Yummy");
  }
} // end class BetterCat