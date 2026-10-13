/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;

/**
 *
 * @author chaimaoueslati
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

}
