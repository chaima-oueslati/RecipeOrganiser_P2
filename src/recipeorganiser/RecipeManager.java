/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Map<String,Recipe>recipes=new HashMap<>();
    private RecipeDatabase database;
    
    public RecipeManager(RecipeDatabase database) {
        this.database = database;
    }
    
    // Adds a recipe using a lowercase name as the map key to prevent
    // duplicate names with different capitalisation.
    public boolean addRecipe(Recipe recipe)throws SQLException{
        return database.addRecipe(recipe);              
    }
    // Removes a recipe by name using a case-insensitive lookup.
    public boolean removeRecipe(String name){
        if(recipes.containsKey(name.toLowerCase())){
            recipes.remove(name.toLowerCase());
            return true;
        }else{
            return false;
        }
    }
    
    // Returns a copy of all recipes currently stored.
    public List<Recipe> viewRecipes(){
        return new ArrayList<>(recipes.values());
        
    }
    
    // Finds a recipe by name using case-insensitive lookup.
    public Recipe getRecipe(String name){
        if(recipes.containsKey(name.toLowerCase())){
            return recipes.get(name.toLowerCase());
        }else{
            return null;
        }    
    }
    
    // Changes a recipe name and updates its key in the map.
    public boolean editName(String oldName,String newName){
        
        if(recipes.containsKey(oldName.toLowerCase())&& (oldName.equalsIgnoreCase(newName)||!(recipes.containsKey(newName.toLowerCase())))){
            Recipe recipe = recipes.get(oldName.toLowerCase());
            recipes.remove(oldName.toLowerCase());
            recipe.setName(newName);
            recipes.put(newName.toLowerCase(),recipe);
            
            return true;
        }else{
            return false;
        }    
    }
    
    // Checks whether a recipe with the given name already exists.
    public boolean recipeExist(String name){
        if(recipes.containsKey(name.toLowerCase())){
            return true;
        }
        return false;
    }    
        
    // Returns all recipes that belong to the selected category.
    public List<Recipe> filterRecipeByCategory(RecipeCategory category){
        List<Recipe> categoryList=new ArrayList<>();
        for(Recipe recipe:recipes.values()){
            if(recipe.getCategory()==category){
                categoryList.add(recipe);
            }
            
        }
        return categoryList;
    }
    
    // Returns recipes whose names contain the given search word.
    public List<Recipe> searchByName(String word){
        List<Recipe> search = new ArrayList<>();
        for(Recipe recipe:recipes.values()){
            if( recipe.getName().toLowerCase().contains(word.toLowerCase())){
                search.add(recipe);
            }
        }
        return search;
    }
    
    
    
    
}
