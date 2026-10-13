/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *
 * @author chaimaoueslati
 */

 // Handles reading recipes from and writing recipes to the text file.
public class RecipeFileManager {
    
    // Saves all recipes from the RecipeManager to the recipe text file.
    public void saveRecipes(RecipeManager recipes){
        
        try{
            FileOutputStream fw = new FileOutputStream("./resources/recipes.txt");
            try (PrintWriter pw = new PrintWriter(fw)) {
                for(Recipe recipe: recipes.viewRecipes()){
                    
                    if(recipe instanceof BakingRecipe){
                        BakingRecipe bakingRecipe =(BakingRecipe) recipe;
                        writeBakingRecipe(pw,bakingRecipe);
                        
                    }else if(recipe instanceof StoveTopRecipe){
                        
                        StoveTopRecipe stoveTopRecipe =(StoveTopRecipe) recipe;
                        writeStoveTopRecipe(pw, stoveTopRecipe);
                        
                    }else if(recipe instanceof NoCookRecipe){
                        
                        NoCookRecipe noCookRecipe =(NoCookRecipe) recipe;
                        writeNoCookRecipe(pw,noCookRecipe);
                        
                    }
                    
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
            
    }
    // Loads recipe data from the text file and rebuilds Recipe objects.
    public void loadRecipes(RecipeManager recipes){
        try{
            FileReader r = new FileReader("./resources/recipes.txt");
            try (BufferedReader reader = new BufferedReader(r)) {
                Recipe currentRecipe=null;
                RecipeCategory category = null;
                String type = null;
                String name = null;
                double prepTime = 0;
                char prepUnit = ' ';
                int ovenTemperature=0;
                char temperatureUnit=' ';
                double bakingTime=0;
                char bakingTimeUnit=' ';
                OvenMode mode=null;
                HeatLevel heatLevel=null;
                double cookingTime=0;
                char cookingTimeUnit=' ';
                double chillTime=-1;
                char chillTimeUnit=' ';
                
                boolean readingIngredients = false;
                boolean readingInstructions = false;
                

                String line=reader.readLine();
                
                while(line!=null){
                    
                    if(line.trim().isEmpty()){
                        line=reader.readLine();
                        continue;
                    }
                    
                    if(line.startsWith("Type:")){
                        
                        type=readValue(line).toLowerCase();
                        
                    }else if(line.startsWith("Name:")){
                        
                        name=readValue(line);
                    }else if(line.startsWith("Category:")){
                        
                        category=RecipeCategory.valueOf(readValue(line).toUpperCase());
                    }else if(line.startsWith("Prep Time:")){
                        
                        prepTime=Double.parseDouble(readValue(line));
                    }else if(line.startsWith("Prep Unit:")){
                        
                        prepUnit=readValue(line).charAt(0);
                        if(currentRecipe == null){
                            throw new IllegalArgumentException();
                        }

                        currentRecipe.setPrepDetails(prepTime, prepUnit);
                    }else if(line.startsWith("Oven Temperature:")){
                        
                        ovenTemperature= Integer.parseInt(readValue(line));
                    }else if(line.startsWith("Temperature Unit:")){
                        
                        temperatureUnit=readValue(line).charAt(0);
                    }else if(line.startsWith("Baking Time:")){
                        
                        bakingTime = Double.parseDouble(readValue(line));
                    }else if(line.startsWith("Baking Time Unit:")){
                        
                        bakingTimeUnit=readValue(line).charAt(0);
                    }else if(line.startsWith("Oven Mode:")){
                        
                        mode=OvenMode.valueOf(readValue(line).toUpperCase());
                        
                        if(ovenTemperature!=0 && temperatureUnit!=' ' && bakingTime!=0 && bakingTimeUnit!=' ' && mode!=null){
                            
                            if (currentRecipe instanceof BakingRecipe) {
                                BakingRecipe bakingRecipe = (BakingRecipe) currentRecipe;
                                bakingRecipe.setBakingDetails(ovenTemperature,temperatureUnit,bakingTime,bakingTimeUnit,mode);
                            }
                            
                        }
                    }else if(line.startsWith("Heat Level:")){
                        

                        heatLevel=HeatLevel.valueOf(readValue(line).toUpperCase());
                    }else if(line.startsWith("Cooking Time:")){
                        
                        cookingTime=Double.parseDouble(readValue(line));
                    }else if(line.startsWith("Cooking Time Unit:")){
                        
                        cookingTimeUnit=readValue(line).charAt(0);
                        if( heatLevel!=null && cookingTime!=0 && cookingTimeUnit!=' '){
                            
                            if(currentRecipe instanceof StoveTopRecipe){
                                
                                StoveTopRecipe stoveTopRecipe=(StoveTopRecipe) currentRecipe;
                                stoveTopRecipe.setStoveTopRecipeDetails(heatLevel, cookingTime, cookingTimeUnit);
                            }
                        }
                    }else if(line.startsWith("Chill Time:")){
                        
                        chillTime=Double.parseDouble(readValue(line));
                    }else if(line.startsWith("Chill Time Unit:")){
                        
                        
                        chillTimeUnit=readValue(line).charAt(0);
                        if(chillTime>-1 && chillTimeUnit!=' '){
                            
                            if(currentRecipe instanceof NoCookRecipe ){
                                
                                NoCookRecipe noCookRecipe =(NoCookRecipe)currentRecipe;
                                noCookRecipe.setChillTimeDetails(chillTime, chillTimeUnit);
                            }
                        }
                        
                    }else if(line.startsWith("Ingredients:")){
                        
                        readingIngredients = true;
                    }else if(line.startsWith("Instructions:")){
                        
                        readingInstructions=true;
                        readingIngredients=false;
                    }else if(line.startsWith("END_RECIPE")){
                        if(currentRecipe == null){
                            throw new IllegalArgumentException();
                        }
                        
                        recipes.addRecipe(currentRecipe);
                        
                        // Reset temporary values before reading the next recipe.
                        currentRecipe=null;
                        category = null;
                        type = null;
                        name = null;
                        prepTime = 0;
                        prepUnit = ' ';
                        ovenTemperature=0;
                        temperatureUnit=' ';
                        bakingTime=0;
                        bakingTimeUnit=' ';
                        mode=null;
                        heatLevel=null;
                        cookingTime=0;
                        cookingTimeUnit=' ';
                        chillTime=-1;
                        chillTimeUnit=' ';
                        
                        readingIngredients = false;
                        readingInstructions = false;
                    }else if(readingIngredients==true){
                        
                        readIngredient(line,currentRecipe);
                        
                    }else if(readingInstructions==true){
                        
                        readInstruction(line,currentRecipe);
                    }
                    
                    
                    if(type!=null && name!=null && category!=null && currentRecipe==null){
                        RecipeFactory recipeFactory= new RecipeFactory();
                        currentRecipe= recipeFactory.createRecipe(name, type, category);
                        
                        if(currentRecipe == null){
                            throw new IllegalArgumentException();
                        }
                    }
                    
                    
                    line=reader.readLine();
                }
            }
            
            
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        catch(IOException e){
            System.out.println("Error reading from file " );
        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid data found in recipe file.");
        }
    }
    
    
    // Writes the fields of a BakingRecipe to the text file.
    private void writeBakingRecipe(PrintWriter pw, BakingRecipe bakingRecipe){
        
        pw.println("Type: "+bakingRecipe.getRecipeType());
        pw.println("Name: "+bakingRecipe.getName());
        pw.println("Category: "+bakingRecipe.getCategory());
        pw.println("Prep Time: "+bakingRecipe.getPrepTime());
        pw.println("Prep Unit: "+bakingRecipe.getPrepTimeUnit());
        pw.println("Oven Temperature: "+bakingRecipe.getOvenTemperature());
        pw.println("Temperature Unit: "+bakingRecipe.getTemperatureUnit());
        pw.println("Baking Time: "+bakingRecipe.getBakingTime());
        pw.println("Baking Time Unit: "+bakingRecipe.getBakingTimeUnit());
        pw.println("Oven Mode: "+bakingRecipe.getMode());
        pw.println();
        
        pw.println("Ingredients: ");
        for(Ingredient ingredient:bakingRecipe.getIngredients()){
            pw.println(ingredient.getName()+" | "+ingredient.getQuantity()+" | "+ingredient.getUnit());
        }
        pw.println();
        pw.println("Instructions: ");
        pw.println();
        int i=1;
        for(String instruction:bakingRecipe.getInstructions()){
            pw.println(i+") "+instruction);
            i++;
                        
        }
        pw.println("END_RECIPE");
        pw.println();
                       
    }
    
    // Writes the fields of a StoveTopRecipe to the text file.
    private void writeStoveTopRecipe(PrintWriter pw, StoveTopRecipe stoveTopRecipe){
        
        pw.println("Type: "+stoveTopRecipe.getRecipeType());
        pw.println("Name: "+stoveTopRecipe.getName());
        pw.println("Category: "+stoveTopRecipe.getCategory());
        pw.println("Prep Time: "+stoveTopRecipe.getPrepTime());
        pw.println("Prep Unit: "+stoveTopRecipe.getPrepTimeUnit());
        pw.println("Heat Level: "+stoveTopRecipe.getHeatLevel());
        pw.println("Cooking Time: "+stoveTopRecipe.getCookingTime());
        pw.println("Cooking Time Unit: "+stoveTopRecipe.getTimeUnit());
        pw.println();
                    
        pw.println("Ingredients: ");
        for(Ingredient ingredient:stoveTopRecipe.getIngredients()){
            pw.println(ingredient.getName()+" | "+ingredient.getQuantity()+" | "+ingredient.getUnit());
        }
        pw.println();
        pw.println("Instructions: ");
        pw.println();
        int i=1;
        for(String instruction:stoveTopRecipe.getInstructions()){
            pw.println(i+") "+instruction);
            i++;
        }
        pw.println("END_RECIPE");            
        pw.println();
                        
    }
    // Writes the fields of a NoCookRecipe to the text file.
    private void writeNoCookRecipe(PrintWriter pw, NoCookRecipe noCookRecipe){
        pw.println("Type: "+noCookRecipe.getRecipeType());
        pw.println("Name: "+noCookRecipe.getName());
        pw.println("Category: "+noCookRecipe.getCategory());
        pw.println("Prep Time: "+noCookRecipe.getPrepTime());
        pw.println("Prep Unit: "+noCookRecipe.getPrepTimeUnit());
        pw.println("Chill Time: "+noCookRecipe.getChillTime());
        pw.println("Chill Time Unit: "+noCookRecipe.getChillTimeUnit());
                    
        pw.println();
                    
        pw.println("Ingredients: ");
        for(Ingredient ingredient:noCookRecipe.getIngredients()){
            pw.println(ingredient.getName()+" | "+ingredient.getQuantity()+" | "+ingredient.getUnit());
        }
        pw.println();
        pw.println("Instructions: ");
        pw.println();
        int i=1;
        for(String instruction:noCookRecipe.getInstructions()){
            pw.println(i+") "+instruction);
            i++;
        }
        pw.println("END_RECIPE");
        pw.println();
    }
    
    // Reads an ingredient line from the file and adds it to the current recipe.
    private void readIngredient(String line, Recipe currentRecipe){
        
        StringTokenizer st = new StringTokenizer(line, "|");
        String ingredientName=st.nextToken().trim();
        double quantity=Double.parseDouble(st.nextToken().trim());
        String unit=st.nextToken().trim();
        currentRecipe.addIngredient(ingredientName, quantity, unit);
    } 
    // Reads an instruction line from the file and adds it to the current recipe.
    private void readInstruction(String line, Recipe currentRecipe){
        
        int position = line.indexOf(")");
        String instruction = line.substring(position + 1).trim();
        currentRecipe.addInstruction(instruction);
    }
    
    
    
    //Extracts the value after ':' from a line read from the recipe file.
    // Refactoring this code into a helper method was suggested with assistance from ChatGPT.
    private String readValue(String line){

        StringTokenizer st = new StringTokenizer(line, ":");
        st.nextToken();

        return st.nextToken().trim();
        
    }
}
