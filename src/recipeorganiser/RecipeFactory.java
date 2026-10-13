/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;

/**
 *
 * @author chaimaoueslati
 */
// Creating a separate RecipeFactory class was suggested with assistance from ChatGPT.
// The recipe-creation logic was adapted from my existing code.
public class RecipeFactory {
    
    // Creates the correct Recipe subclass based on the given type.
    public Recipe createRecipe(String name, String type, RecipeCategory category){
        if ( type.equals("baking") || type.equals("1")) {
            return new BakingRecipe(name, category);

        } else if (type.equals("no_cook") || type.equals("no cook") ||type.equals("nocook") ||type.equals("2")) {
            return new NoCookRecipe(name, category);

        } else if (type.equals("stove_top") || type.equals("stove top") ||type.equals("stovetop")|| type.equals("3")) {
            return new StoveTopRecipe(name, category);

        } else {
            
            return null;
        }
    }
            
    
}
