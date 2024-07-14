public class Flower {
  private String petalColour;
  private double height;
 
  public Flower() { }

  public Flower(String petalColour, double height) {
    this.petalColour = petalColour;
    this.height = height;

    // this.setPetalColour(petalColour);
    // this.setHeight(height);
  }

  /** 
   *  This method sets the petalColour of a Flower.    
   *  @param petalColour  The colour of the petals.
   */
  public void setPetalColour(String petalColour) { 
    this.petalColour = petalColour; 
  }

   /** This method gets the petalColour of a Flower.    
    *  @return The colour of the petals.
    */
  public String getPetalColour() { return this.petalColour; }
   
  public void setHeight(double height) {
    if ((height > 0) && (height < 4.7)) {
       this.height = height;
    }
    else {
      System.out.println("Invalid height. Height must be between 0 and 4.7");
    }
  }
   
  public double getHeight() { return this.height; }
}