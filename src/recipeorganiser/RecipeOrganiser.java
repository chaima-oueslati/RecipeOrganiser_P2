/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package recipeorganiser;

/**
 *
 * @author chaimaoueslati
 */
public class RecipeOrganiser {

    /**
     * @param args the command line arguments
     */
    //Starts the Recipe Organiser application and connects the main classes.
    public static void main(String[] args) {
        
        RecipeManager manager = new RecipeManager();
        RecipeFileManager fileManager = new RecipeFileManager();
        
        fileManager.loadRecipes(manager);
        RecipeConsoleUI cui = new RecipeConsoleUI(manager, fileManager);
        cui.run();
        
    }
    
}
