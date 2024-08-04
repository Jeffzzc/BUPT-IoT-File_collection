/**  
  * Car.java - a simple class for demonstrating the use of javadoc comments.
  * Adapted from an example at https://alvinalexander.com/java/edu/pj/pj010014.
  *
  * Changes: Renamed the original class name (Porsche) and removed the inheritance
  *          relationship; also renamed the variables and parameters used.
  *
  * @author Alvin Alexander 
  * @author Paula Fonseca
  * @version 1.1 
  */ 
public class Car {  

   private String colour; 

   /**  
    * Get the value of colour.  
    * @return A String data type.  
    */  
   public String getColour() {  
      return colour;  
   } 

   /**  
    * Set the value of color.  
    * @param newColour A variable of type String.  
    */  
   public void setColour(String newColour) {  
      colour = newColour;  
   }  
}  