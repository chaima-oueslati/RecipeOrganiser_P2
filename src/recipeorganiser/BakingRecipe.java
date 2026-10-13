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
//Represents a recipe that requires baking in an oven.
public class BakingRecipe extends Recipe{
    
    public BakingRecipe(String name, RecipeCategory category){
        super(name,category);
    }    
    
    private int ovenTemperature;
    private char temperatureUnit;
    private double bakingTime;
    private char bakingTimeUnit;
    private OvenMode mode;
    
    // Validates and sets the baking-specific details.
    public void setBakingDetails(int ovenTemperature,char temperatureUnit,double bakingTime,char bakingTimeUnit,OvenMode mode){
        if(ovenTemperature<=0){
            throw new IllegalArgumentException("Oven temperature must be greater than 0.");
        }
        if( ((Character.toUpperCase(temperatureUnit)!='C' )&& (Character.toUpperCase(temperatureUnit)!='F') )){
            throw new IllegalArgumentException("Temperature unit must be ('F' or 'f' or 'C' or 'c').");
        }
        
        
        if( ((Character.toUpperCase(bakingTimeUnit)!='M' ) &&  (Character.toUpperCase(bakingTimeUnit)!='H') )){
            throw new IllegalArgumentException("Time unit must be ('M' or 'm' or 'H' or 'h').");
        }
        
        if(bakingTime<=0){
            throw new IllegalArgumentException("Baking time must be greater than 0.");
        }
        
        if(mode ==null){
            throw new IllegalArgumentException("Oven mode can not be null.");
        }
        
        this.ovenTemperature=ovenTemperature;
        this.temperatureUnit=Character.toUpperCase(temperatureUnit);
        this.bakingTimeUnit=Character.toUpperCase(bakingTimeUnit);
        this.bakingTime=bakingTime;
        this.mode=mode;
                
    }
    
    public int getOvenTemperature(){
        return ovenTemperature;
    }
    
    public char getTemperatureUnit(){
        return temperatureUnit;
    }
    
    public double getBakingTime(){
        return bakingTime;  
    }
    
    public char getBakingTimeUnit(){
        return bakingTimeUnit;
    }
    public OvenMode getMode(){
        return mode;
    }
    
    // Calculates the total recipe time in minutes.
    // Can support future features that use the overall recipe duration.
    @Override
    public double getTotalTime(){
        
        double prepTimeInMinute=getPrepTime();
        if(getPrepTimeUnit()=='H'){
            prepTimeInMinute=prepTimeInMinute*60;
        }
        double bakingTimeInMinute=bakingTime;
        if(bakingTimeUnit=='H'){
            bakingTimeInMinute=bakingTimeInMinute*60;
        }
        
        return bakingTimeInMinute+prepTimeInMinute;
    }
    
    // Returns the recipe type used when saving and loading recipes.
    @Override
    public String getRecipeType(){
        return "BAKING";
    }
    
    // Formats all BakingRecipe details for console output.
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
        
        
        
        
        result="Type: Baking.\n"+
                "Name: "+getName()+
                ".\nCategory: "+getCategory()+
                ".\nPrep Time and Unit = "+getPrepTime()+" "+getPrepTimeUnit()+
                ".\nOven Temperature and Unit = "+ovenTemperature+" "+temperatureUnit+
                ".\nBaking Time and Unit = "+bakingTime+" "+bakingTimeUnit+
                ".\nOven Mode: "+mode+
                ".\n\nIngredients:\n"+ingredientList+
                "\n\n Instructions:\n"+instructionList;
                
                
                
                
                
        return result;
                
        
    }
    
    
}
