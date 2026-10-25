/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chaimaoueslati
 */
//Represents the common behaviour shared by all recipe types.
public abstract class Recipe {
    

    private RecipeCategory category;
    private String  name;
    private List<Ingredient> ingredients  = new ArrayList<>();
    private List<String> instructions  = new ArrayList<>();
    private double prepTime;
    private char prepTimeUnit;
    
    
    public Recipe(String name,RecipeCategory category){
        this.name=name;
        this.category=category;
    }
    
    // Adds a new Ingredient object to this recipe.
    public void addIngredient(String name,double quantity,String unit){
        Ingredient ingredient=new Ingredient(name,quantity,unit);
        ingredients.add(ingredient);
    }
    
    // Removes an ingredient by matching its name, quantity, and unit.
    public boolean removeIngredient(String name, double quantity, String unit){
        
        for(int i=0;i<ingredients.size();i++){
            Ingredient o=ingredients.get(i);
            
            if( (o.getName().toUpperCase().equals(name.toUpperCase())) && (o.getQuantity() ==quantity) && (o.getUnit().toUpperCase().equals(unit.toUpperCase())) ) {
                
                ingredients.remove(i);
                return true;
                
            }
        }
        return false;
        
    }
    
    
    
    public void addInstruction(String instruction){
        instructions.add(instruction);
    }
    public void removeInstruction(int stepNumber){
        instructions.remove(stepNumber-1);
    }
    
    
    public RecipeCategory getCategory(){
        return this.category;
    }
    public String getName(){
        return this.name;
    }
    
    // Returns a copy of the instructions list to protect the original list.
    public List<String> getInstructions(){
        return new ArrayList<>(this.instructions);
    }
    
    // Returns a copy of the ingredients list to protect the original list.
    public List<Ingredient> getIngredients(){
        return new ArrayList<>(this.ingredients);
    }
    
    
    public void setName(String name){
        this.name = name;
    }
    public void setCategory(RecipeCategory category){
        if(category==null){
            throw new IllegalArgumentException("Category can not be null.");
        }
        this.category=category;
    }
    
    // Validates and sets the preparation time and its unit.
    public void setPrepDetails(double prepTime, char prepTimeUnit){
        
        if(prepTime<=0){
            throw new IllegalArgumentException("Prep time must be greater than 0.");
        }
        
        
        if( (Character.toUpperCase(prepTimeUnit)!='M') &&(Character.toUpperCase(prepTimeUnit)!='H')){
            throw new IllegalArgumentException("Prep time unit must be ('M' or 'm' or 'H' or 'h').");
        }
        this.prepTime=prepTime;
        this.prepTimeUnit=Character.toUpperCase(prepTimeUnit);
    }
    
    public double getPrepTime(){
        return this.prepTime;
    }
    
    public char getPrepTimeUnit(){
        return this.prepTimeUnit;
    }
    
    // Calculates the total recipe duration in minutes.
    public abstract double getTotalTime();
    
    // Returns the recipe type used for saving and loading.
    public abstract String getRecipeType();
    
    // Returns a formatted String representation of the recipe.
    @Override
    public abstract String toString();      

    
 
}
