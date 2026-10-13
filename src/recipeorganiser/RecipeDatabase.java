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
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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
                    " VALUES (?, ?, ?, ?, ?, ?, ?);";
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
        
    

    
    
    
    
    
    
    
    
    
}
