/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package recipeorganiser;
import java.sql.SQLException;
/**
 *
 * @author chaimaoueslati
 */
public class RecipeOrganiser {

    /**
     * @param args the command line arguments
     */
    //Starts the Recipe Organiser application and connects the main classes.
    public static void main(String[] args) throws SQLException{
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
                   break;
               }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
           ex.printStackTrace();
        }
        RecipeDatabase database = new RecipeDatabase();
        database.createTables();
        
        RecipeManager manager = new RecipeManager(database);

        RecipeFactory factory = new RecipeFactory();
        
        java.awt.EventQueue.invokeLater(() -> {
            RecipeMainFrame gui = new RecipeMainFrame(manager, factory);
            gui.setVisible(true);
        });
        
    }
    
}
