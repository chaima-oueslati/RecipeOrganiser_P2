/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package recipeorganiser;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.JTableHeader;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chaimaoueslati
 */
public class RecipeMainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecipeMainFrame.class.getName());
    private RecipeManager manager;
    private RecipeFactory factory;
    private List<Ingredient> ingredients = new ArrayList<>();
    private DefaultListModel<String> ingredientListModel = new DefaultListModel<>();
    private JPanel recipeCardsPanel = new JPanel();
    private Recipe selectedRecipe;
    private boolean editingRecipe = false;
    private String previousPage = "RECIPES";

    /**
     * Creates new form RecipeMainFrame
     */
    public RecipeMainFrame(RecipeManager manager, RecipeFactory factory) {
        initComponents();
        dashboardCardsPanel.setLayout(new GridLayout(1, 3, 15, 15));
        this.manager = manager;
        this.factory = factory;
        styleMealPlanTable();
        loadDashboard();
        setupMealPlanTable();
        loadMealPlan();
        ingredientJList.setModel(ingredientListModel);
        
        recipeCardsPanel.setLayout(new GridLayout(0, 3, 15, 15));
        recipeCardsPanel.setBackground(recipesPanel.getBackground());

        JPanel outerPanel = new JPanel(new BorderLayout());

        outerPanel.setBackground(recipesPanel.getBackground());
        outerPanel.add(recipeCardsPanel, BorderLayout.NORTH);

        recipesScrollPane.setViewportView(outerPanel);
        setSize(1500, 1500);
        setLocationRelativeTo(null);
        
        
    }
    
    

    
    // AI-generated: The JTable styling method was provided by ChatGPT
    // and used to customise the appearance of the weekly meal planner.
    // This method changes the appearance of an existing table.
    // It does not create the table or implement click handling.
    private void styleMealPlanTable() {

        // PART 1: Style the table and its grid lines.
        mealPlanTable.setRowHeight(65);
        mealPlanTable.setBackground(Color.WHITE);
        mealPlanTable.setForeground(new Color(48, 82, 61));
        mealPlanTable.setFont(new Font("SansSerif", Font.PLAIN, 15));
        mealPlanTable.setGridColor(new Color(210, 222, 210));
        mealPlanTable.setShowGrid(true);
        mealPlanTable.setRowSelectionAllowed(false);
        mealPlanTable.setCellSelectionEnabled(false);
        mealPlanTable.setFillsViewportHeight(false);

        // PART 2: Style the column headings.
        JTableHeader header = mealPlanTable.getTableHeader();
        header.setPreferredSize(new Dimension(0, 55));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer();

        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(new Color(232, 241, 232));
        headerRenderer.setForeground(new Color(48, 82, 61));
        headerRenderer.setFont(new Font("SansSerif", Font.BOLD, 16));

        headerRenderer.setBorder(
                BorderFactory.createLineBorder(new Color(210, 222, 210))
        );

        for (int column = 0; column < mealPlanTable.getColumnCount(); column++) {
            mealPlanTable.getColumnModel()
                    .getColumn(column)
                    .setHeaderRenderer(headerRenderer);
        }

        // PART 3: Centre and style Monday–Sunday.
        DefaultTableCellRenderer dayRenderer =
                new DefaultTableCellRenderer();

        dayRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        dayRenderer.setBackground(Color.WHITE);
        dayRenderer.setForeground(new Color(48, 82, 61));
        dayRenderer.setFont(new Font("SansSerif", Font.BOLD, 15));

        mealPlanTable.getColumnModel()
                .getColumn(0)
                .setCellRenderer(dayRenderer);

        
        TableCellRenderer mealRenderer = new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                JPanel cellPanel = new JPanel(new GridBagLayout());
                cellPanel.setBackground(Color.WHITE);

                JLabel mealLabel = new JLabel(
                        value == null ? "" : value.toString()
                );

                mealLabel.setHorizontalAlignment(SwingConstants.CENTER);
                mealLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
                mealLabel.setForeground(new Color(48, 82, 61));

                JPanel roundedBox = new JPanel(new BorderLayout()) {

                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        g2.setColor(new Color(240, 245, 239));

                        g2.fillRoundRect(
                                0, 0, getWidth(), getHeight(), 16, 16
                        );

                        g2.dispose();
                    }
                };

                roundedBox.setOpaque(false);
                roundedBox.setPreferredSize(new Dimension(150, 43));
                roundedBox.add(mealLabel, BorderLayout.CENTER);

                cellPanel.add(roundedBox);

                return cellPanel;
            }
        };

        
        for (int column = 1; column < mealPlanTable.getColumnCount(); column++) {
            mealPlanTable.getColumnModel()
                    .getColumn(column)
                    .setCellRenderer(mealRenderer);
        }

        
        mealPlanScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mealPlanScrollPane.getViewport().setBackground(Color.WHITE);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebarPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        homeButton = new javax.swing.JButton();
        myRecipesButton = new javax.swing.JButton();
        addRecipeButton = new javax.swing.JButton();
        favouritesButton = new javax.swing.JButton();
        mealPlanButton = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        dashboardPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dashboardCardsPanel = new javax.swing.JPanel();
        totalRecipesPanel = new javax.swing.JPanel();
        totalRecipesLabel = new javax.swing.JLabel();
        totalRecipesNumber = new javax.swing.JLabel();
        totalRecipesDescription = new javax.swing.JLabel();
        totalFavouritesPanel = new javax.swing.JPanel();
        totalFavouritesLabel = new javax.swing.JLabel();
        totalFavouriteNumber = new javax.swing.JLabel();
        totalFavouriteDescription = new javax.swing.JLabel();
        viewAllButton = new javax.swing.JButton();
        recipesPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        recipesSearchField = new javax.swing.JTextField();
        categoryComboBox = new javax.swing.JComboBox<>();
        TypeByComboBox = new javax.swing.JComboBox<>();
        recipesScrollPane = new javax.swing.JScrollPane();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        addRecipePanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        recipeNameField = new javax.swing.JTextField();
        recipeTypeComboBox = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        recipeCategoryComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        prepTimeField = new javax.swing.JTextField();
        prepTimeUnitComboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cookingDetailsPanel = new javax.swing.JPanel();
        bakingDetailsPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        ovenTemperatureField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        temperatureUnitComboBox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        bakingTimeField = new javax.swing.JTextField();
        bakingTimeUnitComboBox = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        ovenModeComboBox = new javax.swing.JComboBox<>();
        stoveDetailsPanel = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cookingTimeField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cookingTimeUnitComboBox = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        heatLevelComboBox = new javax.swing.JComboBox<>();
        noCookDetailsPanel = new javax.swing.JPanel();
        chillTimeField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        chillTimeUnitComboBox = new javax.swing.JComboBox<>();
        ingredientsPanel = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        ingredientNameField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        ingredientQuantityField = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        ingredientUnitField = new javax.swing.JTextField();
        addIngredientButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ingredientJList = new javax.swing.JList<>();
        removeIngredientButton = new javax.swing.JButton();
        instructionsPanel = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        instructionsScrollPane = new javax.swing.JScrollPane();
        instructionsTextArea = new javax.swing.JTextArea();
        cancelRecipeButton = new javax.swing.JButton();
        saveRecipeButton = new javax.swing.JButton();
        favouritesPanel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        favouritesSearchField = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        favouritesTypeComboBox = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        favouritesCategoryComboBox = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        favouritesScrollPane = new javax.swing.JScrollPane();
        favouritesListPanel = new javax.swing.JPanel();
        weeklyMealPlanPanel = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        clearPlanButton = new javax.swing.JButton();
        savePlanButton = new javax.swing.JButton();
        mealPlanScrollPane = new javax.swing.JScrollPane();
        mealPlanTable = new javax.swing.JTable();
        recipeDetailsPanel = new javax.swing.JPanel();
        backToRecipesButton = new javax.swing.JButton();
        recipeDetailsImageLabel = new javax.swing.JLabel();
        recipeDetailsNameLabel = new javax.swing.JLabel();
        recipeDetailsFavouriteButton = new javax.swing.JToggleButton();
        recipeDetailsTypeLabel = new javax.swing.JLabel();
        recipeDetailsTimeLabel = new javax.swing.JLabel();
        ingredientsTabButton = new javax.swing.JButton();
        instructionsTabButton = new javax.swing.JButton();
        detailsTabButton = new javax.swing.JButton();
        editRecipeButton = new javax.swing.JButton();
        deleteRecipeButton = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        recipeDetailsCategoryLabel = new javax.swing.JLabel();
        recipeInfoPanel = new javax.swing.JPanel();
        ingredientsInfoPanel = new javax.swing.JPanel();
        ingredientsHeadingLabel = new javax.swing.JLabel();
        ingredientsScrollPane = new javax.swing.JScrollPane();
        recipeIngredientsList = new javax.swing.JList<>();
        instructionsInfoPanel = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        instructionScrollPane = new javax.swing.JScrollPane();
        recipeInstructionsTextArea = new javax.swing.JTextArea();
        cookingInfoPanel = new javax.swing.JPanel();
        cookingDetailsHeadingLabel = new javax.swing.JLabel();
        cookingDetailOneLabel = new javax.swing.JLabel();
        cookingDetailTwoLabel = new javax.swing.JLabel();
        cookingDetailThreeLabel = new javax.swing.JLabel();
        cookingDetailFourLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recipe Organiser");
        setBackground(new java.awt.Color(247, 248, 245));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        sidebarPanel.setBackground(new java.awt.Color(232, 241, 232));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(48, 82, 61));
        jLabel5.setText("<html>Recipe<br>Organiser</html>");

        homeButton.setBackground(new java.awt.Color(145, 177, 156));
        homeButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeButton.setForeground(new java.awt.Color(255, 255, 255));
        homeButton.setText("Home");
        homeButton.setBorderPainted(false);
        homeButton.addActionListener(this::homeButtonActionPerformed);

        myRecipesButton.setBackground(new java.awt.Color(232, 241, 232));
        myRecipesButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        myRecipesButton.setForeground(new java.awt.Color(48, 82, 61));
        myRecipesButton.setText("My Recipes");
        myRecipesButton.setBorderPainted(false);
        myRecipesButton.addActionListener(this::myRecipesButtonActionPerformed);

        addRecipeButton.setBackground(new java.awt.Color(232, 241, 232));
        addRecipeButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        addRecipeButton.setForeground(new java.awt.Color(48, 82, 61));
        addRecipeButton.setText("Add Recipe");
        addRecipeButton.setBorderPainted(false);
        addRecipeButton.addActionListener(this::addRecipeButtonActionPerformed);

        favouritesButton.setBackground(new java.awt.Color(232, 241, 232));
        favouritesButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        favouritesButton.setForeground(new java.awt.Color(48, 82, 61));
        favouritesButton.setText("Favourites");
        favouritesButton.setBorderPainted(false);
        favouritesButton.addActionListener(this::favouritesButtonActionPerformed);

        mealPlanButton.setBackground(new java.awt.Color(232, 241, 232));
        mealPlanButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        mealPlanButton.setForeground(new java.awt.Color(48, 82, 61));
        mealPlanButton.setText("Meal Plan");
        mealPlanButton.setBorderPainted(false);
        mealPlanButton.addActionListener(this::mealPlanButtonActionPerformed);

        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarPanelLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(favouritesButton)
                        .addComponent(addRecipeButton)
                        .addComponent(myRecipesButton)
                        .addComponent(mealPlanButton))
                    .addGroup(sidebarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(65, 65, 65))
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(homeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(homeButton)
                .addGap(26, 26, 26)
                .addComponent(myRecipesButton)
                .addGap(28, 28, 28)
                .addComponent(addRecipeButton)
                .addGap(26, 26, 26)
                .addComponent(favouritesButton)
                .addGap(30, 30, 30)
                .addComponent(mealPlanButton)
                .addContainerGap(385, Short.MAX_VALUE))
        );

        contentPanel.setBackground(new java.awt.Color(247, 248, 245));
        contentPanel.setPreferredSize(new java.awt.Dimension(2000, 1300));
        contentPanel.setLayout(new java.awt.CardLayout());

        dashboardPanel.setBackground(new java.awt.Color(247, 248, 245));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(48, 82, 61));
        jLabel1.setText("Welcome to Recipe Organiser!");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(105, 112, 105));
        jLabel3.setText("What would you like to cook today?");

        jLabel4.setFont(new java.awt.Font("SansSerif", 3, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(35, 52, 40));
        jLabel4.setText("   Your Recipes");

        dashboardCardsPanel.setBackground(new java.awt.Color(247, 248, 245));

        javax.swing.GroupLayout dashboardCardsPanelLayout = new javax.swing.GroupLayout(dashboardCardsPanel);
        dashboardCardsPanel.setLayout(dashboardCardsPanelLayout);
        dashboardCardsPanelLayout.setHorizontalGroup(
            dashboardCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        dashboardCardsPanelLayout.setVerticalGroup(
            dashboardCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );

        totalRecipesPanel.setBackground(new java.awt.Color(238, 242, 231));
        totalRecipesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        totalRecipesPanel.setPreferredSize(new java.awt.Dimension(290, 125));

        totalRecipesLabel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        totalRecipesLabel.setForeground(new java.awt.Color(82, 108, 77));
        totalRecipesLabel.setText("Total Recipes");

        totalRecipesNumber.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        totalRecipesNumber.setForeground(new java.awt.Color(35, 52, 40));
        totalRecipesNumber.setText("0");

        totalRecipesDescription.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        totalRecipesDescription.setForeground(new java.awt.Color(105, 112, 105));
        totalRecipesDescription.setText("Recipes in your collection");

        javax.swing.GroupLayout totalRecipesPanelLayout = new javax.swing.GroupLayout(totalRecipesPanel);
        totalRecipesPanel.setLayout(totalRecipesPanelLayout);
        totalRecipesPanelLayout.setHorizontalGroup(
            totalRecipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalRecipesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalRecipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalRecipesLabel)
                    .addComponent(totalRecipesNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalRecipesDescription))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        totalRecipesPanelLayout.setVerticalGroup(
            totalRecipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalRecipesPanelLayout.createSequentialGroup()
                .addComponent(totalRecipesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalRecipesNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(totalRecipesDescription))
        );

        totalFavouritesPanel.setBackground(new java.awt.Color(250, 235, 231));
        totalFavouritesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        totalFavouritesPanel.setPreferredSize(new java.awt.Dimension(290, 125));

        totalFavouritesLabel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        totalFavouritesLabel.setForeground(new java.awt.Color(163, 79, 72));
        totalFavouritesLabel.setText("Favourites");

        totalFavouriteNumber.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        totalFavouriteNumber.setForeground(new java.awt.Color(68, 45, 42));
        totalFavouriteNumber.setText("0");

        totalFavouriteDescription.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        totalFavouriteDescription.setForeground(new java.awt.Color(135, 109, 105));
        totalFavouriteDescription.setText("Recipes you love");

        javax.swing.GroupLayout totalFavouritesPanelLayout = new javax.swing.GroupLayout(totalFavouritesPanel);
        totalFavouritesPanel.setLayout(totalFavouritesPanelLayout);
        totalFavouritesPanelLayout.setHorizontalGroup(
            totalFavouritesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalFavouritesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalFavouritesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalFavouritesLabel)
                    .addComponent(totalFavouriteDescription)
                    .addComponent(totalFavouriteNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        totalFavouritesPanelLayout.setVerticalGroup(
            totalFavouritesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalFavouritesPanelLayout.createSequentialGroup()
                .addComponent(totalFavouritesLabel)
                .addGap(18, 18, 18)
                .addComponent(totalFavouriteNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalFavouriteDescription))
        );

        viewAllButton.setBackground(new java.awt.Color(247, 248, 245));
        viewAllButton.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        viewAllButton.setForeground(new java.awt.Color(48, 82, 61));
        viewAllButton.setText("View All →");
        viewAllButton.setBorderPainted(false);
        viewAllButton.setContentAreaFilled(false);
        viewAllButton.setFocusPainted(false);
        viewAllButton.addActionListener(this::viewAllButtonActionPerformed);

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dashboardPanelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(totalRecipesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(totalFavouritesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dashboardPanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(493, 493, 493)
                                .addComponent(viewAllButton))
                            .addComponent(dashboardCardsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 636, Short.MAX_VALUE))
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel2))
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(54, 54, 54)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalRecipesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalFavouritesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardCardsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        contentPanel.add(dashboardPanel, "DASHBOARD");

        recipesPanel.setBackground(new java.awt.Color(247, 248, 245));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jLabel6.setText("My Recipes");

        recipesSearchField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        recipesSearchField.setToolTipText("Search recipes by name");
        recipesSearchField.addActionListener(this::recipesSearchFieldActionPerformed);

        categoryComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        categoryComboBox.setForeground(new java.awt.Color(102, 102, 102));
        categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Appetiser", "Breakfast", "Main Course", "Dessert", "Snack", "Drinks", "Sauce" }));
        categoryComboBox.addActionListener(this::categoryComboBoxActionPerformed);

        TypeByComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        TypeByComboBox.setForeground(new java.awt.Color(102, 102, 102));
        TypeByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Baking", "Stove top", "No cook" }));
        TypeByComboBox.addActionListener(this::TypeByComboBoxActionPerformed);

        recipesScrollPane.setBackground(new java.awt.Color(247, 245, 248));
        recipesScrollPane.setBorder(null);

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(48, 82, 61));
        jLabel7.setText("Category");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(48, 82, 61));
        jLabel8.setText("Recipe Type");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(48, 60, 52));
        jLabel9.setText("Search recipes");

        javax.swing.GroupLayout recipesPanelLayout = new javax.swing.GroupLayout(recipesPanel);
        recipesPanel.setLayout(recipesPanelLayout);
        recipesPanelLayout.setHorizontalGroup(
            recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipesPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recipesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(recipesPanelLayout.createSequentialGroup()
                        .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(recipesSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(recipesPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(249, 249, 249)))
                        .addGap(143, 143, 143)
                        .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TypeByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(73, 73, 73)
                        .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap(648, Short.MAX_VALUE))
        );
        recipesPanelLayout.setVerticalGroup(
            recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipesPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TypeByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recipesSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(recipesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        contentPanel.add(recipesPanel, "RECIPES");

        addRecipePanel.setBackground(new java.awt.Color(247, 248, 245));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel10.setText("Add New Recipe ");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(48, 82, 61));
        jLabel11.setText("Recipe Name *");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(48, 82, 61));
        jLabel12.setText("Recipe Type *");

        recipeNameField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

        recipeTypeComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        recipeTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baking", "Stove Top", "No Cook" }));
        recipeTypeComboBox.addActionListener(this::recipeTypeComboBoxActionPerformed);

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(48, 82, 61));
        jLabel13.setText("Category *");

        recipeCategoryComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        recipeCategoryComboBox.setForeground(new java.awt.Color(102, 102, 102));
        recipeCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Appetiser", "breakfast", "Main course", "Dessert", "Snack", "Drinks", "Sauce" }));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(48, 82, 61));
        jLabel14.setText("Preparation Time *");

        prepTimeField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        prepTimeField.addActionListener(this::prepTimeFieldActionPerformed);

        prepTimeUnitComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        prepTimeUnitComboBox.setForeground(new java.awt.Color(102, 102, 102));
        prepTimeUnitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minutes", "Hours" }));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(48, 82, 61));
        jLabel15.setText("Cooking Details");

        cookingDetailsPanel.setPreferredSize(new java.awt.Dimension(650, 160));
        cookingDetailsPanel.setLayout(new java.awt.CardLayout());

        bakingDetailsPanel.setBackground(new java.awt.Color(247, 248, 245));

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(48, 82, 61));
        jLabel16.setText("Oven Temperature *");

        ovenTemperatureField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(48, 82, 61));
        jLabel17.setText("Temperature Unit *");

        temperatureUnitComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        temperatureUnitComboBox.setForeground(new java.awt.Color(102, 102, 102));
        temperatureUnitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "°C", "°F" }));

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(48, 82, 61));
        jLabel18.setText("Baking Time *");

        bakingTimeField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

        bakingTimeUnitComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        bakingTimeUnitComboBox.setForeground(new java.awt.Color(102, 102, 102));
        bakingTimeUnitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minutes", "Hours", " " }));

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(48, 82, 61));
        jLabel20.setText("Oven Mode *");

        ovenModeComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        ovenModeComboBox.setForeground(new java.awt.Color(102, 102, 102));
        ovenModeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conventional", "Fan Forced", "Grill" }));
        ovenModeComboBox.addActionListener(this::ovenModeComboBoxActionPerformed);

        javax.swing.GroupLayout bakingDetailsPanelLayout = new javax.swing.GroupLayout(bakingDetailsPanel);
        bakingDetailsPanel.setLayout(bakingDetailsPanelLayout);
        bakingDetailsPanelLayout.setHorizontalGroup(
            bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bakingDetailsPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bakingDetailsPanelLayout.createSequentialGroup()
                        .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ovenTemperatureField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(107, 107, 107)
                        .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(temperatureUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(91, 91, 91)
                        .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(bakingTimeField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(bakingTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20)
                    .addComponent(ovenModeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(728, Short.MAX_VALUE))
        );
        bakingDetailsPanelLayout.setVerticalGroup(
            bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bakingDetailsPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ovenTemperatureField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temperatureUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bakingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bakingTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ovenModeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        cookingDetailsPanel.add(bakingDetailsPanel, "BAKING");

        stoveDetailsPanel.setBackground(new java.awt.Color(247, 248, 245));

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(48, 82, 61));
        jLabel21.setText("Cooking Time *");

        cookingTimeField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cookingTimeField.addActionListener(this::cookingTimeFieldActionPerformed);

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(48, 82, 61));
        jLabel22.setText("Cooking Time Unit *");

        cookingTimeUnitComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cookingTimeUnitComboBox.setForeground(new java.awt.Color(102, 102, 102));
        cookingTimeUnitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minutes", "Hours" }));

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(48, 82, 61));
        jLabel23.setText("Heat Level *");

        heatLevelComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        heatLevelComboBox.setForeground(new java.awt.Color(102, 102, 102));
        heatLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", "High" }));

        javax.swing.GroupLayout stoveDetailsPanelLayout = new javax.swing.GroupLayout(stoveDetailsPanel);
        stoveDetailsPanel.setLayout(stoveDetailsPanelLayout);
        stoveDetailsPanelLayout.setHorizontalGroup(
            stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stoveDetailsPanelLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cookingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(141, 141, 141)
                .addGroup(stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(cookingTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(172, 172, 172)
                .addGroup(stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(heatLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(752, Short.MAX_VALUE))
        );
        stoveDetailsPanelLayout.setVerticalGroup(
            stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stoveDetailsPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cookingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookingTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(heatLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cookingDetailsPanel.add(stoveDetailsPanel, "STOVE_TOP");

        noCookDetailsPanel.setBackground(new java.awt.Color(247, 248, 245));

        chillTimeField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        chillTimeField.addActionListener(this::chillTimeFieldActionPerformed);

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(48, 82, 61));
        jLabel24.setText("Chill Time *");

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(48, 82, 61));
        jLabel25.setText("Chill Time Unit *");

        chillTimeUnitComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        chillTimeUnitComboBox.setForeground(new java.awt.Color(102, 102, 102));
        chillTimeUnitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minutes", "Hours" }));

        javax.swing.GroupLayout noCookDetailsPanelLayout = new javax.swing.GroupLayout(noCookDetailsPanel);
        noCookDetailsPanel.setLayout(noCookDetailsPanelLayout);
        noCookDetailsPanelLayout.setHorizontalGroup(
            noCookDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noCookDetailsPanelLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(noCookDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(chillTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(181, 181, 181)
                .addGroup(noCookDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(chillTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(983, Short.MAX_VALUE))
        );
        noCookDetailsPanelLayout.setVerticalGroup(
            noCookDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noCookDetailsPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(noCookDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(noCookDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chillTimeField)
                    .addComponent(chillTimeUnitComboBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cookingDetailsPanel.add(noCookDetailsPanel, "NO_COOK");

        ingredientsPanel.setBackground(new java.awt.Color(247, 248, 245));
        ingredientsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(48, 82, 61));
        jLabel26.setText("Ingredients");
        ingredientsPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(48, 82, 61));
        jLabel27.setText("Ingredient Name *");
        ingredientsPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        ingredientNameField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        ingredientNameField.addActionListener(this::ingredientNameFieldActionPerformed);
        ingredientsPanel.add(ingredientNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 120, -1));

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(48, 82, 61));
        jLabel28.setText("Quantity *");
        ingredientsPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));

        ingredientQuantityField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        ingredientQuantityField.addActionListener(this::ingredientQuantityFieldActionPerformed);
        ingredientsPanel.add(ingredientQuantityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, -1));

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(48, 79, 61));
        jLabel29.setText("Unit *");
        ingredientsPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        ingredientUnitField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        ingredientsPanel.add(ingredientUnitField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 80, -1));

        addIngredientButton.setBackground(new java.awt.Color(145, 177, 156));
        addIngredientButton.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        addIngredientButton.setForeground(new java.awt.Color(255, 255, 255));
        addIngredientButton.setText("+ Add Ingredient");
        addIngredientButton.addActionListener(this::addIngredientButtonActionPerformed);
        ingredientsPanel.add(addIngredientButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(60, 30));

        ingredientJList.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jScrollPane1.setViewportView(ingredientJList);

        ingredientsPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 450, 60));

        removeIngredientButton.setBackground(new java.awt.Color(145, 177, 156));
        removeIngredientButton.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        removeIngredientButton.setText("- Remove Selected");
        removeIngredientButton.addActionListener(this::removeIngredientButtonActionPerformed);
        ingredientsPanel.add(removeIngredientButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, -1, -1));

        instructionsPanel.setBackground(new java.awt.Color(247, 248, 245));
        instructionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(48, 82, 61));
        jLabel30.setText("Instructions");
        instructionsPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        instructionsTextArea.setColumns(20);
        instructionsTextArea.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setRows(5);
        instructionsTextArea.setWrapStyleWord(true);
        instructionsScrollPane.setViewportView(instructionsTextArea);

        instructionsPanel.add(instructionsScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 50, 480, 90));

        cancelRecipeButton.setBackground(new java.awt.Color(145, 177, 156));
        cancelRecipeButton.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        cancelRecipeButton.setText("Cancel");
        cancelRecipeButton.addActionListener(this::cancelRecipeButtonActionPerformed);
        instructionsPanel.add(cancelRecipeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, -1, -1));

        saveRecipeButton.setBackground(new java.awt.Color(145, 177, 156));
        saveRecipeButton.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        saveRecipeButton.setForeground(new java.awt.Color(255, 255, 255));
        saveRecipeButton.setText("Save Recipe");
        saveRecipeButton.addActionListener(this::saveRecipeButtonActionPerformed);
        instructionsPanel.add(saveRecipeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 110, -1));

        javax.swing.GroupLayout addRecipePanelLayout = new javax.swing.GroupLayout(addRecipePanel);
        addRecipePanel.setLayout(addRecipePanelLayout);
        addRecipePanelLayout.setHorizontalGroup(
            addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addRecipePanelLayout.createSequentialGroup()
                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addRecipePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cookingDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addRecipePanelLayout.createSequentialGroup()
                        .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addRecipePanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addRecipePanelLayout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(recipeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15))
                                        .addGap(55, 55, 55)
                                        .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(recipeTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(84, 84, 84)
                                        .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(recipeCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(97, 97, 97)
                                        .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addGroup(addRecipePanelLayout.createSequentialGroup()
                                                .addComponent(prepTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(47, 47, 47)
                                                .addComponent(prepTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(addRecipePanelLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(instructionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ingredientsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 564, Short.MAX_VALUE)))
                .addContainerGap())
        );
        addRecipePanelLayout.setVerticalGroup(
            addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addRecipePanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addGap(37, 37, 37)
                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recipeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recipeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recipeCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prepTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prepTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookingDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ingredientsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(instructionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        contentPanel.add(addRecipePanel, "ADD_RECIPE");

        favouritesPanel.setBackground(new java.awt.Color(247, 248, 245));
        favouritesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        jLabel31.setText("My Favourites");
        favouritesPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel32.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(105, 115, 105));
        jLabel32.setText("Your saved recipes, all in one place.");
        favouritesPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        favouritesSearchField.addActionListener(this::favouritesSearchFieldActionPerformed);
        favouritesPanel.add(favouritesSearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 250, 30));

        jLabel33.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel33.setText("Search favourites...");
        favouritesPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        favouritesTypeComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        favouritesTypeComboBox.setForeground(new java.awt.Color(102, 102, 102));
        favouritesTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Baking", "Stove Top", "No Cook" }));
        favouritesTypeComboBox.addActionListener(this::favouritesTypeComboBoxActionPerformed);
        favouritesPanel.add(favouritesTypeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 100, 30));

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(48, 82, 61));
        jLabel34.setText("Recipe Type");
        favouritesPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, -1, -1));

        favouritesCategoryComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        favouritesCategoryComboBox.setForeground(new java.awt.Color(102, 102, 102));
        favouritesCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Appetiser", "Breakfast", "Main course", "Dessert", "Snack", "Drinks", "Sauce" }));
        favouritesCategoryComboBox.addActionListener(this::favouritesCategoryComboBoxActionPerformed);
        favouritesPanel.add(favouritesCategoryComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, 110, 30));

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(48, 82, 61));
        jLabel35.setText("Category");
        favouritesPanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, -1));

        favouritesScrollPane.setBackground(new java.awt.Color(247, 248, 245));
        favouritesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        favouritesListPanel.setBackground(new java.awt.Color(247, 248, 245));
        favouritesListPanel.setLayout(new java.awt.GridLayout(0, 3, 15, 15));
        favouritesScrollPane.setViewportView(favouritesListPanel);

        favouritesPanel.add(favouritesScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 740, 410));

        contentPanel.add(favouritesPanel, "FAVOURITES");

        weeklyMealPlanPanel.setBackground(new java.awt.Color(247, 248, 245));
        weeklyMealPlanPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        jLabel36.setText("Weekly Meal Plan");
        weeklyMealPlanPanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        clearPlanButton.setBackground(new java.awt.Color(145, 177, 156));
        clearPlanButton.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        clearPlanButton.setText("Clear All");
        clearPlanButton.addActionListener(this::clearPlanButtonActionPerformed);
        weeklyMealPlanPanel.add(clearPlanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 100, 40));

        savePlanButton.setBackground(new java.awt.Color(145, 177, 156));
        savePlanButton.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        savePlanButton.setForeground(new java.awt.Color(255, 255, 255));
        savePlanButton.setText("Save Plan");
        savePlanButton.addActionListener(this::savePlanButtonActionPerformed);
        weeklyMealPlanPanel.add(savePlanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 100, 40));

        mealPlanScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mealPlanScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        mealPlanTable.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        mealPlanTable.setForeground(new java.awt.Color(48, 82, 61));
        mealPlanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"  Monday", "+ Add", "+ Add", "+ Add"},
                {"  Tuesday", "+ Add", "+ Add", "+ Add"},
                {"  Wednesday", "+ Add", "+ Add", "+ Add"},
                {"  Thursday", "+ Add", "+ Add", "+ Add"},
                {"  Friday", "+ Add", "+ Add", "+ Add"},
                {"  Saturday", "+ Add", "+ Add", "+ Add"},
                {"  Sunday", "+ Add", "+ Add", "+ Add"}
            },
            new String [] {
                "Day", "Breakfast", "Lunch", "Dinner"
            }
        ));
        mealPlanTable.setGridColor(new java.awt.Color(210, 222, 210));
        mealPlanTable.setRowHeight(65);
        mealPlanTable.setRowSelectionAllowed(false);
        mealPlanTable.setSelectionBackground(new java.awt.Color(232, 241, 232));
        mealPlanTable.setShowHorizontalLines(true);
        mealPlanTable.setShowVerticalLines(true);
        mealPlanScrollPane.setViewportView(mealPlanTable);

        weeklyMealPlanPanel.add(mealPlanScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 550, 500));

        contentPanel.add(weeklyMealPlanPanel, "MEAL_PLAN");

        recipeDetailsPanel.setBackground(new java.awt.Color(247, 248, 245));
        recipeDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backToRecipesButton.setBackground(new java.awt.Color(247, 248, 245));
        backToRecipesButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        backToRecipesButton.setText("← Back");
        backToRecipesButton.setBorderPainted(false);
        backToRecipesButton.setContentAreaFilled(false);
        backToRecipesButton.addActionListener(this::backToRecipesButtonActionPerformed);
        recipeDetailsPanel.add(backToRecipesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        recipeDetailsImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recipeorganiser/Minimal Chef Hat and Utensils Icon.png"))); // NOI18N
        recipeDetailsImageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(48, 82, 61)));
        recipeDetailsImageLabel.setPreferredSize(new java.awt.Dimension(220, 220));
        recipeDetailsPanel.add(recipeDetailsImageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 320, 250));

        recipeDetailsNameLabel.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        recipeDetailsNameLabel.setForeground(new java.awt.Color(48, 82, 61));
        recipeDetailsNameLabel.setText("Chocolate cake");
        recipeDetailsPanel.add(recipeDetailsNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        recipeDetailsFavouriteButton.setBackground(new java.awt.Color(247, 248, 245));
        recipeDetailsFavouriteButton.setFont(new java.awt.Font("SansSerif", 0, 28)); // NOI18N
        recipeDetailsFavouriteButton.setForeground(new java.awt.Color(48, 82, 61));
        recipeDetailsFavouriteButton.setText("♡");
        recipeDetailsFavouriteButton.setBorder(null);
        recipeDetailsFavouriteButton.setBorderPainted(false);
        recipeDetailsFavouriteButton.setContentAreaFilled(false);
        recipeDetailsFavouriteButton.addActionListener(this::recipeDetailsFavouriteButtonActionPerformed);
        recipeDetailsPanel.add(recipeDetailsFavouriteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 50, 70));

        recipeDetailsTypeLabel.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        recipeDetailsTypeLabel.setForeground(new java.awt.Color(95, 125, 100));
        recipeDetailsTypeLabel.setText("Baking");
        recipeDetailsPanel.add(recipeDetailsTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, -1, -1));

        recipeDetailsTimeLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        recipeDetailsTimeLabel.setForeground(new java.awt.Color(102, 102, 102));
        recipeDetailsTimeLabel.setText("1h 20m");
        recipeDetailsPanel.add(recipeDetailsTimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, -1, -1));

        ingredientsTabButton.setBackground(new java.awt.Color(232, 241, 232));
        ingredientsTabButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ingredientsTabButton.setForeground(new java.awt.Color(48, 82, 61));
        ingredientsTabButton.setText("Ingredients");
        ingredientsTabButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(190, 205, 190), 1, true));
        ingredientsTabButton.addActionListener(this::ingredientsTabButtonActionPerformed);
        recipeDetailsPanel.add(ingredientsTabButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 145, 40));

        instructionsTabButton.setBackground(new java.awt.Color(232, 241, 232));
        instructionsTabButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        instructionsTabButton.setForeground(new java.awt.Color(48, 82, 61));
        instructionsTabButton.setText("Instructions");
        instructionsTabButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(190, 205, 190), 1, true));
        instructionsTabButton.addActionListener(this::instructionsTabButtonActionPerformed);
        recipeDetailsPanel.add(instructionsTabButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 145, 40));

        detailsTabButton.setBackground(new java.awt.Color(232, 241, 232));
        detailsTabButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        detailsTabButton.setForeground(new java.awt.Color(48, 82, 61));
        detailsTabButton.setText("Details");
        detailsTabButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(190, 205, 190), 1, true));
        detailsTabButton.addActionListener(this::detailsTabButtonActionPerformed);
        recipeDetailsPanel.add(detailsTabButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, 145, 40));

        editRecipeButton.setBackground(new java.awt.Color(48, 82, 61));
        editRecipeButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editRecipeButton.setForeground(new java.awt.Color(255, 255, 255));
        editRecipeButton.setText("Edit Recipe");
        editRecipeButton.setBorderPainted(false);
        editRecipeButton.addActionListener(this::editRecipeButtonActionPerformed);
        recipeDetailsPanel.add(editRecipeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 610, 130, 40));

        deleteRecipeButton.setBackground(new java.awt.Color(247, 226, 226));
        deleteRecipeButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteRecipeButton.setForeground(new java.awt.Color(161, 62, 62));
        deleteRecipeButton.setText("Delete");
        deleteRecipeButton.setBorderPainted(false);
        deleteRecipeButton.addActionListener(this::deleteRecipeButtonActionPerformed);
        recipeDetailsPanel.add(deleteRecipeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 540, 110, 40));

        jLabel37.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel37.setText("⏲ Total Time:");
        recipeDetailsPanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        jLabel38.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel38.setText("🏷 Category:");
        recipeDetailsPanel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, -1, -1));

        recipeDetailsCategoryLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        recipeDetailsCategoryLabel.setForeground(new java.awt.Color(102, 102, 102));
        recipeDetailsCategoryLabel.setText("dessert");
        recipeDetailsPanel.add(recipeDetailsCategoryLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, -1, -1));

        recipeInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        recipeInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 205, 190)));
        recipeInfoPanel.setLayout(new java.awt.CardLayout());

        ingredientsInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        ingredientsInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ingredientsHeadingLabel.setFont(new java.awt.Font("SansSerif", 3, 16)); // NOI18N
        ingredientsHeadingLabel.setForeground(new java.awt.Color(48, 82, 61));
        ingredientsHeadingLabel.setText("Ingredients");
        ingredientsInfoPanel.add(ingredientsHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        ingredientsScrollPane.setBorder(null);

        recipeIngredientsList.setBorder(null);
        recipeIngredientsList.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        recipeIngredientsList.setForeground(new java.awt.Color(50, 50, 50));
        recipeIngredientsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "item", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ingredientsScrollPane.setViewportView(recipeIngredientsList);

        ingredientsInfoPanel.add(ingredientsScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 510, 170));

        recipeInfoPanel.add(ingredientsInfoPanel, "INGREDIENTS_INFO");

        instructionsInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        instructionsInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("SansSerif", 3, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(48, 82, 61));
        jLabel39.setText("Instructions");
        instructionsInfoPanel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        instructionScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        instructionScrollPane.setBorder(null);

        recipeInstructionsTextArea.setEditable(false);
        recipeInstructionsTextArea.setBackground(new java.awt.Color(255, 255, 255));
        recipeInstructionsTextArea.setColumns(20);
        recipeInstructionsTextArea.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        recipeInstructionsTextArea.setForeground(new java.awt.Color(50, 50, 50));
        recipeInstructionsTextArea.setLineWrap(true);
        recipeInstructionsTextArea.setRows(5);
        recipeInstructionsTextArea.setText("1.Mix the ingredients.");
        recipeInstructionsTextArea.setWrapStyleWord(true);
        recipeInstructionsTextArea.setBorder(null);
        instructionScrollPane.setViewportView(recipeInstructionsTextArea);

        instructionsInfoPanel.add(instructionScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 470, 180));

        recipeInfoPanel.add(instructionsInfoPanel, "INSTRUCTIONS_INFO");

        cookingInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        cookingInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cookingDetailsHeadingLabel.setFont(new java.awt.Font("SansSerif", 3, 16)); // NOI18N
        cookingDetailsHeadingLabel.setForeground(new java.awt.Color(46, 82, 61));
        cookingDetailsHeadingLabel.setText("Cooking Details");
        cookingInfoPanel.add(cookingDetailsHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cookingDetailOneLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cookingDetailOneLabel.setForeground(new java.awt.Color(50, 50, 50));
        cookingDetailOneLabel.setText("Oven temperature: 180°C");
        cookingInfoPanel.add(cookingDetailOneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        cookingDetailTwoLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cookingDetailTwoLabel.setForeground(new java.awt.Color(50, 50, 50));
        cookingDetailTwoLabel.setText("Oven mode: Conventional");
        cookingInfoPanel.add(cookingDetailTwoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        cookingDetailThreeLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cookingDetailThreeLabel.setForeground(new java.awt.Color(50, 50, 50));
        cookingDetailThreeLabel.setText("Cooking time: 40 min");
        cookingInfoPanel.add(cookingDetailThreeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        cookingDetailFourLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cookingDetailFourLabel.setForeground(new java.awt.Color(50, 50, 50));
        cookingInfoPanel.add(cookingDetailFourLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 170, 30));

        recipeInfoPanel.add(cookingInfoPanel, "COOKING_INFO");

        recipeDetailsPanel.add(recipeInfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 610, 260));

        contentPanel.add(recipeDetailsPanel, "RECIPE_DETAILS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recipesSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recipesSearchFieldActionPerformed
        // TODO add your handling code here:
        String search=recipesSearchField.getText().trim();
        try {
            loadRecipeCards(manager.searchByName(search));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error loading recipes: " + ex.getMessage());
        }
    }//GEN-LAST:event_recipesSearchFieldActionPerformed
    // AI-assisted: Guidance on refreshing the table using saved meal assignments.
    private void loadMealPlan() {

        try {
            List<PlannedMeal> plannedMeals = manager.viewWeeklyMealPlan();

            DefaultTableModel model =(DefaultTableModel) mealPlanTable.getModel();
            
            for (int row = 0; row < 7; row++) {
                for (int column = 1; column <= 3; column++) {
                    model.setValueAt("+ Add", row, column);
                }
            }

            for (PlannedMeal plannedMeal : plannedMeals) {

                int row = plannedMeal.getDay().ordinal();
                int column;
                if (plannedMeal.getMealType() == MealType.BREAKFAST) {
                    column = 1;
                } else if (plannedMeal.getMealType() == MealType.LUNCH) {
                    column = 2;
                } else {
                    column = 3;
                }

                String recipeName = plannedMeal.getRecipe().getName();
                model.setValueAt(recipeName, row, column);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error loading meal plan: " + ex.getMessage());
        }
    }
    private void chillTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chillTimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chillTimeFieldActionPerformed

    private void cookingTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookingTimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cookingTimeFieldActionPerformed

    private void ingredientNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredientNameFieldActionPerformed

    private void ingredientQuantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientQuantityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingredientQuantityFieldActionPerformed
    // Removes the selected ingredient from the form's ingredient list.
    private void removeIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeIngredientButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = ingredientJList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an ingredient.");
            return;
        }

        ingredients.remove(selectedIndex);
        ingredientListModel.remove(selectedIndex);
        
        
    }//GEN-LAST:event_removeIngredientButtonActionPerformed

    private void clearPlanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPlanButtonActionPerformed
        // TODO add your handling code here:
        clearWeeklyMealPlan();
    }//GEN-LAST:event_clearPlanButtonActionPerformed
    //back to recipe button goes back to previous page.
    private void backToRecipesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToRecipesButtonActionPerformed
        // TODO add your handling code here:
        if (previousPage.equals("DASHBOARD")) {
            homeButton.doClick();
        } else if (previousPage.equals("FAVOURITES")) {
            favouritesButton.doClick();
        } else {
            myRecipesButton.doClick();
        }
    }//GEN-LAST:event_backToRecipesButtonActionPerformed
    // AI-assisted: Reviewed the logic for selecting and assigning recipes to meals.
    private void chooseMeal(DayOfWeek day, MealType mealType) {

        try {
            List<Recipe> recipes = manager.viewRecipes();

            if (recipes.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "You have no recipes. Please add a recipe first."
                );
                return;
            }

            String[] recipeNames = new String[recipes.size()];

            for (int i = 0; i < recipes.size(); i++) {
                recipeNames[i] = recipes.get(i).getName();
            }

            String selectedName = (String) JOptionPane.showInputDialog(
                    this,
                    "Select a recipe for " + day + " - " + mealType,
                    "Choose Recipe",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    recipeNames,
                    recipeNames[0]
            );

            if (selectedName == null) {
                return;
            }

            boolean added =manager.addMealToPlan(selectedName, day, mealType);
                    

            if (added) {
                loadMealPlan();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Could not add the selected recipe."
                );
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error adding meal: " + ex.getMessage()
            );
        }
    }
    // AI-assisted: Guidance on handling JTable cell clicks and meal selection.
    private void setupMealPlanTable() {

        
        mealPlanTable.setDefaultEditor(Object.class, null);

        mealPlanTable.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int row = mealPlanTable.rowAtPoint(evt.getPoint());
                int column = mealPlanTable.columnAtPoint(evt.getPoint());
                
                if (row < 0 || column < 1 || column > 3) {
                    return;
                }

                DayOfWeek day = DayOfWeek.values()[row];

                MealType mealType;

                if (column == 1) {
                    mealType = MealType.BREAKFAST;
                } else if (column == 2) {
                    mealType = MealType.LUNCH;
                } else {
                    mealType = MealType.DINNER;
                }

                Object value = mealPlanTable.getValueAt(row, column);

                String currentMeal =value == null ? "+ Add" : value.toString();

                if (currentMeal.equals("+ Add")) {
                    chooseMeal(day, mealType);
                    return;
                }

                String[] options = {"Replace", "Remove", "Cancel"};

                int choice = JOptionPane.showOptionDialog(
                        RecipeMainFrame.this,
                        "Current recipe: " + currentMeal,
                        "Manage Meal",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == 0) {

                    chooseMeal(day, mealType);
                } else if (choice == 1) {

                    try {
                        boolean removed =manager.removeMealFromPlan(day, mealType);

                        if (removed) {
                            loadMealPlan();
                        } else {
                            JOptionPane.showMessageDialog(RecipeMainFrame.this,"Could not remove the meal.");
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(RecipeMainFrame.this,"Error removing meal: " + ex.getMessage());
                    }
                    
                }
            }
        });
    }
    
    private void clearWeeklyMealPlan() {

        int answer = JOptionPane.showConfirmDialog(this,"Are you sure you want to clear the entire weekly meal plan?","Clear Meal Plan",JOptionPane.YES_NO_OPTION);

        if (answer != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            for (DayOfWeek day : DayOfWeek.values()) {

                for (MealType mealType : new MealType[]{
                        MealType.BREAKFAST,
                        MealType.LUNCH,
                        MealType.DINNER
                }) {
                    manager.removeMealFromPlan(day, mealType);
                }
            }

            loadMealPlan();

            JOptionPane.showMessageDialog(this,"Meal plan cleared successfully!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error clearing meal plan: " + ex.getMessage());
            loadMealPlan();
        }
    }
    // Adds or removes the selected recipe from favourites.
    private void recipeDetailsFavouriteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recipeDetailsFavouriteButtonActionPerformed
        // TODO add your handling code here:
        try {
            boolean favourite = manager.isFavourite(selectedRecipe.getName());

            if (favourite) {
                manager.removeFavourite(selectedRecipe.getName());
                recipeDetailsFavouriteButton.setText("♡");
                recipeDetailsFavouriteButton.setSelected(false);
            } else {
                manager.addFavourite(selectedRecipe.getName());
                recipeDetailsFavouriteButton.setText("♥");
                recipeDetailsFavouriteButton.setSelected(true);
            }

            totalFavouriteNumber.setText(String.valueOf(manager.viewFavourites().size()));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
    }//GEN-LAST:event_recipeDetailsFavouriteButtonActionPerformed
    //Myrecipe page loading
    private void myRecipesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myRecipesButtonActionPerformed
        // TODO add your handling code here:
        recipesSearchField.setText("");
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, "RECIPES");
        updateSidebarSelection(myRecipesButton);
        try {
            loadRecipeCards(manager.viewRecipes());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error loading recipes: " + ex.getMessage());
        }
    }//GEN-LAST:event_myRecipesButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, "DASHBOARD");
        updateSidebarSelection(homeButton);
        loadDashboard();
    }//GEN-LAST:event_homeButtonActionPerformed
    // Updates the recipe and favourite counts and displays up to three recipe cards.
    private void loadDashboard(){
        try {
            List<Recipe> recipes = manager.viewRecipes();
            List<Recipe> favourites = manager.viewFavourites();

            totalRecipesNumber.setText(String.valueOf(recipes.size()));
            totalFavouriteNumber.setText(String.valueOf(favourites.size()));
            dashboardCardsPanel.removeAll();

            for (int i = 0; i < recipes.size() && i < 3; i++) {
                JPanel card = createRecipeCard(recipes.get(i));
                dashboardCardsPanel.add(card);
            }
            

            dashboardCardsPanel.revalidate();
            dashboardCardsPanel.repaint();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error loading dashboard: " + ex.getMessage());
        }
    }
    
    private void addRecipeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRecipeButtonActionPerformed
        // TODO add your handling code here:
        editingRecipe = false;
        selectedRecipe = null;

        ingredients.clear();
        ingredientListModel.clear();

        recipeNameField.setText("");
        prepTimeField.setText("");

        ovenTemperatureField.setText("");
        bakingTimeField.setText("");

        cookingTimeField.setText("");
        chillTimeField.setText("");

        ingredientNameField.setText("");
        ingredientQuantityField.setText("");
        ingredientUnitField.setText("");

        instructionsTextArea.setText("");

        recipeTypeComboBox.setSelectedIndex(0);
        recipeCategoryComboBox.setSelectedIndex(0);
        prepTimeUnitComboBox.setSelectedIndex(0);

        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, "ADD_RECIPE");
        updateSidebarSelection(addRecipeButton);
        
    }//GEN-LAST:event_addRecipeButtonActionPerformed

    private void favouritesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favouritesButtonActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, "FAVOURITES");
        updateSidebarSelection(favouritesButton);
        try {
            loadFavouriteCards(manager.viewFavourites());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error loading recipes: " + ex.getMessage());
        }
        
        
    }//GEN-LAST:event_favouritesButtonActionPerformed

    private void mealPlanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mealPlanButtonActionPerformed
        // TODO add your handling code here:
        loadMealPlan();
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, "MEAL_PLAN");
        updateSidebarSelection(mealPlanButton);
        
    }//GEN-LAST:event_mealPlanButtonActionPerformed

    
    //save a recipe by clicking save after filling the add recipe form add recipe form 
    private void saveRecipeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveRecipeButtonActionPerformed
        // TODO add your handling code here:
        
        String name = recipeNameField.getText().trim();
        String type = recipeTypeComboBox.getSelectedItem().toString().toLowerCase();
        String categoryText = recipeCategoryComboBox.getSelectedItem().toString().toUpperCase().replace(" ", "_");
        RecipeCategory category= RecipeCategory.valueOf(categoryText);

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a recipe name.");
            return;
        }
        Recipe recipe = factory.createRecipe(name, type, category);

        String prepText = prepTimeField.getText().trim();
        double prepTime;
        try {
            prepTime = Double.parseDouble(prepText);

            if (!Double.isFinite(prepTime) ||prepTime <= 0) {
                JOptionPane.showMessageDialog(this, "Preparation time has to be greater than 0.");
                return;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid preparation time.");
            return;
        }

        char prepUnitText = prepTimeUnitComboBox.getSelectedItem().toString().charAt(0);
        recipe.setPrepDetails(prepTime, prepUnitText);


        if(recipe instanceof BakingRecipe){
            BakingRecipe bakingRecipe = (BakingRecipe)recipe;
            String selectedMode = ovenModeComboBox.getSelectedItem().toString();

            OvenMode mode;

            if (selectedMode.equalsIgnoreCase("Conventional")) {
                mode = OvenMode.BAKE_CONVENTIONAL;
            } else {
                mode = OvenMode.valueOf(selectedMode.toUpperCase().replace(" ", "_"));
            }

            String ovenTempText= ovenTemperatureField.getText().trim();
            int ovenTemp;
            try {
                ovenTemp = Integer.parseInt(ovenTempText);

                if (ovenTemp <= 0) {
                    JOptionPane.showMessageDialog(this, "Oven temperature has to be greater than 0.");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid oven temperature.");
                return;
            }
            char tempUnit = temperatureUnitComboBox.getSelectedItem().toString().charAt(1);

            String bakingTimeText= bakingTimeField.getText().trim();
            double bakingTime;
            try {
                bakingTime = Double.parseDouble(bakingTimeText);

                if (!Double.isFinite(bakingTime) ||bakingTime <= 0) {
                    JOptionPane.showMessageDialog(this, "Baking time has to be greater than 0.");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid baking time.");
                return;
            }

            char bakingTimeUnit = bakingTimeUnitComboBox.getSelectedItem().toString().charAt(0);

            bakingRecipe.setBakingDetails(ovenTemp, tempUnit, bakingTime, bakingTimeUnit, mode);

        }else if(recipe instanceof StoveTopRecipe){
            StoveTopRecipe stoveTopRecipe = (StoveTopRecipe)recipe;

            String cookingTimeText= cookingTimeField.getText().trim();
            double cookingTime;
            try {
                cookingTime = Double.parseDouble(cookingTimeText);

                if (!Double.isFinite(cookingTime) ||cookingTime <= 0) {
                    JOptionPane.showMessageDialog(this, "Cooking time has to be greater than 0.");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid cooking time.");
                return;
            }

            char cookingTimeUnit = cookingTimeUnitComboBox.getSelectedItem().toString().charAt(0);

            HeatLevel heat=HeatLevel.valueOf(heatLevelComboBox.getSelectedItem().toString().toUpperCase());

            stoveTopRecipe.setStoveTopRecipeDetails(heat, cookingTime, cookingTimeUnit);

        }else if(recipe instanceof NoCookRecipe){
            NoCookRecipe noCookRecipe = (NoCookRecipe)recipe;

            String chillTimeText= chillTimeField.getText().trim();
            double chillTime;
            try {
                chillTime = Double.parseDouble(chillTimeText);

                if (!Double.isFinite(chillTime) ||chillTime < 0) {
                    JOptionPane.showMessageDialog(this, "Chill time can not be negative.");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid chill time.");
                return;
            }

            char chillTimeUnit = chillTimeUnitComboBox.getSelectedItem().toString().charAt(0);

            noCookRecipe.setChillTimeDetails(chillTime, chillTimeUnit);

        }
        String instructionsText = instructionsTextArea.getText().trim();
        if (instructionsText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter at least one instruction.");
            return;
        }

        String[] instructionLines = instructionsText.split("\\R");

        for (String line : instructionLines) {
            String instruction = line.trim();

            if (!instruction.isEmpty()) {
                recipe.addInstruction(instruction);
            }
        }
        for (Ingredient ing : ingredients) {
            recipe.addIngredient(ing.getName(), ing.getQuantity(), ing.getUnit());
        }
        try {
            boolean saved;

            if (editingRecipe) {
                saved = manager.editRecipe(selectedRecipe.getName(), recipe);
            } else {
                saved = manager.addRecipe(recipe);
            }

            if (saved) {
                if (editingRecipe) {
                    JOptionPane.showMessageDialog(this, "Recipe updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Recipe saved successfully!");
                }

                editingRecipe = false;
                selectedRecipe = null;
                
                ingredients.clear();
                ingredientListModel.clear();
                recipeNameField.setText("");
                prepTimeField.setText("");

                ovenTemperatureField.setText("");
                bakingTimeField.setText("");

                cookingTimeField.setText("");
                chillTimeField.setText("");

                ingredientNameField.setText("");
                ingredientQuantityField.setText("");
                ingredientUnitField.setText("");

                instructionsTextArea.setText("");

                
                myRecipesButton.doClick();

            } else {
                JOptionPane.showMessageDialog(this, "Recipe could not be saved.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving recipe: " + ex.getMessage());
        }

        

        

                  
       
    }//GEN-LAST:event_saveRecipeButtonActionPerformed
    // Validates and adds an ingredient to the current recipe form.
    private void addIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIngredientButtonActionPerformed
        // TODO add your handling code here:
        String ingredientName = ingredientNameField.getText().trim();
        if (ingredientName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ingredient name.");
            return;
        }
        String ingredientQuantityText= ingredientQuantityField.getText().trim();
        double quantity;
        try {
            quantity = Double.parseDouble(ingredientQuantityText);

            if (!Double.isFinite(quantity) ||quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Ingredient's quantity has to be greater than 0.");
                return;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid ingredient quantity.");
            return;
        }
        String ingredientUnit=ingredientUnitField.getText().trim();
        if (ingredientUnit.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ingredient unit.");
            return;
        }
        
        Ingredient ingredient = new Ingredient(ingredientName,quantity,ingredientUnit);
        
        ingredients.add(ingredient);
        
        ingredientListModel.addElement(ingredient.toString());
        
        ingredientNameField.setText("");
        ingredientQuantityField.setText("");
        ingredientUnitField.setText("");
    }//GEN-LAST:event_addIngredientButtonActionPerformed
    // Displays the cooking detail fields for the selected recipe type.
    private void recipeTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recipeTypeComboBoxActionPerformed
        // TODO add your handling code here:
        String type = recipeTypeComboBox.getSelectedItem().toString();
        

        CardLayout layout = (CardLayout) cookingDetailsPanel.getLayout();

        if (type.equals("Baking")) {
            layout.show(cookingDetailsPanel, "BAKING");

        } else if (type.equals("Stove Top")) {
            layout.show(cookingDetailsPanel, "STOVE_TOP");

            
        } else if (type.equals("No Cook")) {
            layout.show(cookingDetailsPanel, "NO_COOK");
        }
    }//GEN-LAST:event_recipeTypeComboBoxActionPerformed

    private void categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryComboBoxActionPerformed
        // TODO add your handling code here:
        applyFilters();  
    }//GEN-LAST:event_categoryComboBoxActionPerformed

    private void TypeByComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TypeByComboBoxActionPerformed
        // TODO add your handling code here:
        applyFilters();
    }//GEN-LAST:event_TypeByComboBoxActionPerformed

    private void viewAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllButtonActionPerformed
        // TODO add your handling code here:
        myRecipesButton.doClick();
        
    }//GEN-LAST:event_viewAllButtonActionPerformed

    private void favouritesSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favouritesSearchFieldActionPerformed
        // TODO add your handling code here:
        String search=favouritesSearchField.getText().trim();
        try {
            List<Recipe> recipes=manager.searchByName(search);
            List<Recipe> favouriteList= new ArrayList<>();
            for(Recipe recipe:recipes){
                
                if(manager.isFavourite(recipe.getName())){
                    favouriteList.add(recipe);
                }
            }
            recipes=favouriteList;
            loadFavouriteCards(recipes);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error loading recipes: " + ex.getMessage());
        }
    }//GEN-LAST:event_favouritesSearchFieldActionPerformed

    private void favouritesTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favouritesTypeComboBoxActionPerformed
        // TODO add your handling code here:
        favouriteFilters();
        
    }//GEN-LAST:event_favouritesTypeComboBoxActionPerformed

    private void favouritesCategoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favouritesCategoryComboBoxActionPerformed
        // TODO add your handling code here:
        favouriteFilters();
    }//GEN-LAST:event_favouritesCategoryComboBoxActionPerformed

    private void ingredientsTabButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingredientsTabButtonActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) recipeInfoPanel.getLayout();
        layout.show(recipeInfoPanel, "INGREDIENTS_INFO");
    }//GEN-LAST:event_ingredientsTabButtonActionPerformed

    private void instructionsTabButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructionsTabButtonActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) recipeInfoPanel.getLayout();
        layout.show(recipeInfoPanel, "INSTRUCTIONS_INFO");
    }//GEN-LAST:event_instructionsTabButtonActionPerformed

    private void detailsTabButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsTabButtonActionPerformed
        // TODO add your handling code here:
        CardLayout layout = (CardLayout) recipeInfoPanel.getLayout();
        layout.show(recipeInfoPanel, "COOKING_INFO");
    }//GEN-LAST:event_detailsTabButtonActionPerformed
    // Confirms deletion and removes the selected recipe from the database.
    private void deleteRecipeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRecipeButtonActionPerformed
        // TODO add your handling code here:
        int answer = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete " + selectedRecipe.getName() + "?","Delete Recipe",JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION) {
           
           try{
               manager.removeRecipe(selectedRecipe.getName());
               myRecipesButton.doClick();
               
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,"Error loading recipes: " + ex.getMessage());
            }
            
        }
        
    }//GEN-LAST:event_deleteRecipeButtonActionPerformed

    private void editRecipeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRecipeButtonActionPerformed
        // TODO add your handling code here:
        editingRecipe = true;
        
        recipeNameField.setText(selectedRecipe.getName());

        String type = selectedRecipe.getRecipeType();

        if (type.equalsIgnoreCase("STOVE_TOP")) {
            recipeTypeComboBox.setSelectedItem("Stove Top");
        } else if (type.equalsIgnoreCase("NO_COOK")) {
            recipeTypeComboBox.setSelectedItem("No Cook");
        } else if (type.equalsIgnoreCase("BAKING")) {
            recipeTypeComboBox.setSelectedItem("Baking");
        }

        String category = selectedRecipe.getCategory().toString();

        if (category.equalsIgnoreCase("APPETISER")) {
            recipeCategoryComboBox.setSelectedItem("Appetiser");
        } else if (category.equalsIgnoreCase("BREAKFAST")) {
            recipeCategoryComboBox.setSelectedItem("Breakfast");
        } else if (category.equalsIgnoreCase("MAIN_COURSE")) {
            recipeCategoryComboBox.setSelectedItem("Main course");
        }else if (category.equalsIgnoreCase("DESSERT")) {
            recipeCategoryComboBox.setSelectedItem("Dessert");
        }else if (category.equalsIgnoreCase("SNACK")) {
            recipeCategoryComboBox.setSelectedItem("Snack");
        }else if (category.equalsIgnoreCase("DRINKS")) {
            recipeCategoryComboBox.setSelectedItem("Drinks");
        }else if (category.equalsIgnoreCase("SAUCE")) {
            recipeCategoryComboBox.setSelectedItem("Sauce");
        }
        prepTimeField.setText(String.valueOf(selectedRecipe.getPrepTime()));
        
        char prepUnit=selectedRecipe.getPrepTimeUnit();
        if(prepUnit=='M'|| prepUnit=='m'){
            prepTimeUnitComboBox.setSelectedItem("Minutes");
        }else if(prepUnit=='H'|| prepUnit=='h'){
           prepTimeUnitComboBox.setSelectedItem("Hours"); 
        }

        ingredients.clear();
        ingredientListModel.clear();

        for (Ingredient ingredient : selectedRecipe.getIngredients()) {
            ingredients.add(ingredient);
            ingredientListModel.addElement(ingredient.toString());
        }

        
        String instructionText = "";

        for (String instruction : selectedRecipe.getInstructions()) {
            instructionText += instruction + "\n";
            
        }

        instructionsTextArea.setText(instructionText);

        if (selectedRecipe instanceof BakingRecipe) {
            BakingRecipe baking = (BakingRecipe) selectedRecipe;

            ovenTemperatureField.setText(String.valueOf(baking.getOvenTemperature()));
            
            char ovenUnit=baking.getTemperatureUnit();
            if(ovenUnit=='C'|| ovenUnit=='c'){
                temperatureUnitComboBox.setSelectedItem("°C");
            }else if(ovenUnit=='F'|| ovenUnit=='f'){
                temperatureUnitComboBox.setSelectedItem("°F"); 
            }

            bakingTimeField.setText(String.valueOf(baking.getBakingTime()));

            char bakingUnit=baking.getBakingTimeUnit();
            if(bakingUnit=='M'|| bakingUnit=='m'){
                bakingTimeUnitComboBox.setSelectedItem("Minutes");
            }else if(bakingUnit=='H'|| bakingUnit=='h'){
               bakingTimeUnitComboBox.setSelectedItem("Hours"); 
            }
  
            String mode = baking.getMode().name();
            if(mode.equalsIgnoreCase("BAKE_CONVENTIONAL")){
                ovenModeComboBox.setSelectedItem("Conventional");
            }else if(mode.equalsIgnoreCase("FAN_FORCED")){
                ovenModeComboBox.setSelectedItem("Fan Forced");
            }else if(mode.equalsIgnoreCase("GRILL")){
                ovenModeComboBox.setSelectedItem("Grill");
            }
            
        }else if (selectedRecipe instanceof StoveTopRecipe) {
            StoveTopRecipe stove = (StoveTopRecipe) selectedRecipe;

            cookingTimeField.setText(String.valueOf(stove.getCookingTime()));
            
            char stoveUnit=stove.getTimeUnit();
            if(stoveUnit=='M'|| stoveUnit=='m'){
                cookingTimeUnitComboBox.setSelectedItem("Minutes");
            }else if(stoveUnit=='H'|| stoveUnit=='h'){
               cookingTimeUnitComboBox.setSelectedItem("Hours"); 
            }
                       
            String heat = stove.getHeatLevel().name();
            if(heat.equalsIgnoreCase("LOW")){
                heatLevelComboBox.setSelectedItem("Low");
            }else if(heat.equalsIgnoreCase("MEDIUM")){
                heatLevelComboBox.setSelectedItem("Medium");
            }else if(heat.equalsIgnoreCase("HIGH")){
                heatLevelComboBox.setSelectedItem("High");
            }
            
        }else if (selectedRecipe instanceof NoCookRecipe) {
            NoCookRecipe noCook = (NoCookRecipe) selectedRecipe;

            chillTimeField.setText(String.valueOf(noCook.getChillTime()));
            char noCookUnit=noCook.getChillTimeUnit();
            if(noCookUnit=='M'|| noCookUnit=='m'){
                chillTimeUnitComboBox.setSelectedItem("Minutes");
            }else if(noCookUnit=='H'|| noCookUnit=='h'){
               chillTimeUnitComboBox.setSelectedItem("Hours"); 
            }
            
        }
        
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, "ADD_RECIPE");
        
    }//GEN-LAST:event_editRecipeButtonActionPerformed
    // Clears the form and returns to the appropriate page.
    private void cancelRecipeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelRecipeButtonActionPerformed
        // TODO add your handling code here:
        boolean wasEditing = editingRecipe;

        recipeNameField.setText("");
        prepTimeField.setText("");
        ovenTemperatureField.setText("");
        bakingTimeField.setText("");
        cookingTimeField.setText("");
        chillTimeField.setText("");

        ingredientNameField.setText("");
        ingredientQuantityField.setText("");
        ingredientUnitField.setText("");
        instructionsTextArea.setText("");

        ingredients.clear();
        ingredientListModel.clear();

        editingRecipe = false;

        if (wasEditing) {
            CardLayout layout = (CardLayout) contentPanel.getLayout();
            layout.show(contentPanel, "RECIPE_DETAILS");
        } else {
            homeButton.doClick();
        }

    }//GEN-LAST:event_cancelRecipeButtonActionPerformed

    private void savePlanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePlanButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this,"Your weekly meal plan is already saved!");
    }//GEN-LAST:event_savePlanButtonActionPerformed

    private void prepTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prepTimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prepTimeFieldActionPerformed

    private void ovenModeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ovenModeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ovenModeComboBoxActionPerformed

    private void favouriteFilters(){
        String favouriteCategory = favouritesCategoryComboBox.getSelectedItem().toString();
        String favouriteType = favouritesTypeComboBox.getSelectedItem().toString();

        try {
            List<Recipe> recipes = new ArrayList<>(manager.viewFavourites());
            System.out.println("Favourites retrieved: " + recipes.size());
            System.out.println("Selected category: " + favouriteCategory+ ", Selected type: " + favouriteType);

            if (!favouriteCategory.trim().equalsIgnoreCase("All")) {
                RecipeCategory category = RecipeCategory.valueOf(favouriteCategory.toUpperCase().replace(" ", "_"));

                List<Recipe> filteredRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    if (recipe.getCategory().equals(category)) {
                        filteredRecipes.add(recipe);
                    }
                }

                recipes = filteredRecipes;
            }

            if (!favouriteType.trim().equalsIgnoreCase("All")) {
                String type = favouriteType.toUpperCase().replace(" ", "_");
                List<Recipe> filteredRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    if (recipe.getRecipeType().equalsIgnoreCase(type)) {
                        filteredRecipes.add(recipe);
                    }
                }

                recipes = filteredRecipes;
    
            }
            
            System.out.println("Recipes after filtering: " + recipes.size());
            loadFavouriteCards(recipes);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error filtering recipes: " + ex.getMessage());
        }
    }
    
    private void applyFilters() {
        String selectedCategory = categoryComboBox.getSelectedItem().toString();
        String selectedType = TypeByComboBox.getSelectedItem().toString();

        try {
            List<Recipe> recipes = manager.viewRecipes();

            if (!selectedCategory.equals("All")) {
                RecipeCategory category = RecipeCategory.valueOf(selectedCategory.toUpperCase().replace(" ", "_"));

                List<Recipe> filteredRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    if (recipe.getCategory().equals(category)) {
                        filteredRecipes.add(recipe);
                    }
                }

                recipes = filteredRecipes;
            }

            if (!selectedType.equals("All")) {
                String type = selectedType.toUpperCase().replace(" ", "_");
                List<Recipe> filteredRecipes = new ArrayList<>();

                for (Recipe recipe : recipes) {
                    if (recipe.getRecipeType().equalsIgnoreCase(type)) {
                        filteredRecipes.add(recipe);
                    }
                }

                recipes = filteredRecipes;

                
            }

            loadRecipeCards(recipes);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Error filtering recipes: " + ex.getMessage());
        }
    }
   
    // displaying recipe cards and favourite buttons. AI assisted and helped throught the debugging and error handling.
    private JPanel createRecipeCard(Recipe recipe) {

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(240, 290));
        card.setLayout(new BorderLayout());

        card.setBackground(Color.WHITE);
        
        
        ImageIcon icon = new ImageIcon(getClass().getResource("Minimal Chef Hat and Utensils Icon.png"));
        Image scaledImage = icon.getImage().getScaledInstance(250, 180, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        JLayeredPane imagePanel = new JLayeredPane();
        imagePanel.setPreferredSize(new Dimension(250, 180));
        imageLabel.setBounds(0, 0, 250, 180);
        imagePanel.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        
        JLabel nameLabel = new JLabel(recipe.getName());
        JLabel typeLabel = new JLabel(recipe.getRecipeType());
        double timeInMin=recipe.getTotalTime();
        int hours=(int)(timeInMin/60);
        int min = (int)(timeInMin%60);
        
        JLabel timeLabel = new JLabel("◷ " +hours+"h "+ min + "m");
        
        JButton favouriteButton=new JButton("♡");
        favouriteButton.setBounds(180, 5, 55, 45);
        imagePanel.add(favouriteButton, JLayeredPane.PALETTE_LAYER);
        try{
            boolean isFavourite=manager.isFavourite(recipe.getName());
            if(isFavourite){
                favouriteButton.setText("♥");
                
            }else{
                favouriteButton.setText("♡");
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this,"Error loading recipes: " + ex.getMessage());
        }
        favouriteButton.setFont(new Font("SansSerif", Font.PLAIN, 24));
        favouriteButton.setFocusPainted(false);
        favouriteButton.setBorderPainted(false);
        favouriteButton.setContentAreaFilled(false);
        
        
        
        favouriteButton.addActionListener(e -> {

            try {
                boolean isFavourite = manager.isFavourite(recipe.getName());

                if (isFavourite) {
                    boolean removed =manager.removeFavourite(recipe.getName());
                    if(removed){
                        favouriteButton.setText("♡");
                        if (favouritesPanel.isVisible()) {
                            favouritesButton.doClick();
                        }
                    }
                } else {
                    boolean added=manager.addFavourite(recipe.getName());
                    if(added){
                        favouriteButton.setText("♥");
                    }
                }
                totalFavouriteNumber.setText( String.valueOf(manager.viewFavourites().size()));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error updating favourite: " + ex.getMessage());
            }
        });
        
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        nameLabel.setForeground(Color.BLACK);

        typeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        typeLabel.setForeground(Color.GRAY);

        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        timeLabel.setForeground(Color.DARK_GRAY);

        textPanel.add(nameLabel);
        textPanel.add(Box.createVerticalStrut(6));
        textPanel.add(typeLabel);
        textPanel.add(Box.createVerticalStrut(8));
        textPanel.add(timeLabel);

        card.add(imagePanel, BorderLayout.NORTH);
        card.add(textPanel, BorderLayout.CENTER);
        
        java.awt.event.MouseAdapter openDetails = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (dashboardPanel.isVisible()) {
                    previousPage = "DASHBOARD";
                } else if (favouritesPanel.isVisible()) {
                    previousPage = "FAVOURITES";
                } else {
                    previousPage = "RECIPES";
                }
                showRecipeDetails(recipe);
            }
        };
        card.addMouseListener(openDetails);
        imagePanel.addMouseListener(openDetails);
        imageLabel.addMouseListener(openDetails);
        textPanel.addMouseListener(openDetails);
        nameLabel.addMouseListener(openDetails);
        typeLabel.addMouseListener(openDetails);
        timeLabel.addMouseListener(openDetails);
        

        return card;
    }
    
    private void loadRecipeCards(List<Recipe> recipes) {

        recipeCardsPanel.removeAll();
        
        for (Recipe recipe : recipes) {
            JPanel card = createRecipeCard(recipe);
            recipeCardsPanel.add(card);
        }


        recipeCardsPanel.revalidate();
        recipeCardsPanel.repaint();
        
        

    }
    
    private void loadFavouriteCards(List<Recipe> recipes){
        
        favouritesListPanel.removeAll();
        
        for (Recipe recipe: recipes){
            
            JPanel card = createRecipeCard(recipe);
            favouritesListPanel.add(card);
        }
        favouritesListPanel.revalidate();
        favouritesListPanel.repaint();
        
        
    }
    
    
    private void showRecipeDetails(Recipe recipe){
        
        selectedRecipe=recipe;
        recipeDetailsNameLabel.setText(recipe.getName());

        recipeDetailsTypeLabel.setText(recipe.getRecipeType());

        recipeDetailsCategoryLabel.setText(recipe.getCategory().toString());

        double totalTime = recipe.getTotalTime();
        int hours = (int) (totalTime / 60);
        int minutes = (int) (totalTime % 60);

        recipeDetailsTimeLabel.setText(hours + "h " + minutes + "m");
        
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Ingredient ingredient : recipe.getIngredients()) {
            model.addElement(ingredient.toString());
        }

        recipeIngredientsList.setModel(model);
        
        String instructionsText = "";
        int stepNumber = 1;

        for (String instruction : recipe.getInstructions()) {
            instructionsText += stepNumber + ". " + instruction + "\n\n";
            stepNumber++;
        }
        recipeInstructionsTextArea.setText(instructionsText);
        
        if (recipe instanceof BakingRecipe) {
            BakingRecipe baking = (BakingRecipe) recipe;
            char ovenUnit = baking.getTemperatureUnit();
            cookingDetailTwoLabel.setText("Oven Temperature: "+baking.getOvenTemperature()+" °"+Character.toLowerCase(ovenUnit));
            cookingDetailThreeLabel.setText("Oven Mode: "+baking.getMode().name().replace('_', ' ').toLowerCase());
            char bakingUnit = baking.getBakingTimeUnit();
            cookingDetailFourLabel.setText("Baking Time: "+baking.getBakingTime()+" "+Character.toLowerCase(bakingUnit));
            char prepUnit=baking.getPrepTimeUnit();
            cookingDetailOneLabel.setText("Prep Time: "+baking.getPrepTime()+" "+Character.toLowerCase(prepUnit));

        } else if (recipe instanceof StoveTopRecipe) {
            StoveTopRecipe stoveTop = (StoveTopRecipe) recipe;
            char prepUnit = stoveTop.getPrepTimeUnit();
            cookingDetailOneLabel.setText("Prep Time: "+stoveTop.getPrepTime()+Character.toLowerCase(prepUnit));
            cookingDetailTwoLabel.setText("Heat Level: "+stoveTop.getHeatLevel().name().toLowerCase());
            char timeUnit=stoveTop.getTimeUnit();
            cookingDetailThreeLabel.setText("Cooking Time: "+stoveTop.getCookingTime()+" "+Character.toLowerCase(timeUnit));
            

        } else if (recipe instanceof NoCookRecipe) {
            NoCookRecipe noCook = (NoCookRecipe) recipe;
            char prepUnit = noCook.getPrepTimeUnit();
            cookingDetailOneLabel.setText("Prep Time: "+noCook.getPrepTime()+" "+Character.toLowerCase(prepUnit));
            char chillUnit = noCook.getChillTimeUnit();
            cookingDetailTwoLabel.setText("Chill Time: "+noCook.getChillTime()+" "+Character.toLowerCase(chillUnit));
            
        }
        
        try{
            boolean favourite=manager.isFavourite(recipe.getName());
            if(favourite){
                recipeDetailsFavouriteButton.setText("♥");
                
            }else{
                recipeDetailsFavouriteButton.setText("♡");
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this,"Error loading recipes: " + ex.getMessage());
        }
        
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, "RECIPE_DETAILS");
        
    }
    // Highlights the selected sidebar button and resets the others.
    private void updateSidebarSelection(JButton selectedButton) {

        JButton[] buttons = {
            homeButton,
            myRecipesButton,
            addRecipeButton,
            favouritesButton,
            mealPlanButton
        };

        for (JButton button : buttons) {
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(48, 82, 61));
        }

        selectedButton.setBackground(new Color(200, 225, 205));
        selectedButton.setForeground(new Color(48, 82, 61));
    }
    /**
     * @param args the command line arguments
     */
    //old main
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new RecipeMainFrame().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> TypeByComboBox;
    private javax.swing.JButton addIngredientButton;
    private javax.swing.JButton addRecipeButton;
    private javax.swing.JPanel addRecipePanel;
    private javax.swing.JButton backToRecipesButton;
    private javax.swing.JPanel bakingDetailsPanel;
    private javax.swing.JTextField bakingTimeField;
    private javax.swing.JComboBox<String> bakingTimeUnitComboBox;
    private javax.swing.JButton cancelRecipeButton;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JTextField chillTimeField;
    private javax.swing.JComboBox<String> chillTimeUnitComboBox;
    private javax.swing.JButton clearPlanButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel cookingDetailFourLabel;
    private javax.swing.JLabel cookingDetailOneLabel;
    private javax.swing.JLabel cookingDetailThreeLabel;
    private javax.swing.JLabel cookingDetailTwoLabel;
    private javax.swing.JLabel cookingDetailsHeadingLabel;
    private javax.swing.JPanel cookingDetailsPanel;
    private javax.swing.JPanel cookingInfoPanel;
    private javax.swing.JTextField cookingTimeField;
    private javax.swing.JComboBox<String> cookingTimeUnitComboBox;
    private javax.swing.JPanel dashboardCardsPanel;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JButton deleteRecipeButton;
    private javax.swing.JButton detailsTabButton;
    private javax.swing.JButton editRecipeButton;
    private javax.swing.JButton favouritesButton;
    private javax.swing.JComboBox<String> favouritesCategoryComboBox;
    private javax.swing.JPanel favouritesListPanel;
    private javax.swing.JPanel favouritesPanel;
    private javax.swing.JScrollPane favouritesScrollPane;
    private javax.swing.JTextField favouritesSearchField;
    private javax.swing.JComboBox<String> favouritesTypeComboBox;
    private javax.swing.JComboBox<String> heatLevelComboBox;
    private javax.swing.JButton homeButton;
    private javax.swing.JList<String> ingredientJList;
    private javax.swing.JTextField ingredientNameField;
    private javax.swing.JTextField ingredientQuantityField;
    private javax.swing.JTextField ingredientUnitField;
    private javax.swing.JLabel ingredientsHeadingLabel;
    private javax.swing.JPanel ingredientsInfoPanel;
    private javax.swing.JPanel ingredientsPanel;
    private javax.swing.JScrollPane ingredientsScrollPane;
    private javax.swing.JButton ingredientsTabButton;
    private javax.swing.JScrollPane instructionScrollPane;
    private javax.swing.JPanel instructionsInfoPanel;
    private javax.swing.JPanel instructionsPanel;
    private javax.swing.JScrollPane instructionsScrollPane;
    private javax.swing.JButton instructionsTabButton;
    private javax.swing.JTextArea instructionsTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mealPlanButton;
    private javax.swing.JScrollPane mealPlanScrollPane;
    private javax.swing.JTable mealPlanTable;
    private javax.swing.JButton myRecipesButton;
    private javax.swing.JPanel noCookDetailsPanel;
    private javax.swing.JComboBox<String> ovenModeComboBox;
    private javax.swing.JTextField ovenTemperatureField;
    private javax.swing.JTextField prepTimeField;
    private javax.swing.JComboBox<String> prepTimeUnitComboBox;
    private javax.swing.JComboBox<String> recipeCategoryComboBox;
    private javax.swing.JLabel recipeDetailsCategoryLabel;
    private javax.swing.JToggleButton recipeDetailsFavouriteButton;
    private javax.swing.JLabel recipeDetailsImageLabel;
    private javax.swing.JLabel recipeDetailsNameLabel;
    private javax.swing.JPanel recipeDetailsPanel;
    private javax.swing.JLabel recipeDetailsTimeLabel;
    private javax.swing.JLabel recipeDetailsTypeLabel;
    private javax.swing.JPanel recipeInfoPanel;
    private javax.swing.JList<String> recipeIngredientsList;
    private javax.swing.JTextArea recipeInstructionsTextArea;
    private javax.swing.JTextField recipeNameField;
    private javax.swing.JComboBox<String> recipeTypeComboBox;
    private javax.swing.JPanel recipesPanel;
    private javax.swing.JScrollPane recipesScrollPane;
    private javax.swing.JTextField recipesSearchField;
    private javax.swing.JButton removeIngredientButton;
    private javax.swing.JButton savePlanButton;
    private javax.swing.JButton saveRecipeButton;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel stoveDetailsPanel;
    private javax.swing.JComboBox<String> temperatureUnitComboBox;
    private javax.swing.JLabel totalFavouriteDescription;
    private javax.swing.JLabel totalFavouriteNumber;
    private javax.swing.JLabel totalFavouritesLabel;
    private javax.swing.JPanel totalFavouritesPanel;
    private javax.swing.JLabel totalRecipesDescription;
    private javax.swing.JLabel totalRecipesLabel;
    private javax.swing.JLabel totalRecipesNumber;
    private javax.swing.JPanel totalRecipesPanel;
    private javax.swing.JButton viewAllButton;
    private javax.swing.JPanel weeklyMealPlanPanel;
    // End of variables declaration//GEN-END:variables
}
