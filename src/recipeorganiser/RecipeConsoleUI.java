/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package recipeorganiser;
//
//import java.util.List;
//import java.util.Scanner;
//
///**
// *
// * @author chaimaoueslati
// */
///**
// * Handles console interaction with the user and connects user input
// * to the recipe management and file management functions.
// */
//public class RecipeConsoleUI {
//    
//    private RecipeManager manager;
//    private RecipeFileManager fileManager;
//    private Scanner scanner= new Scanner(System.in);
//    private String input;
//    
//    public RecipeConsoleUI(RecipeManager manager,RecipeFileManager fileManager){
//        this.manager=manager;
//        this.fileManager=fileManager;
//    }
//    // Displays the main menu and controls navigation between all recipe actions.
//    public void run(){
//        
//        // Label used so B/X can return directly to the main menu from nested loops.
//        mainLoop:
//        while(true){
//            
//            System.out.println(" ------------------");
//            System.out.println("| Recipe Organiser |");
//            System.out.println(" ------------------");
//            System.out.println();
//            System.out.println("1. Add Recipe");
//            System.out.println("2. View All Recipes");
//            System.out.println("3. View A Recipe by name");
//            System.out.println("4. Search Recipes by name");
//            System.out.println("5. Filter Recipes by Category");
//            System.out.println("6. Edit Recipe");
//            System.out.println("7. Remove Recipe");
//            System.out.println("8. Save Recipes");
//            System.out.println("X. Exit");
//            System.out.print("Enter your choice: ");
//            
//            input=scanner.nextLine().trim();
//            if(input.equals("x") || input.equals("X")){
//                System.out.println("Exiting App");
//                break;
//                
//            // Add a new recipe.
//            }else if(input.equals("1")){
//                String name;
//                RecipeCategory category;
//                String type;
//                String categoryInput;
//                Recipe recipe;
//                double prepTime;
//                char prepTimeUnit;
//                
//                
//                System.out.println(" ------------------");
//                System.out.println("|    Add Recipe    |");
//                System.out.println(" ------------------");
//                System.out.println("B.Back to menu");
//                System.out.println("X.Exit");
//                while(true){
//                    System.out.print("Enter the recipe name: ");
//                    name=scanner.nextLine().trim();
//                    if(name.equals("B")||name.equals("b")) {
//                        continue mainLoop;
//                    }
//
//                    if (name.equals("X")||name.equals("x")) {
//                        break mainLoop;  
//                    }
//                    if(validNewRecipeName(name)){
//                        break;
//                    }
//                }
//                while(true){
//                    System.out.println("Categories: APPETISER, BREAKFAST, MAIN_COURSE, DESSERT, SNACK, DRINKS, SAUCE");
//                    System.out.print("Enter the recipe category: ");
//                    categoryInput=scanner.nextLine().trim().toUpperCase().replace(' ', '_');
//                    
//                    if(categoryInput.equals("B")) {
//                        continue mainLoop;
//                    }
//
//                    if (categoryInput.equals("X")) {
//                        break mainLoop;  
//                    }
//                     if(validCategory(categoryInput)){
//                        category = RecipeCategory.valueOf(categoryInput);
//                        break;
//                    }            
//                }
//                while(true){
//                    System.out.println("Enter the recipe type:");
//                    System.out.print("1)baking \n2)no cook \n3)stove top) \n:");
//                    type=scanner.nextLine().trim().toLowerCase();
//                    if(type.equals("B")||type.equals("b")) {
//                        continue mainLoop;
//                    }
//                    
//                    if (type.equals("X")||type.equals("x")) {
//                        break mainLoop;  
//                    }    
//                    
//                    recipe = createRecipeByType(type, name, category);
//                    if(recipe != null){
//                        break;
//                    }
//                    System.out.println("Invalid input.");
//                    
//                }
//                while(true){
//                    System.out.println("Enter Prep Time:");
//                    String prepTimeInput=scanner.nextLine().trim();
//                    if(prepTimeInput.equals("B")||prepTimeInput.equals("b")) {
//                        continue mainLoop;
//                    }
//
//                    if (prepTimeInput.equals("X")||prepTimeInput.equals("x")) {
//                        break mainLoop;  
//                    }    
//                    if(prepTimeInput.isEmpty()){
//                        System.out.println("Invalid Input.Prep Time can not be empty.");
//                        continue;
//                    }
//                    try{
//                        prepTime=Double.parseDouble(prepTimeInput);
//                        if(prepTime<=0){
//                            System.out.println("Prep time must be greater than 0.");
//                            continue;
//                        }
//                        break;
//                    }
//                    catch(NumberFormatException e){
//                        System.out.println("Invalid Input.");
//                    }
//                }
//                while(true){
//                    System.out.print("Enter Prep Time Unit ('m' or 'h'): ");
//                    String unitInput=scanner.nextLine().toUpperCase().trim();
//                    if(unitInput.isEmpty()){
//                        System.out.println("Invalid Input.Prep Time Unit can not be empty.");
//                        continue;
//                    }
//                    prepTimeUnit=unitInput.charAt(0);
//                    
//                    if(prepTimeUnit=='B') {
//                        continue mainLoop;
//                    }
//
//                    if (prepTimeUnit=='X') {
//                        break mainLoop;  
//                    }  
//                    
//                    if(prepTimeUnit=='M'){
//                        recipe.setPrepDetails(prepTime, prepTimeUnit);
//                        break;
//                    }else if(prepTimeUnit=='H'){
//                        recipe.setPrepDetails(prepTime, prepTimeUnit);
//                        break;
//                    }else{
//                        System.out.println("Invalid Input.");
//                        
//                    }
//                }
//                
//                String ingredientResult = addIngredients(recipe);
//
//                if(ingredientResult.equals("BACK")){
//                    continue mainLoop;
//                }
//
//                if(ingredientResult.equals("EXIT")){
//                    break mainLoop;
//                }
//                
//                
//                String instructionResult = addInstructions(recipe);
//
//                if(instructionResult.equals("BACK")){
//                    continue mainLoop;
//                }
//
//                if(instructionResult.equals("EXIT")){
//                    break mainLoop;
//                }
//                
//                
//                if(recipe instanceof BakingRecipe){
//                    BakingRecipe bakingRecipe = (BakingRecipe)recipe;
//                    String bakingResult = setBakingDetails(bakingRecipe);
//
//                    if(bakingResult.equals("BACK")){
//                        continue mainLoop;
//                    }
//
//                    if(bakingResult.equals("EXIT")){
//                        break mainLoop;
//                    }
//                    
//                    
//                }else if(recipe instanceof StoveTopRecipe){
//                    StoveTopRecipe stoveTopRecipe = (StoveTopRecipe)recipe;
//                    String stoveResult = setStoveTopDetails(stoveTopRecipe);
//
//                    if(stoveResult.equals("BACK")){
//                        continue mainLoop;
//                    }
//
//                    if(stoveResult.equals("EXIT")){
//                        break mainLoop;
//                    }
//                    
//                    
//                    
//                }else if(recipe instanceof NoCookRecipe){
//                    NoCookRecipe noCookRecipe = (NoCookRecipe)recipe;
//
//                    String noCookResult = setNoCookDetails(noCookRecipe);
//
//                    if(noCookResult.equals("BACK")){
//                        continue mainLoop;
//                    }
//
//                    if(noCookResult.equals("EXIT")){
//                        break mainLoop;
//                    }
//                    
//                }
//                
//                if(manager.addRecipe(recipe)){
//                    System.out.println("Recipe has been added.");
//                }else {
//                    System.out.println("Recipe could not be added.");
//                }
//                
//                
//                
//                
//                   
//            }else if(input.equals("2")){
//                
//                viewAllRecipes();
//                
//            }else if(input.equals("3")){
//                
//                System.out.println("  View A Recipe by name ");
//                System.out.println(" ------------------------");
//                
//                while(true){
//                    System.out.print("Enter the recipe name: ");
//                    String name=scanner.nextLine().trim();
//                    if(name.equals("B")||name.equals("b")) {
//                        continue mainLoop;
//                    }
//
//                    if (name.equals("X")||name.equals("x")) {
//                        break mainLoop;  
//                    }   
//                    
//                    if(viewRecipeByName(name)){
//                        break;
//                    }
//                    
//                }
//                    
//            }else if(input.equals("4")){
//                
//                System.out.println("  Search Recipes by name");
//                System.out.println(" -------------------------");
//                
//                while(true){
//                    System.out.print("Search names containing this word: ");
//                    String word=scanner.nextLine().trim();
//                    
//                    if(word.equals("B")||word.equals("b")) {
//                        continue mainLoop;
//                    }
//
//                    if (word.equals("X")||word.equals("x")) {
//                        break mainLoop;  
//                    }  
//                    
//                    if(searchRecipesByName(word)){
//                        break;
//                    }
//                        
//                }
//                    
//            }else if(input.equals("5")){
//                
//                System.out.println(" Filter Recipes by Category");
//                System.out.println("----------------------------");
//                while(true){
//                    
//                    System.out.println("Enter a category from the following (APPETISER,BREAKFAST, MAIN_COURSE, DESSERT, SNACK, DRINKS, SAUCE): ");
//                    String categoryInput=scanner.nextLine().trim().toUpperCase().replace(' ', '_');
//                    
//                    if(categoryInput.equals("B")) {
//                        continue mainLoop;
//                    }
//
//                    if (categoryInput.equals("X")) {
//                        break mainLoop;  
//                    }  
//                    
//                    if(filterRecipesByCategory(categoryInput)){
//                        break;
//                    }
//                }
//             // Edit an existing recipe.   
//            }else if(input.equals("6")){
//                Recipe recipe;
//                String recipeName;
//                System.out.println(" Edit Recipe:");
//                System.out.println("--------------");
//                while(true){
//                        
//                    System.out.print("Which recipe do you want to edit? ");
//                    recipeName=scanner.nextLine().trim();
//                    if(recipeName.isEmpty()){
//                            System.out.println("Invalid Input. Recipe name can not be empty.");
//                            continue;
//                        }
//                    if(recipeName.equals("B")||recipeName.equals("b")) {
//                        continue mainLoop;
//                    }
//
//                    if (recipeName.equals("X")||recipeName.equals("x")) {
//                        break mainLoop;  
//                    }  
//                    if(manager.recipeExist(recipeName)){
//                        recipe=manager.getRecipe(recipeName);
//                        break;
//                    }else{
//                        System.out.println("Invalid Input.");
//                    }    
//                        
//                }
//                
//                // Label used to return to the edit submenu from nested edit loops.
//                subMenuLoop:
//                while(true){
//
//                    System.out.println("1. Edit Name");
//                    System.out.println("2. Edit Category");
//                    System.out.println("3. Edit Prep Details");
//                    System.out.println("4. Add Ingredient");
//                    System.out.println("5. Remove Ingredient");
//                    System.out.println("6. Add Instruction");
//                    System.out.println("7. Remove Instruction");
//                    System.out.println("8. Edit Type-Specific Details");
//                    System.out.println("B. Back");
//                    System.out.println("X. Exit");
//                    System.out.println("Enter your choice:");
//                    String input=scanner.nextLine().trim();
//                    if(input.isEmpty()){
//                        System.out.println("Invalid Input. Input can not be empty.");
//                        continue;
//                    }
//                    
//                    if(input.equals("X")||input.equals("x")){
//                        System.out.println("Exiting App");
//                        break mainLoop;
//                    }else if(input.equals("B")||input.equals("b")){
//                        System.out.println("Going Back to main menu:");
//                        continue mainLoop;
//                        
//                    }else if(input.equals("1"))  {
//                        System.out.println(" Edit Name:");
//                        System.out.println("------------");
//                        
//                        String newName;
//                        while(true){
//                            System.out.println("Enter the new recipe name:  ");
//                            newName=scanner.nextLine().trim();
//                            
//                            if(newName.equals("B")||newName.equals("b")) {
//                                continue subMenuLoop;
//                            }
//                            if (newName.equals("X")||newName.equals("x")) {
//                                break mainLoop;  
//                            }  
//                            
//                            if(editRecipeName(recipeName, newName)){
//                                recipeName=newName;
//                                break;
//                            }   
//                            
//                        }
//                            
//                    }else if(input.equals("2")){
//                        System.out.println(" Edit Category:");
//                        System.out.println("----------------");
//                        
//                        String categoryInput;
//                        while(true){
//                            System.out.print("Enter the new category: ");
//                            categoryInput=scanner.nextLine().trim().toUpperCase().replace(' ','_');
//                            
//                            if(categoryInput.equals("B")) {
//                                continue subMenuLoop;
//                            }
//                            if (categoryInput.equals("X")) {
//                                break mainLoop;  
//                            }  
//                            if(editRecipeCategory(recipe, categoryInput)){
//                                break;
//                            }
//                                
//                        }
//                    
//                    }else if(input.equals("3")){
//                        System.out.println(" Edit Prep Details: ");
//                        System.out.println("--------------------");
//                        
//                        String prepResult = editPrepDetails(recipe);
//                        if(prepResult.equals("BACK")){
//                            continue subMenuLoop;
//                        }
//
//                        if(prepResult.equals("EXIT")){
//                            break mainLoop;
//                        }
//                                              
//    
//                    }else if(input.equals("4")){
//                        System.out.println(" Add Ingredient:");
//                        System.out.println("-----------------");
//                        
//                        String addIngredientResult = addIngredientToRecipe(recipe);
//                        if(addIngredientResult.equals("BACK")){
//                            continue subMenuLoop;
//                        }
//
//                        if(addIngredientResult.equals("EXIT")){
//                            break mainLoop;
//                        }
//                      
//                    }else if(input.equals("5")){
//                        System.out.println(" Remove Ingredient:");
//                        System.out.println("-------------------");
//                        String removeIngredientResult = removeIngredientFromRecipe(recipe);
//
//                        if(removeIngredientResult.equals("BACK")){
//                            continue subMenuLoop;
//                        }
//
//                        if(removeIngredientResult.equals("EXIT")){
//                            break mainLoop;
//                        }
//                        
//                        
//                       
//                    }else if(input.equals("6")){
//                        System.out.println(" Add Instruction:");
//                        System.out.println("------------------");
//                        String instruction;
//                        while(true){
//                            System.out.print("Enter the new instruction: ");
//                            instruction=scanner.nextLine().trim();
//                            if(instruction.equals("B")||instruction.equals("b")) {
//                                continue subMenuLoop;
//                            }
//                            if (instruction.equals("X")||instruction.equals("x")) {
//                                break mainLoop;  
//                            }  
//                            if(instruction.isEmpty()){
//                                System.out.println("Invalid Input. Instruction can not be empty");
//                            }else{
//                                recipe.addInstruction(instruction);
//                                System.out.println("Instruction added.");
//                                break;
//                            }   
//                        }
//                        
//                      
//                    }else if(input.equals("7")){
//                        System.out.println(" Remove Instruction:");
//                        System.out.println("--------------------");
//                        int instructionNumber;
//                        String instruction;
//                        System.out.println("Instructions:");
//                        List<String> instructions = recipe.getInstructions();
//                        if(instructions.isEmpty()){
//                            System.out.println("There are no instructions to remove.");
//                            continue subMenuLoop;
//                        }
//
//                        for (int i = 0; i < instructions.size(); i++) {
//                            System.out.println((i + 1) + ". " + instructions.get(i));
//                        }
//                        
//                        while(true){
//                            System.out.print("Enter instruction number:");
//                            instruction=scanner.nextLine().trim();
//                            
//                            if(instruction.isEmpty()){
//                                System.out.println("Invalid Input. Instruction can not be empty.");
//                                continue;
//                            }
//                            if(instruction.equals("B")||instruction.equals("b")) {
//                                continue subMenuLoop;
//                            }
//                            if (instruction.equals("X")||instruction.equals("x")) {
//                                break mainLoop;  
//                            }  
//                            try{
//                                instructionNumber=Integer.parseInt(instruction);
//                                if(instructionNumber>instructions.size()||instructionNumber<=0){
//                                    System.out.println("Invalid number. Enter a number from 1 to " + instructions.size() + ".");
//                                    continue;
//                                }
//                                recipe.removeInstruction(instructionNumber);
//                                System.out.println("Instruction was removed");
//                                break;
//                               
//                            }catch(NumberFormatException e){
//                                System.out.println("Invalid Input.");
//                            }
//                        }
//
//                        
//                    }else if(input.equals("8")){
//                        System.out.println(" Edit Type-Specific Details:");
//                        System.out.println("-----------------------------");
//                        
//                       
//                        if(recipe instanceof BakingRecipe){
//                            BakingRecipe bakingRecipe=(BakingRecipe) recipe;
//                            
//                            System.out.println("* Edit Baking Details *");
//
//                            String bakingResult = setBakingDetails(bakingRecipe);
//
//                            if(bakingResult.equals("BACK")){
//                                continue subMenuLoop;
//                            }
//
//                            if(bakingResult.equals("EXIT")){
//                                break mainLoop;
//                            }
//
//                            System.out.println("Baking Recipe details have been updated.");
//                               
//                        }else if(recipe instanceof StoveTopRecipe){
//                            
//                            StoveTopRecipe stoveRecipe = (StoveTopRecipe) recipe;
//                            String stoveResult = setStoveTopDetails(stoveRecipe);
//
//                            if(stoveResult.equals("BACK")){
//                                continue subMenuLoop;
//                            }
//
//                            if(stoveResult.equals("EXIT")){
//                                break mainLoop;
//                            }
//
//                            System.out.println("Stove Top Recipe details have been updated.");
//                            
//                        }else if(recipe instanceof NoCookRecipe){
//                            
//                            NoCookRecipe noCook = (NoCookRecipe) recipe;
//                            String noCookResult = setNoCookDetails(noCook);
//
//                            if(noCookResult.equals("BACK")){
//                                continue subMenuLoop;
//                            }
//
//                            if(noCookResult.equals("EXIT")){
//                                break mainLoop;
//                            }
//
//                            System.out.println("No Cook Recipe details have been updated.");
//                            
//                        }    
//                    }
//                    
//                }
//                
//            // Remove a recipe.    
//            }else if(input.equals("7")){
//                System.out.println(" Remove Recipe");
//                System.out.println("---------------");
//                viewRecipeNames();
//                while(true){
//                    
//                    System.out.print("Enter the recipe name to be removed: ");
//                    String name=scanner.nextLine().trim();
//                    if( name.equals("B")||name.equals("b")) {
//                        continue mainLoop;
//                    }
//
//                    if ( name.equals("X")||name.equals("x")) {
//                        break mainLoop;  
//                    }  
//                    
//                    if(removeRecipeByName(name)){
//                        break;
//                    }
//                    
//                }
//            // Save all recipes to the file.    
//            }else if(input.equals("8")){
//                System.out.println(" Save Recipes");
//                System.out.println("--------------");
//                
//                fileManager.saveRecipes(manager);
//                System.out.println("File was saved.");
//                    
//            }else{
//                System.out.println("Invalid choice. Please try again: ");
//                continue;
//            }
//           
//                
//        }
//        
//    }
//    
//    
//    // Displays all recipes currently stored in the manager.
//    private void viewAllRecipes(){
//        
//        System.out.println("  All Recipes: ");
//        System.out.println("-----------------");
//        List<Recipe> recipes=manager.viewRecipes();
//        if(recipes.isEmpty()){
//            System.out.println("No recipes were found.");
//        }
//        else{
//            for(Recipe recipe:recipes){
//                System.out.println(recipe);
//            }
//        }
//    }
//    
//    // Finds and displays a recipe with the exact given name.
//    private boolean viewRecipeByName(String name){
//        
//        if(name.isEmpty()){
//            System.out.println("Invalid Input. Recipe name can not be empty");
//            return false;
//                        
//        }else{
//            Recipe recipe=manager.getRecipe(name);
//            if(recipe!=null){
//                System.out.println(recipe);
//                return true;
//                    
//            }else{
//                System.out.println("Recipe name does not exist.");
//                return false;
//            }
//                    
//        }
//    }
//    
//    // Searches for recipes whose names contain the given word.
//    private boolean searchRecipesByName(String word){
//        if(word.isEmpty()){
//            System.out.println("Invalid Input. Word can not be empty ");
//            return false;
//                        
//        }else{
//            List<Recipe> recipes=manager.searchByName(word);
//            if(recipes.isEmpty()){
//                System.out.println("No matches were found.");
//                return false;
//            }else{
//                for(Recipe recipe:recipes){
//                    System.out.println(recipe);
//                }
//                return true;
//                
//            }
//                        
//        }
//        
//    }
//    
//    // Filters and displays recipes belonging to the selected category.
//    private boolean filterRecipesByCategory(String categoryInput){
//
//        if(categoryInput.isEmpty()){
//            System.out.println("Invalid Input. Category can not be empty.");
//            return false;
//        }
//
//        try{
//            RecipeCategory category = RecipeCategory.valueOf(categoryInput);
//
//            List<Recipe> recipes =
//                    manager.filterRecipeByCategory(category);
//
//            if(recipes.isEmpty()){
//                System.out.println("Category has no recipes.");
//                return true;
//            }
//
//            for(Recipe recipe : recipes){
//                System.out.println(recipe);
//            }
//
//            return true;
//
//        }catch(IllegalArgumentException e){
//            System.out.println("Invalid category.");
//            return false;
//        }
//    }
//    
//    // Removes a recipe with the given name.
//    private boolean removeRecipeByName(String name){
//        
//        if(name.isEmpty()){
//            System.out.println("Invalid Input. Recipe name can not be empty");
//            return false;
//                        
//        }else{
//            if(manager.removeRecipe(name)){
//                System.out.println("The recipe "+name+" was removed.");
//                return true;
//            }else{
//                System.out.println("Recipe name was not found");
//                return false;
//            }
//                    
//        }
//    }
//    
//    // Checks that a new recipe name is not empty and does not already exist.
//    private boolean validNewRecipeName(String name){
//
//        if(name.isEmpty()){
//            System.out.println("Recipe name can not be empty.");
//            return false;
//
//        }else if(manager.recipeExist(name)){
//            System.out.println("The recipe name exists.");
//            return false;
//
//        }else{
//            return true;
//        }
//    }
//    
//    // Checks whether the entered category is valid.
//    private boolean validCategory(String categoryInput){
//
//        if(categoryInput.isEmpty()){
//            System.out.println("Invalid Input. Category can not be empty.");
//            return false;
//        }
//
//        try{
//            RecipeCategory.valueOf(categoryInput);
//            return true;
//        }
//        catch(IllegalArgumentException e){
//            System.out.println("Invalid Input. Please try again.");
//            return false;
//        }
//    }
//    
//    // Uses RecipeFactory to create the correct Recipe subclass.
//    private Recipe createRecipeByType( String type,String name,RecipeCategory category){
//
//        
//        if(type.isEmpty()){
//            System.out.println("Invalid Input. Recipe type can not be empty.");
//            return null;
//        }
//
//        RecipeFactory recipeFactory = new RecipeFactory();
//        return recipeFactory.createRecipe(name, type, category);
//    }
//    
//    // Collects ingredient details from the user and adds them to the recipe.
//    private String addIngredients(Recipe recipe){
//        ingredientLoop:
//        while(true){
//            String ingredientName;
//            double quantity;
//            String unit;
//            System.out.println("Enter ingredient:('S' to stop adding ingredient  ,  'R' to restart this ingredient )");
//                    
//            while(true){
//                        
//                System.out.print("Enter ingredient name: ");
//                ingredientName=scanner.nextLine().trim();
//                if(ingredientName.isEmpty()){
//                    System.out.println("Invalid Input.Ingredient name can not be empty.");
//                    continue;
//                }
//                if(ingredientName.equals("B")||ingredientName.equals("b")) {
//                    return "BACK";
//                }
//
//                if (ingredientName.equals("X")||ingredientName.equals("x")) {
//                    return "EXIT";
//                }    
//                if(ingredientName.equals("S")||ingredientName.equals("s")){
//                    break ingredientLoop;
//                }else if(ingredientName.equals("R")||ingredientName.equals("r")){
//                    continue ingredientLoop;
//                }else{
//                    break;
//                }    
//            }
//            while(true){
//                System.out.print("Enter ingredient quantity: ");
//                String quantityInput=scanner.nextLine().trim();
//                if(quantityInput.isEmpty()){
//                    System.out.println("Invalid Input. Quantity can not be empty.");
//                    continue;
//                }
//                if(quantityInput.equals("B")||quantityInput.equals("b")) {
//                    return "BACK";
//                }
//
//                if (quantityInput.equals("X")||quantityInput.equals("x")) {
//                    return "EXIT";  
//                }   
//                        
//                if(quantityInput.equals("S")||quantityInput.equals("s")){
//                    break ingredientLoop;
//                    
//                }else if(quantityInput.equals("R")||quantityInput.equals("r")){
//                    continue ingredientLoop;
//                }else{
//                    try{
//                        quantity=Double.parseDouble(quantityInput);
//                        if(quantity<=0){
//                            System.out.println("Ingredient quantity must be greater than 0.");
//                            continue;
//                        }
//                        break;
//                    }catch(NumberFormatException e){
//                        System.out.println("Invalid Input. Quantity must be a number.");
//                    }
//                    
//                }
//            }    
//            while(true){
//                System.out.print("Enter ingredient unit: ");
//                unit=scanner.nextLine().trim();
//                if(unit.isEmpty()){
//                    System.out.println("Invalid Input. Ingredient unit can not be empty.");
//                    continue;
//                }
//                if(unit.equals("B")||unit.equals("b")) {
//                    return "BACK";
//                }
//
//                if (unit.equals("X")||unit.equals("x")) {
//                    return "EXIT";  
//                }   
//                if(unit.equals("S")||unit.equals("s")){
//                    break ingredientLoop;
//                }else if(unit.equals("R")||unit.equals("r")){
//                    continue ingredientLoop;
//                }else{
//                    break;
//                }
//            }
//            recipe.addIngredient(ingredientName, quantity, unit);
//        
//        }
//        return "DONE";
//    }
//    
//    // Collects instructions from the user and adds them to the recipe.
//    private String addInstructions(Recipe recipe){
//        int i = 1;
//
//        instructionLoop:
//        while(true){
//            String instruction;
//
//            while(true){
//                System.out.print("Enter instruction number " + i + ": ");
//                instruction = scanner.nextLine().trim();
//
//                if(instruction.isEmpty()){
//                    System.out.println("Invalid Input. Instruction can not be empty.");
//                    continue;
//                }
//
//                if(instruction.equals("B")||instruction.equals("b")){
//                    return "BACK";
//                }
//
//                if(instruction.equals("X")||instruction.equals("x")){
//                    return "EXIT";
//                }
//
//                if(instruction.equals("S")||instruction.equals("s")){
//                    break instructionLoop;
//
//                }else if(instruction.equals("R")||instruction.equals("r")){
//                    continue instructionLoop;
//
//                }else{
//                    recipe.addInstruction(instruction);
//                    i++;
//                    break;
//                }
//            }
//        }
//
//        return "DONE";
//    }
//    
//    // Collects and sets baking-specific details.
//    private String setBakingDetails(BakingRecipe bakingRecipe){
//        int ovenTemperature;
//        char temperatureUnit;
//        double bakingTime;
//        char bakingTimeUnit;
//        OvenMode mode;
//                    
//        while(true){
//            System.out.println("Enter oven temperature:");
//            String ovenTInput=scanner.nextLine().trim();
//            if(ovenTInput.isEmpty()){
//                System.out.println("Invalid Input. Oven Temperature can not be empty.");
//                continue;
//            }
//            if(ovenTInput.equals("B")||ovenTInput.equals("b")) {
//                return "BACK";
//            }
//
//            if (ovenTInput.equals("X")||ovenTInput.equals("x")) {
//                return "EXIT";
//            }   
//            try{
//                ovenTemperature= Integer.parseInt(ovenTInput);
//                if(ovenTemperature<=0){
//                    System.out.println("Oven Temperature must be greater than 0.");
//                    continue;
//                }
//                break;
//            }catch(NumberFormatException e){
//                System.out.println("Invalid Input");
//            }
//        }
//        while(true){
//            System.out.print("Enter Oven Temperature Unit ('C' or 'F'): ");
//            String temperatureUnitInput=scanner.nextLine().toUpperCase().trim();
//            if(temperatureUnitInput.isEmpty()){
//                System.out.println("Invalid Input. Oven Temperature Unit can not be empty.");
//                continue;
//            }
//            temperatureUnit=temperatureUnitInput.charAt(0);
//            if(temperatureUnit=='B') {
//                return "BACK";
//            }
//
//            if (temperatureUnit=='X') {
//                return"EXIT"; 
//            }   
//            if(temperatureUnit=='C' || temperatureUnit=='F'){
//                break;
//            }else{
//                System.out.println("Invalid Input.");
//            }
//        }
//        while(true){
//            System.out.println("Enter baking time:");
//            String bakingInput=scanner.nextLine().trim();
//            if(bakingInput.isEmpty()){
//                System.out.println("Invalid Input. Baking Time can not be empty.");
//                continue;
//            }
//            if(bakingInput.equals("B")||bakingInput.equals("b")) {
//                return "BACK";
//            }
//
//            if (bakingInput.equals("X")||bakingInput.equals("x")) {
//                return"EXIT";  
//            }   
//            try{
//                bakingTime= Double.parseDouble(bakingInput);
//                if(bakingTime<=0){
//                    System.out.println("Baking time must be greater than 0.");
//                    continue;
//                }
//                break;
//            }catch(NumberFormatException e){
//                System.out.println("Invalid Input");
//            }
//        }
//        while(true){
//            System.out.print("Enter Baking Time Unit ('M' or 'H'): ");
//            String bakingUnitInput=scanner.nextLine().toUpperCase().trim();
//            if(bakingUnitInput.isEmpty()){
//                System.out.println("Invalid Input. Baking Time Unit can not be empty.");
//                continue;
//            }
//            bakingTimeUnit=bakingUnitInput.charAt(0);
//            if(bakingTimeUnit=='B') {
//                return "BACK";
//            }
//
//            if (bakingTimeUnit=='X') {
//                return "EXIT";  
//            }   
//            if(bakingTimeUnit=='M' || bakingTimeUnit=='H'){
//                break;
//            }else{
//                System.out.println("Invalid Input.");
//            }
//        }
//        while(true){
//            System.out.print("Enter the oven mode (BAKE_CONVENTIONAL, FAN_FORCED , GRILL): ");
//            String modeInput=scanner.nextLine().trim().toUpperCase().replace(' ', '_');
//            if(modeInput.isEmpty()){
//                System.out.println("Invalid Input. Oven Mode can not be empty.");
//                continue;
//            }
//            if(modeInput.equals("B")) {
//                return "BACK";
//            }
//
//            if (modeInput.equals("X")) {
//                return "EXIT"; 
//            }   
//                    
//            try{
//                mode=OvenMode.valueOf(modeInput);
//                break;
//            }
//            catch(IllegalArgumentException e){
//                System.out.println("Invalid Input.Please try again.");
//            }
//        }
//        bakingRecipe.setBakingDetails(ovenTemperature, temperatureUnit, bakingTime, bakingTimeUnit, mode);
//        return "DONE";                
//
//    }
//    
//    // Collects and sets stove_top specific details.
//    private String setStoveTopDetails(StoveTopRecipe stoveTopRecipe){
//        HeatLevel heatLevel;
//        double cookingTime;
//        char cookingTimeUnit;
//        
//        while(true){
//            System.out.println("Enter cooking time:");
//            String cookingTimeInput=scanner.nextLine().trim();
//            if(cookingTimeInput.isEmpty()){
//                System.out.println("Invalid Input. Cooking Time can not be empty.");
//                continue;
//            }
//            if(cookingTimeInput.equals("B")||cookingTimeInput.equals("b")) {
//                return "BACK";
//            }
//            
//            if (cookingTimeInput.equals("X")||cookingTimeInput.equals("x")) {
//                return "EXIT";  
//            }    
//            try{
//                cookingTime= Double.parseDouble(cookingTimeInput);
//                if(cookingTime<=0){
//                    System.out.println("Cooking time must be greater than 0.");
//                    continue;
//                }   
//                break;
//            }catch(NumberFormatException e){
//                System.out.println("Invalid Input");
//            }
//        }
//        
//        
//        while(true){
//            System.out.print("Enter Cooking Time Unit ('M' or 'H'): ");
//            String cookingUnitInput=scanner.nextLine().toUpperCase().trim();
//            if(cookingUnitInput.isEmpty()){
//                System.out.println("Invalid Input. Cooking Time Unit can not be empty.");
//                continue;
//            }
//            cookingTimeUnit=cookingUnitInput.charAt(0);
//            if(cookingTimeUnit=='B') {
//                return "BACK";
//            }
//            if (cookingTimeUnit=='X') {
//                return "EXIT";  
//            }   
//                        
//            if(cookingTimeUnit=='M' || cookingTimeUnit=='H'){
//                break;
//            }else{
//                System.out.println("Invalid Input.");
//            }
//        }
//        
//        while(true){
//            System.out.print("Enter the heat level (LOW, MEDIUM , HIGH): ");
//            String heatInput=scanner.nextLine().trim().toUpperCase();
//            if(heatInput.isEmpty()){
//                System.out.println("Invalid Input. Heat Level can not be empty.");
//                continue;
//            }
//            if(heatInput.equals("B")) {
//                return "BACK";
//            }
//
//            if (heatInput.equals("X")) {
//                return "EXIT";
//            }    
//            try{
//                heatLevel=HeatLevel.valueOf(heatInput);
//                break;
//            }
//            catch(IllegalArgumentException e){
//                System.out.println("Invalid Input.Please try again.");
//            }
//        }
//        stoveTopRecipe.setStoveTopRecipeDetails(heatLevel, cookingTime, cookingTimeUnit);
//        return "DONE";           
//        
//    }
//    
//    // Collects and sets no_cook specific details.
//    private String setNoCookDetails(NoCookRecipe noCookRecipe){
//        double chillTime=0;
//        char chillTimeUnit;
//                   
//        while(true){
//            System.out.println("Enter chill time: ");
//            String chillTimeInput=scanner.nextLine().trim();
//            if(chillTimeInput.isEmpty()){
//                System.out.println("Invalid Input. Chill Time can not be empty.");
//                continue;
//            }
//            if(chillTimeInput.equals("B")||chillTimeInput.equals("b")) {
//                return "BACK";
//            }
//
//            if (chillTimeInput.equals("X")||chillTimeInput.equals("x")) {
//                return "EXIT"; 
//            }    
//            try{
//                chillTime=Double.parseDouble(chillTimeInput);
//                if(chillTime<0){
//                    System.out.println("Chill time can not be a negative value.");
//                    continue;
//                }
//                break;
//            }catch(NumberFormatException e){
//                System.out.println("Invalid Input");
//            }
//    
//        }
//        
//        while(true){
//        
//            System.out.print("Enter Chill Time Unit ('M' or 'H'): ");            
//            String chillUnitInput=scanner.nextLine().toUpperCase().trim();
//            if(chillUnitInput.isEmpty()){
//                            
//                System.out.println("Invalid Input. Chill Time Unit can not be empty.");    
//                continue;    
//            }
//            chillTimeUnit=chillUnitInput.charAt(0);
//            
//            if(chillTimeUnit=='B') {            
//                return "BACK";
//            }
//                       
//            if (chillTimeUnit=='X') {
//    
//                return "EXIT";      
//            }   
//
//            if(chillTimeUnit=='M' || chillTimeUnit=='H'){
//                break;
//            }else{
//                System.out.println("Invalid Input.");
//            }
//        }
//        noCookRecipe.setChillTimeDetails(chillTime, chillTimeUnit);
//        return "DONE";
//    }
//    
//    // Updates the name of an existing recipe.
//    private boolean editRecipeName(String recipeName, String newName){
//
//        if(newName.isEmpty()){
//            System.out.println("Invalid Input. New name can not be empty.");
//            return false;
//        }
//
//        if(manager.editName(recipeName, newName)){
//            System.out.println("Recipe name updated.");
//            return true;
//        }else{
//            System.out.println("Invalid Input.");
//            return false;
//        }
//    }
//    
//    // Updates the category of an existing recipe.
//    private boolean editRecipeCategory(Recipe recipe, String categoryInput){
//        if(categoryInput.isEmpty()){
//            System.out.println("Invalid Input. New category can not be empty.");
//            return false;
//        }
//        
//        try{
//            RecipeCategory category= RecipeCategory.valueOf(categoryInput);
//            recipe.setCategory(category);
//            System.out.println("The recipe category has been updated.");
//            return true;
//                                
//        }catch(IllegalArgumentException e){
//            System.out.println("Invalid Input.");
//            return false;
//        }
//    }
//    
//    // Updates the preparation time and unit of a recipe.
//    private String editPrepDetails(Recipe recipe){
//        double prepTime;
//        char prepTimeUnit;
//        String prepTimeInput;
//                                
//        while(true){
//        
//            System.out.print("Enter the new prep Time: ");                            
//            prepTimeInput=scanner.nextLine().trim();
//                        
//            if(prepTimeInput.isEmpty()){            
//                System.out.println("Invalid Input. New prep time can not be empty.");                
//                continue;
//            }
//            
//            if(prepTimeInput.equals("B")||prepTimeInput.equals("b")) {            
//                return"BACK"; 
//            }
//
//            if (prepTimeInput.equals("X")||prepTimeInput.equals("x")) {            
//                return"EXIT";                
//            }  
//
//            try{            
//                prepTime=Double.parseDouble(prepTimeInput);                
//                if(prepTime<=0){
//                    System.out.println("New prep time must to be greater than 0.");                    
//                    continue;                    
//                }                
//                break;
//                               
//            }catch(NumberFormatException e){
//                System.out.println("Invalid Input.");                
//            }    
//        }
//        
//        while(true){
//        
//            System.out.print("Enter the new prep Time Unit: ");            
//            String prepUnitInput=scanner.nextLine().trim().toUpperCase();
//            
//            if(prepUnitInput.isEmpty()){            
//                System.out.println("Invalid Input. New prep Time Unit can not be empty.");                
//                continue;
//            }            
//            prepTimeUnit=prepUnitInput.charAt(0);
//            
//            if(prepTimeUnit=='B') {            
//                return "BACK";               
//            }
//            if (prepTimeUnit=='X') {            
//                return"EXIT";                  
//            }  
//            if(prepTimeUnit=='M' || prepTimeUnit=='H'){
//                recipe.setPrepDetails(prepTime, prepTimeUnit);
//                break;
//            }else{
//                System.out.println("Invalid Input. Unit must be M or H.");
//            }
//                   
//        }
//        return "DONE";
//    }
//    
//    // Adds one new ingredient to an existing recipe.
//    private String addIngredientToRecipe(Recipe recipe){
//        String ingredientName;                       
//        double quantity;                                
//        String quantityInput;        
//        String unit;
//                                
//        while(true){
//        
//            System.out.print("Enter the new ingredient name: " );            
//            ingredientName=scanner.nextLine().trim();            
//            if(ingredientName.equals("B")||ingredientName.equals("b")) {           
//                return"BACK";              
//            }
//
//            if (ingredientName.equals("X")||ingredientName.equals("x")) {            
//                return"EXIT";                 
//            }  
//
//            if(ingredientName.isEmpty()){            
//                System.out.println("Invalid Input ");                                
//            }else{
//            
//                break;                
//            }
//        }
//        
//        while(true){
//        
//            System.out.print("Enter the new ingredient quantity: " );            
//            quantityInput=scanner.nextLine().trim();
//           
//            if(quantityInput.isEmpty()){            
//                System.out.println("Invalid Input. New ingredient quantity can not be empty.");               
//                continue;
//            }
//            
//            if(quantityInput.equals("B")||quantityInput.equals("b")) {            
//                return "BACK";               
//            }
//            if (quantityInput.equals("X")||quantityInput.equals("x")) {            
//                return"EXIT";                 
//            }  
//
//            try{            
//                quantity=Double.parseDouble(quantityInput);                
//                if(quantity<=0){
//                    System.out.println("Ingredient quantity must be greater than 0.");                    
//                    continue;                    
//                }                
//                break;                                
//            }catch(NumberFormatException e){            
//                System.out.println("Invalid Input.");                
//            }  
//        }
//        
//        while(true){
//        
//            System.out.print("Enter the new ingredient unit: ");            
//            unit=scanner.nextLine().trim();
//            
//            if(unit.equals("B")||(unit.equals("b"))) {            
//                return"BACK";              
//            }
//
//            if (unit.equals("X")||(unit.equals("x"))) {            
//                return"EXIT";                 
//            }  
//            if(unit.isEmpty()){                           
//                System.out.println("Invalid Input. Unit can not be empty");               
//            }else{
//                recipe.addIngredient(ingredientName, quantity, unit);
//                
//                System.out.println("Ingredient added.");
//                break;               
//            }
//
//        }
//        return "DONE";                
//    }
//    
//    // Removes an ingredient matching the entered name, quantity, and unit.
//    private String removeIngredientFromRecipe(Recipe recipe){
//        String ingredientName;
//        double quantity;
//        String quantityInput;
//        String unit;
//        while(true){
//            System.out.print("Enter the ingredient to be removed name: " );
//            ingredientName=scanner.nextLine().trim();
//            if(ingredientName.equals("B")||ingredientName.equals("b")) {
//                return"BACK";
//            }
//            if (ingredientName.equals("X")||ingredientName.equals("x")) {
//                return"EXIT";
//            }  
//
//            if(ingredientName.isEmpty()){
//                System.out.println("Invalid Input ");
//                               
//            }else{
//                break;
//            }
//        }
//        while(true){
//            System.out.print("Enter the ingredient to be removed quantity: " );
//            quantityInput=scanner.nextLine().trim();
//            if(quantityInput.isEmpty()){
//                System.out.println("Invalid Input. Quantity can not be empty.");
//                continue;
//            }
//            if(quantityInput.equals("B")||quantityInput.equals("b")) {
//                return"BACK";
//            }
//            if (quantityInput.equals("X")||quantityInput.equals("x")) {
//                return"EXIT"; 
//            }  
//            try{
//                quantity=Double.parseDouble(quantityInput);
//                if(quantity<=0){
//                    System.out.println("Ingredient quantity must be greater than 0.");
//                    continue;
//                }
//                break;
//                
//            }catch(NumberFormatException e){
//                System.out.println("Invalid Input.");
//            }  
//        }
//        while(true){
//            System.out.print("Enter the ingredient to be removed unit: ");
//            unit=scanner.nextLine().trim();
//            if(unit.equals("B")||(unit.equals("b"))) {
//                return"BACK";
//            }
//            if (unit.equals("X")||(unit.equals("x"))) {
//                return"EXIT";
//            }  
//            if(unit.isEmpty()){
//                System.out.println("Invalid Input. Unit can not be empty");
//            }else{
//                if(recipe.removeIngredient(ingredientName, quantity, unit)){
//                    System.out.println("Ingredient removed .");
//                                    
//                }else{
//                    System.out.println("Ingredient not found.");                                                
//                }
//                
//                break;                                
//            }
//        }                
//        return"DONE";
//    }
//    
//    // Generated with assistance from ChatGPT.
//    // Displays only the names of the existing recipes.
//    private void viewRecipeNames(){
//        List<Recipe> recipes = manager.viewRecipes();
//
//        if(recipes.isEmpty()){
//            System.out.println("No recipes were found.");
//        }else{
//            System.out.println("Existing recipes:");
//            for(Recipe recipe : recipes){
//                System.out.println("- " + recipe.getName());
//            }
//        }
//    }
//  
//    
//
//}
//
//
//
