/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;

/**
 *
 * @author chaimaoueslati
 */
/*
 * Represents a recipe assigned to a particular day and meal type
 * in the weekly meal plan.
 */
public class PlannedMeal {
    
    private DayOfWeek day;
    private MealType mealType;
    private Recipe recipe;
    
    public PlannedMeal(DayOfWeek day, MealType mealType, Recipe recipe) {
        this.day = day;
        this.mealType = mealType;
        this.recipe = recipe;
    }
    public DayOfWeek getDay() {
        return day;
    }

    public MealType getMealType() {
        return mealType;
    }

    public Recipe getRecipe() {
        return recipe;
    }

}
