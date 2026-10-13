/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recipeorganiser;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chaimaoueslati
 */
public class RecipeDatabase {
    public Connection connectDatabase() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:RecipeDB;create=true");
        return conn;
    }
    public void createTables() throws SQLException {
        
        try (Connection conn = connectDatabase();
            Statement statement = conn.createStatement()) {        


            createTable(statement,

                "CREATE TABLE RECIPE (" +
                "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "NAME VARCHAR(100) NOT NULL, " +
                "CATEGORY VARCHAR(30) NOT NULL, " +
                "RECIPE_TYPE VARCHAR(20) NOT NULL, " +
                "PREP_TIME DOUBLE, " +
                "PREP_TIME_UNIT CHAR(1), " +
                "OVEN_TEMPERATURE INT, " +
                "TEMPERATURE_UNIT CHAR(1), " +
                "BAKING_TIME DOUBLE, " +
                "BAKING_TIME_UNIT CHAR(1), " +
                "OVEN_MODE VARCHAR(30), " +
                "COOKING_TIME DOUBLE, " +
                "COOKING_TIME_UNIT CHAR(1), " +
                "HEAT_LEVEL VARCHAR(20), " +
                "CHILL_TIME DOUBLE, " +
                "CHILL_TIME_UNIT CHAR(1))"
            );

            createTable(statement,
                "CREATE TABLE INGREDIENT (" +
                "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                "RECIPE_ID INT NOT NULL, " +
                "NAME VARCHAR(100) NOT NULL, " +
                "QUANTITY DOUBLE NOT NULL, " +
                "UNIT VARCHAR(30) NOT NULL, " +
                "INGREDIENT_ORDER INT NOT NULL,"+
                "FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(ID))"
            );

            createTable(statement,
                "CREATE TABLE INSTRUCTIONS ("+
                "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,"+
                "RECIPE_ID INT NOT NULL,"+
                "INSTRUCTION_ORDER INT NOT NULL,"+
                "INSTRUCTION VARCHAR(2000),"+
                "FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(ID))"

            );

            createTable(statement,
                "CREATE TABLE FAVOURITE ("+

                "RECIPE_ID INT NOT NULL,"+


                "FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(ID))"

            );

            createTable(statement,
            "CREATE TABLE WEEKLY_MEAL_PLAN ("+        
            "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
            "DAY_OF_WEEK VARCHAR(10) NOT NULL," +
            "MEAL_TYPE VARCHAR(20) NOT NULL," +
            "RECIPE_ID INT NOT NULL," +
            "FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(ID))"     

            );
        }
    }
        
    private void createTable(Statement statement, String sql) throws SQLException {
        try {
            
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) {
                throw e;
            }
        }
    }
    
    public boolean addRecipe(Recipe recipe) throws SQLException {
        
        String checkSql = "SELECT ID FROM RECIPE WHERE LOWER(NAME) = LOWER(?)";
        
        try (Connection conn = connectDatabase()){
            conn.setAutoCommit(false);
            
            try{
                try(PreparedStatement ps = conn.prepareStatement(checkSql)) {

                    ps.setString(1, recipe.getName());

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            conn.rollback();
                            return false;
                        }
                    }

                }

                if (recipe instanceof BakingRecipe) {
                    insertBakingRecipe(conn,(BakingRecipe)recipe);

                } else if (recipe instanceof StoveTopRecipe) {
                    insertStoveTopRecipe(conn, (StoveTopRecipe)recipe);
                } else if (recipe instanceof NoCookRecipe) {
                    insertNoCookRecipe(conn, (NoCookRecipe) recipe);
                }else {
                    throw new IllegalArgumentException("Unsupported recipe type.");
                }
                conn.commit();
                return true;
                
            }catch(SQLException e){
                conn.rollback();
                throw e;
            }
                    
            
        }
        
        

    }
    
    private void insertBakingRecipe(Connection conn,BakingRecipe recipe) throws SQLException{
        int recipeId;
        String sql="INSERT INTO RECIPE" +
                    "(NAME, CATEGORY, RECIPE_TYPE, PREP_TIME, PREP_TIME_UNIT," +
                    " OVEN_TEMPERATURE, TEMPERATURE_UNIT, BAKING_TIME," +
                    " BAKING_TIME_UNIT, OVEN_MODE)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, recipe.getName());
            ps.setString(2, recipe.getCategory().name());
            ps.setString(3, recipe.getRecipeType());
            ps.setDouble(4, recipe.getPrepTime());
            ps.setString(5, String.valueOf(recipe.getPrepTimeUnit()));
            ps.setInt(6, recipe.getOvenTemperature());
            ps.setString(7, String.valueOf(recipe.getTemperatureUnit()));
            ps.setDouble(8, recipe.getBakingTime());
            ps.setString(9, String.valueOf(recipe.getBakingTimeUnit()));
            ps.setString(10, recipe.getMode().name());

            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    recipeId = rs.getInt(1);
                }else {
                    throw new SQLException("No recipe ID was generated.");
                }
                
            }
            
        }
        insertIngredients(conn, recipeId, recipe);
        insertInstructions(conn, recipeId, recipe);
        
    }
    
    private void insertStoveTopRecipe(Connection conn, StoveTopRecipe recipe) throws SQLException{
        int recipeId;
        String sql="INSERT INTO RECIPE" +
                    "(NAME, CATEGORY, RECIPE_TYPE, PREP_TIME, PREP_TIME_UNIT," +
                    " COOKING_TIME, COOKING_TIME_UNIT, HEAT_LEVEL)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, recipe.getName());
            ps.setString(2, recipe.getCategory().name());
            ps.setString(3, recipe.getRecipeType());
            ps.setDouble(4, recipe.getPrepTime());
            ps.setString(5, String.valueOf(recipe.getPrepTimeUnit()));
            ps.setDouble(6, recipe.getCookingTime());
            ps.setString(7, String.valueOf(recipe.getTimeUnit()));
            ps.setString(8, recipe.getHeatLevel().name());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    recipeId = rs.getInt(1);
                }else {
                    throw new SQLException("No recipe ID was generated.");
                }
                
            }
            
        }
        insertIngredients(conn, recipeId, recipe);
        insertInstructions(conn, recipeId, recipe);
        
    }
    
    private void insertNoCookRecipe(Connection conn, NoCookRecipe recipe) throws SQLException{
        int recipeId;
        String sql="INSERT INTO RECIPE" +
                    "(NAME, CATEGORY, RECIPE_TYPE, PREP_TIME, PREP_TIME_UNIT," +
                    " CHILL_TIME, CHILL_TIME_UNIT)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, recipe.getName());
            ps.setString(2, recipe.getCategory().name());
            ps.setString(3, recipe.getRecipeType());
            ps.setDouble(4, recipe.getPrepTime());
            ps.setString(5, String.valueOf(recipe.getPrepTimeUnit()));
            ps.setDouble(6, recipe.getChillTime());
            ps.setString(7, String.valueOf(recipe.getChillTimeUnit()));

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    recipeId = rs.getInt(1);
                }else {
                    throw new SQLException("No recipe ID was generated.");
                }
                
            }
            
        }
        insertIngredients(conn, recipeId, recipe);
        insertInstructions(conn, recipeId, recipe);
        
    }
    
    private void insertIngredients(Connection conn, int recipeId, Recipe recipe) throws SQLException{
        
        String sql = "INSERT INTO INGREDIENT " +
                     "(RECIPE_ID, NAME, QUANTITY, UNIT, INGREDIENT_ORDER) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            int order = 1;

            for (Ingredient ingredient : recipe.getIngredients()) {
                ps.setInt(1, recipeId);
                ps.setString(2, ingredient.getName());
                ps.setDouble(3, ingredient.getQuantity());
                ps.setString(4, ingredient.getUnit());
                ps.setInt(5, order);

                ps.executeUpdate();
                order++;
            }
        }

        
    }
    
    private void insertInstructions(Connection conn, int recipeId, Recipe recipe) throws SQLException{
        String sql = "INSERT INTO INSTRUCTIONS " +
                     "(RECIPE_ID, INSTRUCTION_ORDER, INSTRUCTION) " +
                     "VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            int order = 1;

            for (String instruction : recipe.getInstructions()) {
                ps.setInt(1, recipeId);
                ps.setInt(2, order);
                ps.setString(3, instruction);

                ps.executeUpdate();
                order++;
            }
        }
        
        
    }
        
    public List<Recipe> viewRecipes() throws SQLException{
        
        List<Recipe> list = new ArrayList<>();
        String sql="SELECT * FROM RECIPE";
        
        try (Connection conn = connectDatabase();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Recipe recipe = buildRecipe(conn, rs);

                
                list.add(recipe);
            }
        }

        return list;
    }
    
    
    private void loadIngredients(Connection conn, int recipeId, Recipe recipe) throws SQLException {

        String sql = "SELECT NAME, QUANTITY, UNIT FROM INGREDIENT " +
                     "WHERE RECIPE_ID = ? ORDER BY INGREDIENT_ORDER";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, recipeId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    String name = rs.getString("NAME");
                    double quantity = rs.getDouble("QUANTITY");
                    String unit = rs.getString("UNIT");

                    recipe.addIngredient(name, quantity, unit);
                }
            }
        }
    }
    
    private void loadInstructions(Connection conn, int recipeId, Recipe recipe) throws SQLException {

        String sql = "SELECT INSTRUCTION FROM INSTRUCTIONS " +
                     "WHERE RECIPE_ID = ? ORDER BY INSTRUCTION_ORDER";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, recipeId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    recipe.addInstruction(rs.getString("INSTRUCTION"));
                }
            }
        }
    }
    
    public Recipe getRecipe(String name) throws SQLException{
        String sql="SELECT * FROM RECIPE WHERE LOWER(NAME) = LOWER(?)";
        
        try (Connection conn = connectDatabase();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            
            ps.setString(1, name);
            
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return buildRecipe(conn, rs);
                }
            }
        }
        return null;
        
    }  
    
    private Recipe buildRecipe(Connection conn, ResultSet rs) throws SQLException{
        int recipeId = rs.getInt("ID");
        String name = rs.getString("NAME");
        String type = rs.getString("RECIPE_TYPE").toLowerCase();
        String recipeCategory=rs.getString("CATEGORY");

        RecipeCategory category = RecipeCategory.valueOf(recipeCategory);

        RecipeFactory recipeFactory = new RecipeFactory();        
        Recipe recipe = recipeFactory.createRecipe(name, type, category);

        if (recipe == null) {
            throw new SQLException("Unknown recipe type: " + type);
        }

                
        double prepTime = rs.getDouble("PREP_TIME");
        char prepUnit = rs.getString("PREP_TIME_UNIT").charAt(0);

        recipe.setPrepDetails(prepTime, prepUnit);

                
        if (recipe instanceof BakingRecipe) {

            BakingRecipe bakingRecipe = (BakingRecipe) recipe;

            int ovenTemperature = rs.getInt("OVEN_TEMPERATURE");
            char temperatureUnit =rs.getString("TEMPERATURE_UNIT").charAt(0);

            double bakingTime = rs.getDouble("BAKING_TIME");
            char bakingTimeUnit =rs.getString("BAKING_TIME_UNIT").charAt(0);
            String ovenMode=rs.getString("OVEN_MODE");
            OvenMode mode = OvenMode.valueOf(ovenMode);

            bakingRecipe.setBakingDetails(ovenTemperature, temperatureUnit,bakingTime, bakingTimeUnit, mode);

        } else if (recipe instanceof StoveTopRecipe) {

            StoveTopRecipe stoveTopRecipe = (StoveTopRecipe) recipe;

            double cookingTime = rs.getDouble("COOKING_TIME");
            char cookingTimeUnit =rs.getString("COOKING_TIME_UNIT").charAt(0);

            String heat=rs.getString("HEAT_LEVEL");
                    
            HeatLevel heatLevel = HeatLevel.valueOf(heat);

            stoveTopRecipe.setStoveTopRecipeDetails(heatLevel, cookingTime, cookingTimeUnit);

        } else if (recipe instanceof NoCookRecipe) {

            NoCookRecipe noCookRecipe = (NoCookRecipe) recipe;

            double chillTime = rs.getDouble("CHILL_TIME");
            char chillTimeUnit =rs.getString("CHILL_TIME_UNIT").charAt(0);

            noCookRecipe.setChillTimeDetails(chillTime, chillTimeUnit);
        }

                
        loadIngredients(conn, recipeId, recipe);
        loadInstructions(conn, recipeId, recipe);

        
        return recipe;
        
    }
    
    public List<Recipe> searchByName(String word) throws SQLException {

        List<Recipe> list = new ArrayList<>();

        String sql = "SELECT * FROM RECIPE WHERE LOWER(NAME) LIKE LOWER(?)";

        try (Connection conn = connectDatabase();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + word + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Recipe recipe = buildRecipe(conn, rs);
                    list.add(recipe);
                }
            }
        }

        return list;
    }

    
    public List<Recipe> searchByCategory(RecipeCategory category) throws SQLException {

        List<Recipe> list = new ArrayList<>();

        String sql = "SELECT * FROM RECIPE WHERE CATEGORY = ?";

        try (Connection conn = connectDatabase();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, category.name());

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Recipe recipe = buildRecipe(conn, rs);
                    list.add(recipe);
                }
            }
        }

        return list;
    }
    
    public boolean removeRecipe(String name) throws SQLException {

        String findSql = "SELECT ID FROM RECIPE WHERE LOWER(NAME) = LOWER(?)";

        try (Connection conn = connectDatabase()) {
            conn.setAutoCommit(false);

            try {
                int recipeId;

                try (PreparedStatement ps = conn.prepareStatement(findSql)) {
                    ps.setString(1, name);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            conn.rollback();
                            return false;
                        }
                        recipeId = rs.getInt("ID");
                    }
                }

                String sql1 = "DELETE FROM INGREDIENT WHERE RECIPE_ID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql1)) {
                    ps.setInt(1, recipeId);
                    ps.executeUpdate();
                }

                String sql2 = "DELETE FROM INSTRUCTIONS WHERE RECIPE_ID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql2)) {
                    ps.setInt(1, recipeId);
                    ps.executeUpdate();
                }

                String sql3 = "DELETE FROM FAVOURITE WHERE RECIPE_ID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql3)) {
                    ps.setInt(1, recipeId);
                    ps.executeUpdate();
                }

                String sql4 = "DELETE FROM WEEKLY_MEAL_PLAN WHERE RECIPE_ID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql4)) {
                    ps.setInt(1, recipeId);
                    ps.executeUpdate();
                }

                String sql5 = "DELETE FROM RECIPE WHERE ID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql5)) {
                    ps.setInt(1, recipeId);
                    ps.executeUpdate();
                }
                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    
    public boolean editRecipe(String oldName, Recipe updatedRecipe) throws SQLException {

        String findSql = "SELECT ID FROM RECIPE WHERE LOWER(NAME) = LOWER(?)";

        try (Connection conn = connectDatabase()) {
            conn.setAutoCommit(false);

            try {
                int recipeId;

                
                try (PreparedStatement ps = conn.prepareStatement(findSql)) {
                    ps.setString(1, oldName);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            conn.rollback();
                            return false;
                        }
                        recipeId = rs.getInt("ID");
                    }
                }

                
                String checkSql = "SELECT ID FROM RECIPE WHERE LOWER(NAME) = LOWER(?) AND ID <> ?";

                try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                    ps.setString(1, updatedRecipe.getName());
                    ps.setInt(2, recipeId);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            conn.rollback();
                            return false;
                        }
                    }
                }

                // 3. Update common fields and clear old type-specific fields
                String updateSql =
                    "UPDATE RECIPE SET NAME = ?, CATEGORY = ?, RECIPE_TYPE = ?, " +
                    "PREP_TIME = ?, PREP_TIME_UNIT = ?, " +
                    "OVEN_TEMPERATURE = NULL, TEMPERATURE_UNIT = NULL, " +
                    "BAKING_TIME = NULL, BAKING_TIME_UNIT = NULL, OVEN_MODE = NULL, " +
                    "COOKING_TIME = NULL, COOKING_TIME_UNIT = NULL, HEAT_LEVEL = NULL, " +
                    "CHILL_TIME = NULL, CHILL_TIME_UNIT = NULL " +
                    "WHERE ID = ?";

                try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                    ps.setString(1, updatedRecipe.getName());
                    ps.setString(2, updatedRecipe.getCategory().name());
                    ps.setString(3, updatedRecipe.getRecipeType());
                    ps.setDouble(4, updatedRecipe.getPrepTime());
                    ps.setString(5, String.valueOf(updatedRecipe.getPrepTimeUnit()));
                    ps.setInt(6, recipeId);

                    ps.executeUpdate();
                }

                
                if (updatedRecipe instanceof BakingRecipe) {
                    updateBakingRecipe(conn, recipeId, (BakingRecipe) updatedRecipe);

                } else if (updatedRecipe instanceof StoveTopRecipe) {
                    updateStoveTopRecipe(conn, recipeId, (StoveTopRecipe) updatedRecipe);

                } else if (updatedRecipe instanceof NoCookRecipe) {
                    updateNoCookRecipe(conn, recipeId, (NoCookRecipe) updatedRecipe);

                } else {
                    throw new SQLException("Unsupported recipe type.");
                }

                
                String deleteIngredients = "DELETE FROM INGREDIENT WHERE RECIPE_ID = ?";

                try (PreparedStatement ps = conn.prepareStatement(deleteIngredients)) {
                    ps.setInt(1, recipeId);
                    ps.executeUpdate();
                }

                
                String deleteInstructions = "DELETE FROM INSTRUCTIONS WHERE RECIPE_ID = ?";

                try (PreparedStatement ps = conn.prepareStatement(deleteInstructions)) {
                    ps.setInt(1, recipeId);
                    ps.executeUpdate();
                }

                
                insertIngredients(conn, recipeId, updatedRecipe);
                insertInstructions(conn, recipeId, updatedRecipe);

                conn.commit();
                return true;

            } catch (SQLException | RuntimeException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    private void updateBakingRecipe(Connection conn, int recipeId, BakingRecipe recipe) throws SQLException {

        String sql = "UPDATE RECIPE SET OVEN_TEMPERATURE = ?, TEMPERATURE_UNIT = ?, " +
                     "BAKING_TIME = ?, BAKING_TIME_UNIT = ?, OVEN_MODE = ? " +
                     "WHERE ID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recipe.getOvenTemperature());
            ps.setString(2, String.valueOf(recipe.getTemperatureUnit()));
            ps.setDouble(3, recipe.getBakingTime());
            ps.setString(4, String.valueOf(recipe.getBakingTimeUnit()));
            ps.setString(5, recipe.getMode().name());
            ps.setInt(6, recipeId);

            ps.executeUpdate();
        }
    }
    private void updateStoveTopRecipe(Connection conn, int recipeId, StoveTopRecipe recipe) throws SQLException {

        String sql = "UPDATE RECIPE SET COOKING_TIME = ?, COOKING_TIME_UNIT = ?, " +
                     "HEAT_LEVEL = ? WHERE ID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, recipe.getCookingTime());
            ps.setString(2, String.valueOf(recipe.getTimeUnit()));
            ps.setString(3, recipe.getHeatLevel().name());
            ps.setInt(4, recipeId);

            ps.executeUpdate();
        }
    }
    
    private void updateNoCookRecipe(Connection conn, int recipeId, NoCookRecipe recipe) throws SQLException {

        String sql = "UPDATE RECIPE SET CHILL_TIME = ?, CHILL_TIME_UNIT = ? WHERE ID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, recipe.getChillTime());
            ps.setString(2, String.valueOf(recipe.getChillTimeUnit()));
            ps.setInt(3, recipeId);

            ps.executeUpdate();
        }
    }
    
    
    
    
    
}
