/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;

/**
 *
 * @author chaimaoueslati
 */
//Represents an ingredient object with a name, quantity, and unit.
public class Ingredient {
    private String name;
    private double quantity;
    private String unit;
    
    public Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
    
    public String getName(){
        return this.name;
        
    }
    public double getQuantity(){
        return this.quantity;
    }
    public String getUnit(){
        return this.unit;
        
    }
    //Generated with assistance from ChatGPT.
    
    @Override
    public String toString(){
        return name + " " + quantity + " " + unit;
    }
}
    