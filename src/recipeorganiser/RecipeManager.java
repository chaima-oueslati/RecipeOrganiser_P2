/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;


import java.util.List;

import java.sql.SQLException;

/**
 *
 * @author chaimaoueslati
 */
/*
 * Manages the collection of recipes, including adding, removing,
 * searching, filtering, and editing recipes.
 */
public class RecipeManager {
    
    private RecipeDatabase database;
    
    public RecipeManager(RecipeDatabase database) {
        this.database = database;
    }
    
    // Adds a recipe using a lowercase name as the map key to prevent
    // duplicate names with different capitalisation.
    public boolean addRecipe(Recipe recipe)throws SQLException{
        return database.addRecipe(recipe);              
    }
    
    public boolean removeRecipe(String name) throws SQLException{
        return database.removeRecipe(name);
    }
    
    // Returns a copy of all recipes currently stored.
    public List<Recipe> viewRecipes() throws SQLException{
        return database.viewRecipes();
        
    }
    
    // Finds a recipe by name using case-insensitive lookup.
    public Recipe getRecipe(String name) throws SQLException{
        return database.getRecipe(name);
    }
    
    public boolean editRecipe(String oldName, Recipe updatedRecipe) throws SQLException {
        return database.editRecipe(oldName, updatedRecipe);
    }
    
   
        
    // Returns all recipes that belong to the selected category.
    public List<Recipe> filterRecipeByCategory(RecipeCategory category) throws SQLException{
        
        return database.searchByCategory(category);
    }
    
    // Returns recipes whose names contain the given search word.
    public List<Recipe> searchByName(String word) throws SQLException{
        return database.searchByName(word);
    }
    
    
    
    
}
