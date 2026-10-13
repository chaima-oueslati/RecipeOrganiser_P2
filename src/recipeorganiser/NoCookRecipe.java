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
//Represents a recipe that does not require cooking.
public class NoCookRecipe extends Recipe{
    
    public NoCookRecipe(String name, RecipeCategory category){
        super(name,category);
    }
    
    private double chillTime=0;
    private char chillTimeUnit;
    
    public double getChillTime(){
        return this.chillTime;
    }
    public char getChillTimeUnit(){
        return this.chillTimeUnit;
    }
    
    // Validates and sets the chill time and its unit.
    public void setChillTimeDetails(double chillTime, char chillTimeUnit){
        
        if(chillTime<0){
            throw new IllegalArgumentException("Chill time can not be a negative value.");
        }
        
        if( Character.toUpperCase(chillTimeUnit)!='M' && Character.toUpperCase(chillTimeUnit)!='H'){
            throw new IllegalArgumentException("Chill time unit must be ('M' or 'm' or 'H' or 'h').");
        }
        
        this.chillTime =chillTime;
        this.chillTimeUnit=Character.toUpperCase(chillTimeUnit);
        
    }
    
    // Calculates the total recipe time in minutes, including prep and chill time.
    // Can be used by future features that need the overall recipe duration.
    @Override
    public double getTotalTime(){
        
        double prepTimeInMinute=getPrepTime();
        if(getPrepTimeUnit()=='H'){
            prepTimeInMinute=prepTimeInMinute*60;
        }
        double chillTimeInMinute=chillTime;
        if(chillTimeUnit=='H'){
            chillTimeInMinute=chillTimeInMinute*60;
        }
        
        return chillTimeInMinute+prepTimeInMinute;
    }
    
    // Returns the recipe type.
    @Override
    public String getRecipeType(){
        return "NO_COOK";
    }
    
    // Formats all NoCookRecipe details for console output.
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
        
        
        result="Type: No Cooking.\n"+
                "Name: "+getName()+
                ".\nCategory: "+getCategory()+
                ".\nPrep Time and Unit = "+getPrepTime()+" "+getPrepTimeUnit()+
                
                ".\nChill Time and Unit = "+chillTime+" "+chillTimeUnit+
                ".\n\nIngredients:\n"+ingredientList+
                "\n\n Instructions:\n"+instructionList;
                
                
        return result;
                
        
    }
    
}
