/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;

import java.util.List;

/**
 *
 * @author chaimaoueslati
 */
//Represents a recipe that is cooked on a stove top.
public class StoveTopRecipe extends Recipe{
    
    public StoveTopRecipe(String name, RecipeCategory category){
        super(name,category);
    }
    private HeatLevel heatLevel;
    private double cookingTime;
    private char cookingTimeUnit;
    
    // Validates and sets the stove-top cooking details.
    public void setStoveTopRecipeDetails(HeatLevel heatLevel, double cookingTime, char cookingTimeUnit){
        if(heatLevel==null){
            throw new IllegalArgumentException("Heat level can not be null.");
        }
        
        
        if(cookingTime<=0){
            throw new IllegalArgumentException("Cooking time must be greater than 0.");
        }
        
        
        if((Character.toUpperCase(cookingTimeUnit)!='M')&&(Character.toUpperCase(cookingTimeUnit)!='H')){
            throw new IllegalArgumentException("Time unit must be ('M' or 'm' or 'H' or 'h').");
        }
        
        this.heatLevel = heatLevel;
        this.cookingTime = cookingTime;
        this.cookingTimeUnit=Character.toUpperCase(cookingTimeUnit);
    }
    
    public double getCookingTime(){
        return this.cookingTime;
    }
    public char getTimeUnit(){
        return this.cookingTimeUnit;
    }
    
    public HeatLevel getHeatLevel(){
        return this.heatLevel;
    }
    
    // Calculates the total recipe time in minutes.
    // Can support future features that use the overall recipe duration.
    @Override
    public double getTotalTime(){
        
        double prepTimeInMinute=getPrepTime();
        if(getPrepTimeUnit()=='H'){
            prepTimeInMinute=prepTimeInMinute*60;
        }
        double cookingTimeInMinute=cookingTime;
        if(cookingTimeUnit=='H'){
            cookingTimeInMinute=cookingTimeInMinute*60;
        }
        
        return cookingTimeInMinute+prepTimeInMinute;
    }
    
    // Returns the recipe type used when saving and loading recipes.
    @Override
    public String getRecipeType(){
        return "STOVE_TOP";
    }
    
    // Formats all StoveTopRecipe details for console output.
    @Override
    public String toString(){
        
        String result ;
        String ingredientList="";
        String instructionList="";
        List<String>instructions=getInstructions();
        List<Ingredient>ingredients=getIngredients();
        for(Ingredient ingredient:ingredients){
            ingredientList+=ingredient+".\n";
        }
        int i=1;
        for(String instruction:instructions){
            instructionList+=i+"."+instruction+".\n";
            i++;
        }
        
        
        result="Type: Stove Top.\n"+
                "Name: "+getName()+
                ".\nCategory: "+getCategory()+
                ".\nPrep Time and Unit = "+getPrepTime()+" "+getPrepTimeUnit()+
                ".\nHeat Level = "+heatLevel+
                ".\nCooking Time and Unit = "+cookingTime+" "+cookingTimeUnit+
                ".\n\nIngredients:\n"+ingredientList+
                "\n\n Instructions:\n"+instructionList;
                
                
        return result;
                
        
    }
    
    
}
