/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package recipeorganiser;

import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
/**
 *
 * @author chaimaoueslati
 */
// AI assistance was used to develop and debug the JUnit4 tests.
// NetBeanswas used to check and run Tests
public class RecipeFactoryTest {
    
    private final RecipeFactory factory = new RecipeFactory();
    private final RecipeManager manager =new RecipeManager(new RecipeDatabase());

    @Test
    public void testCreateBakingRecipe() {
        Recipe recipe = factory.createRecipe(
                "Test Cookies", "baking", RecipeCategory.DESSERT
        );

        assertNotNull(recipe);
        assertTrue(recipe instanceof BakingRecipe);
        assertEquals("Test Cookies", recipe.getName());
    }

    @Test
    public void testCreateNoCookRecipe() {
        Recipe recipe = factory.createRecipe(
                "Test Salad", "no_cook", RecipeCategory.APPETISER
        );

        assertNotNull(recipe);
        assertTrue(recipe instanceof NoCookRecipe);
    }

    @Test
    public void testCreateStoveTopRecipe() {
        Recipe recipe = factory.createRecipe(
                "Test Pasta", "stove_top", RecipeCategory.MAIN_COURSE
        );

        assertNotNull(recipe);
        assertTrue(recipe instanceof StoveTopRecipe);
    }

    @Test
    public void testInvalidRecipeType() {
        Recipe recipe = factory.createRecipe(
                "Unknown Recipe", "invalid", RecipeCategory.DESSERT
        );

        assertNull(recipe);
    }

    @Test
    public void testAlternativeRecipeType() {
        Recipe recipe = factory.createRecipe(
                "Test Pancakes", "stove top", RecipeCategory.BREAKFAST
        );

        assertNotNull(recipe);
        assertTrue(recipe instanceof StoveTopRecipe);
    }
    @Test
    public void testAddRecipe() throws SQLException {
        String name = "JUnit Salad " + UUID.randomUUID();

        NoCookRecipe recipe = new NoCookRecipe(
                name, RecipeCategory.APPETISER
        );
        recipe.setPrepDetails(10, 'M');
        recipe.setChillTimeDetails(0, 'M');
        

        boolean added = manager.addRecipe(recipe);

        assertTrue(added);
        assertNotNull(manager.getRecipe(name));
    }

    
    @Test
    public void testRejectDuplicateRecipeName() throws SQLException {

        String name = "JUnit Duplicate " + UUID.randomUUID();

        NoCookRecipe first = new NoCookRecipe(
                name, RecipeCategory.APPETISER
        );

        NoCookRecipe duplicate = new NoCookRecipe(
                name, RecipeCategory.APPETISER
        );

        first.setPrepDetails(10, 'M');
        first.setChillTimeDetails(0, 'M');

        duplicate.setPrepDetails(10, 'M');
        duplicate.setChillTimeDetails(0, 'M');

        assertTrue(manager.addRecipe(first));
        assertFalse(manager.addRecipe(duplicate));
    }
}
