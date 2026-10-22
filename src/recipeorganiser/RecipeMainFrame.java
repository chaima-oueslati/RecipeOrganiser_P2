/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package recipeorganiser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.JTableHeader;


/**
 *
 * @author chaimaoueslati
 */
public class RecipeMainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecipeMainFrame.class.getName());

    /**
     * Creates new form RecipeMainFrame
     */
    public RecipeMainFrame() {
        initComponents();
        styleMealPlanTable();
    }

    
    // AI-assisted GUI styling for the Weekly Meal Plan JTable.
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

        // PART 4: Display rounded, pale-green boxes in the meal cells.
        // These are visual boxes, NOT actual JButtons.
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

        // Apply the rounded-box renderer to Breakfast, Lunch and Dinner.
        for (int column = 1; column < mealPlanTable.getColumnCount(); column++) {
            mealPlanTable.getColumnModel()
                    .getColumn(column)
                    .setCellRenderer(mealRenderer);
        }

        // PART 5: Remove the scroll pane border.
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
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        allFilterButton = new javax.swing.JButton();
        bakingFilterButton = new javax.swing.JButton();
        stoveTopFilterButton = new javax.swing.JButton();
        noCookFilterButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        viewAllButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        recipesPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        recipesSearchField = new javax.swing.JTextField();
        categoryComboBox = new javax.swing.JComboBox<>();
        sortByComboBox = new javax.swing.JComboBox<>();
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
        jLabel19 = new javax.swing.JLabel();
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
        jList1 = new javax.swing.JList<>();
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
        favouritesSortComboBox = new javax.swing.JComboBox<>();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recipe Organiser");
        setPreferredSize(new java.awt.Dimension(1100, 700));

        sidebarPanel.setBackground(new java.awt.Color(232, 241, 232));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(48, 82, 61));
        jLabel5.setText("<html>Recipe<br>Organiser</html>");

        homeButton.setBackground(new java.awt.Color(145, 177, 156));
        homeButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        homeButton.setForeground(new java.awt.Color(255, 255, 255));
        homeButton.setText("Home");
        homeButton.setBorderPainted(false);

        myRecipesButton.setBackground(new java.awt.Color(232, 241, 232));
        myRecipesButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        myRecipesButton.setForeground(new java.awt.Color(48, 82, 61));
        myRecipesButton.setText("My Recipes");
        myRecipesButton.setBorderPainted(false);

        addRecipeButton.setBackground(new java.awt.Color(232, 241, 232));
        addRecipeButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        addRecipeButton.setForeground(new java.awt.Color(48, 82, 61));
        addRecipeButton.setText("Add Recipe");
        addRecipeButton.setBorderPainted(false);

        favouritesButton.setBackground(new java.awt.Color(232, 241, 232));
        favouritesButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        favouritesButton.setForeground(new java.awt.Color(48, 82, 61));
        favouritesButton.setText("Favourites");
        favouritesButton.setBorderPainted(false);

        mealPlanButton.setBackground(new java.awt.Color(232, 241, 232));
        mealPlanButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        mealPlanButton.setForeground(new java.awt.Color(48, 82, 61));
        mealPlanButton.setText("Meal Plan");
        mealPlanButton.setBorderPainted(false);

        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarPanelLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(favouritesButton)
                    .addComponent(addRecipeButton)
                    .addComponent(myRecipesButton)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mealPlanButton))
                .addGap(27, 27, 27))
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(homeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(homeButton)
                .addGap(26, 26, 26)
                .addComponent(myRecipesButton)
                .addGap(28, 28, 28)
                .addComponent(addRecipeButton)
                .addGap(26, 26, 26)
                .addComponent(favouritesButton)
                .addGap(30, 30, 30)
                .addComponent(mealPlanButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new java.awt.CardLayout());

        dashboardPanel.setBackground(new java.awt.Color(247, 248, 245));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(48, 82, 61));
        jLabel1.setText("Welcome to Recipe Organiser!");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("What would you like to cook today?");

        searchTextField.addActionListener(this::searchTextFieldActionPerformed);

        searchButton.setText("Search");
        searchButton.addActionListener(this::searchButtonActionPerformed);

        allFilterButton.setBackground(new java.awt.Color(103, 155, 120));
        allFilterButton.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        allFilterButton.setForeground(new java.awt.Color(255, 255, 255));
        allFilterButton.setText("All");
        allFilterButton.addActionListener(this::allFilterButtonActionPerformed);

        bakingFilterButton.setBackground(new java.awt.Color(238, 242, 237));
        bakingFilterButton.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        bakingFilterButton.setForeground(new java.awt.Color(48, 82, 61));
        bakingFilterButton.setText("Baking");

        stoveTopFilterButton.setBackground(new java.awt.Color(238, 242, 237));
        stoveTopFilterButton.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        stoveTopFilterButton.setForeground(new java.awt.Color(48, 82, 61));
        stoveTopFilterButton.setText("Stove Top");

        noCookFilterButton.setBackground(new java.awt.Color(238, 242, 237));
        noCookFilterButton.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        noCookFilterButton.setForeground(new java.awt.Color(48, 82, 61));
        noCookFilterButton.setText("No Cook");

        jLabel4.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel4.setText("   My Recipes");

        viewAllButton.setBackground(new java.awt.Color(247, 248, 245));
        viewAllButton.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        viewAllButton.setForeground(new java.awt.Color(48, 82, 61));
        viewAllButton.setText("View All");
        viewAllButton.setBorderPainted(false);
        viewAllButton.setContentAreaFilled(false);
        viewAllButton.setFocusPainted(false);

        jPanel2.setBackground(new java.awt.Color(247, 248, 245));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
        dashboardPanel.setLayout(dashboardPanelLayout);
        dashboardPanelLayout.setHorizontalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dashboardPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2))
                                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(allFilterButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(bakingFilterButton)
                                        .addGap(26, 26, 26)
                                        .addComponent(stoveTopFilterButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(noCookFilterButton))))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dashboardPanelLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dashboardPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewAllButton)
                        .addGap(227, 227, 227))))
        );
        dashboardPanelLayout.setVerticalGroup(
            dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(44, 44, 44)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allFilterButton)
                    .addComponent(bakingFilterButton)
                    .addComponent(stoveTopFilterButton)
                    .addComponent(noCookFilterButton))
                .addGap(50, 50, 50)
                .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Baking", " Stove Top", " No Cook" }));

        sortByComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        sortByComboBox.setForeground(new java.awt.Color(102, 102, 102));
        sortByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Category", "Prep Time" }));

        recipesScrollPane.setBackground(new java.awt.Color(247, 248, 245));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(48, 60, 52));
        jLabel7.setText("Category");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(48, 60, 52));
        jLabel8.setText("Sort By");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(48, 60, 52));
        jLabel9.setText("Search recipes");

        javax.swing.GroupLayout recipesPanelLayout = new javax.swing.GroupLayout(recipesPanel);
        recipesPanel.setLayout(recipesPanelLayout);
        recipesPanelLayout.setHorizontalGroup(
            recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipesPanelLayout.createSequentialGroup()
                .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(recipesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(recipesPanelLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(jLabel6))
                        .addGroup(recipesPanelLayout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(recipesSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(112, 112, 112)
                            .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGap(46, 46, 46)
                            .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        recipesPanelLayout.setVerticalGroup(
            recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipesPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel6)
                .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(recipesPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, recipesPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(9, 9, 9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(recipesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recipesSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(recipesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        recipeTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baking", "Stove Top ", "No Cook" }));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(48, 82, 61));
        jLabel13.setText("Category *");

        recipeCategoryComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        recipeCategoryComboBox.setForeground(new java.awt.Color(102, 102, 102));
        recipeCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(48, 82, 61));
        jLabel14.setText("Preparation Time *");

        prepTimeField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

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

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(48, 82, 61));
        jLabel19.setText("Baking Time Unit *");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(48, 82, 61));
        jLabel20.setText("Oven Mode *");

        ovenModeComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        ovenModeComboBox.setForeground(new java.awt.Color(102, 102, 102));
        ovenModeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conventional", "Fan Forced", "Grill" }));

        javax.swing.GroupLayout bakingDetailsPanelLayout = new javax.swing.GroupLayout(bakingDetailsPanel);
        bakingDetailsPanel.setLayout(bakingDetailsPanelLayout);
        bakingDetailsPanelLayout.setHorizontalGroup(
            bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bakingDetailsPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bakingDetailsPanelLayout.createSequentialGroup()
                        .addComponent(ovenModeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(bakingDetailsPanelLayout.createSequentialGroup()
                        .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(bakingDetailsPanelLayout.createSequentialGroup()
                                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(ovenTemperatureField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(82, 82, 82)
                                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(temperatureUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89)
                                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bakingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(28, 28, 28)
                                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(bakingTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        bakingDetailsPanelLayout.setVerticalGroup(
            bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bakingDetailsPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bakingDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ovenTemperatureField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temperatureUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bakingTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bakingTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ovenModeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        heatLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", " High" }));

        javax.swing.GroupLayout stoveDetailsPanelLayout = new javax.swing.GroupLayout(stoveDetailsPanel);
        stoveDetailsPanel.setLayout(stoveDetailsPanelLayout);
        stoveDetailsPanelLayout.setHorizontalGroup(
            stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stoveDetailsPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cookingTimeField))
                .addGap(154, 154, 154)
                .addGroup(stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(cookingTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(175, 175, 175)
                .addGroup(stoveDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(heatLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        ingredientsPanel.add(ingredientNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(48, 82, 61));
        jLabel28.setText("Quantity *");
        ingredientsPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));

        ingredientQuantityField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        ingredientQuantityField.addActionListener(this::ingredientQuantityFieldActionPerformed);
        ingredientsPanel.add(ingredientQuantityField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(48, 79, 61));
        jLabel29.setText("Unit *");
        ingredientsPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, -1));

        ingredientUnitField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        ingredientsPanel.add(ingredientUnitField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        addIngredientButton.setBackground(new java.awt.Color(145, 177, 156));
        addIngredientButton.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        addIngredientButton.setForeground(new java.awt.Color(255, 255, 255));
        addIngredientButton.setText("+ Add Ingredient");
        ingredientsPanel.add(addIngredientButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, -1));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(60, 30));

        jList1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        ingredientsPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 430, 30));

        removeIngredientButton.setBackground(new java.awt.Color(145, 177, 156));
        removeIngredientButton.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        removeIngredientButton.setText("- Remove Selected");
        removeIngredientButton.addActionListener(this::removeIngredientButtonActionPerformed);
        ingredientsPanel.add(removeIngredientButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        instructionsPanel.setBackground(new java.awt.Color(247, 248, 245));
        instructionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(48, 82, 61));
        jLabel30.setText("Instructions");
        instructionsPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        instructionsTextArea.setColumns(20);
        instructionsTextArea.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        instructionsTextArea.setLineWrap(true);
        instructionsTextArea.setRows(5);
        instructionsTextArea.setWrapStyleWord(true);
        instructionsScrollPane.setViewportView(instructionsTextArea);

        instructionsPanel.add(instructionsScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 440, 60));

        cancelRecipeButton.setBackground(new java.awt.Color(145, 177, 156));
        cancelRecipeButton.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        cancelRecipeButton.setText("Cancel");
        instructionsPanel.add(cancelRecipeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        saveRecipeButton.setBackground(new java.awt.Color(145, 177, 156));
        saveRecipeButton.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        saveRecipeButton.setForeground(new java.awt.Color(255, 255, 255));
        saveRecipeButton.setText("Save Recipe");
        instructionsPanel.add(saveRecipeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, -1));

        javax.swing.GroupLayout addRecipePanelLayout = new javax.swing.GroupLayout(addRecipePanel);
        addRecipePanel.setLayout(addRecipePanelLayout);
        addRecipePanelLayout.setHorizontalGroup(
            addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addRecipePanelLayout.createSequentialGroup()
                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addRecipePanelLayout.createSequentialGroup()
                        .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addRecipePanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(recipeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)
                                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(recipeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(91, 91, 91)
                                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(recipeCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(addRecipePanelLayout.createSequentialGroup()
                                        .addComponent(prepTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(prepTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(addRecipePanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ingredientsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                                    .addComponent(instructionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(addRecipePanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel15))
                            .addGroup(addRecipePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 64, Short.MAX_VALUE))
                    .addGroup(addRecipePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(cookingDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        addRecipePanelLayout.setVerticalGroup(
            addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addRecipePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(27, 27, 27)
                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addRecipePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recipeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recipeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recipeCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prepTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prepTimeUnitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(cookingDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ingredientsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instructionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        favouritesPanel.add(favouritesSearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 250, 30));

        jLabel33.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel33.setText("Search favourites...");
        favouritesPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        favouritesTypeComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        favouritesTypeComboBox.setForeground(new java.awt.Color(102, 102, 102));
        favouritesTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All ", "Baking", "Stove Top", "No Cook" }));
        favouritesPanel.add(favouritesTypeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 100, 30));

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(48, 82, 61));
        jLabel34.setText("Recipe Type");
        favouritesPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        favouritesSortComboBox.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        favouritesSortComboBox.setForeground(new java.awt.Color(102, 102, 102));
        favouritesSortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name (A–Z)", "Name (Z–A)" }));
        favouritesPanel.add(favouritesSortComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 110, 30));

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(48, 82, 61));
        jLabel35.setText("Sort By");
        favouritesPanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, -1, -1));

        favouritesScrollPane.setBackground(new java.awt.Color(247, 248, 245));
        favouritesScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        favouritesListPanel.setBackground(new java.awt.Color(247, 248, 245));
        favouritesListPanel.setLayout(new java.awt.GridLayout(0, 3, 15, 15));
        favouritesScrollPane.setViewportView(favouritesListPanel);

        favouritesPanel.add(favouritesScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 810, 430));

        contentPanel.add(favouritesPanel, "card5");

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

        weeklyMealPlanPanel.add(mealPlanScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 540, 480));

        contentPanel.add(weeklyMealPlanPanel, "card6");

        recipeDetailsPanel.setBackground(new java.awt.Color(247, 248, 245));
        recipeDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backToRecipesButton.setBackground(new java.awt.Color(247, 248, 245));
        backToRecipesButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        backToRecipesButton.setText("← Back");
        backToRecipesButton.setBorderPainted(false);
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
        recipeDetailsFavouriteButton.addActionListener(this::recipeDetailsFavouriteButtonActionPerformed);
        recipeDetailsPanel.add(recipeDetailsFavouriteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 50, 70));

        recipeDetailsTypeLabel.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        recipeDetailsTypeLabel.setForeground(new java.awt.Color(95, 125, 100));
        recipeDetailsTypeLabel.setText("Baking");
        recipeDetailsPanel.add(recipeDetailsTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, -1, -1));

        recipeDetailsTimeLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        recipeDetailsTimeLabel.setForeground(new java.awt.Color(102, 102, 102));
        recipeDetailsTimeLabel.setText("1h 20m");
        recipeDetailsPanel.add(recipeDetailsTimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, -1, -1));

        ingredientsTabButton.setBackground(new java.awt.Color(232, 241, 232));
        ingredientsTabButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ingredientsTabButton.setForeground(new java.awt.Color(48, 82, 61));
        ingredientsTabButton.setText("Ingredients");
        ingredientsTabButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(190, 205, 190), 1, true));
        recipeDetailsPanel.add(ingredientsTabButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 145, 40));

        instructionsTabButton.setBackground(new java.awt.Color(232, 241, 232));
        instructionsTabButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        instructionsTabButton.setForeground(new java.awt.Color(48, 82, 61));
        instructionsTabButton.setText("Instructions");
        instructionsTabButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(190, 205, 190), 1, true));
        recipeDetailsPanel.add(instructionsTabButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 145, 40));

        detailsTabButton.setBackground(new java.awt.Color(232, 241, 232));
        detailsTabButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        detailsTabButton.setForeground(new java.awt.Color(48, 82, 61));
        detailsTabButton.setText("Details");
        detailsTabButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(190, 205, 190), 1, true));
        recipeDetailsPanel.add(detailsTabButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, 145, 40));

        editRecipeButton.setBackground(new java.awt.Color(48, 82, 61));
        editRecipeButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        editRecipeButton.setForeground(new java.awt.Color(255, 255, 255));
        editRecipeButton.setText("Edit Recipe");
        editRecipeButton.setBorderPainted(false);
        recipeDetailsPanel.add(editRecipeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 610, 130, 40));

        deleteRecipeButton.setBackground(new java.awt.Color(247, 226, 226));
        deleteRecipeButton.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        deleteRecipeButton.setForeground(new java.awt.Color(161, 62, 62));
        deleteRecipeButton.setText("Delete");
        deleteRecipeButton.setBorderPainted(false);
        recipeDetailsPanel.add(deleteRecipeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 540, 110, 40));

        jLabel37.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel37.setText("⏲ \tPrep time:");
        recipeDetailsPanel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        jLabel38.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel38.setText("🏷 Category:");
        recipeDetailsPanel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, -1, -1));

        recipeDetailsCategoryLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        recipeDetailsCategoryLabel.setForeground(new java.awt.Color(102, 102, 102));
        recipeDetailsCategoryLabel.setText("dessert");
        recipeDetailsPanel.add(recipeDetailsCategoryLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, -1, -1));

        recipeInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        recipeInfoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 205, 190)));
        recipeInfoPanel.setLayout(new java.awt.CardLayout());

        ingredientsInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        ingredientsInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ingredientsHeadingLabel.setFont(new java.awt.Font("SansSerif", 3, 16)); // NOI18N
        ingredientsHeadingLabel.setForeground(new java.awt.Color(48, 82, 61));
        ingredientsHeadingLabel.setText("Ingredients");
        ingredientsInfoPanel.add(ingredientsHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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

        ingredientsInfoPanel.add(ingredientsScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 510, 170));

        recipeInfoPanel.add(ingredientsInfoPanel, "card2");

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

        instructionsInfoPanel.add(instructionScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 470, 180));

        recipeInfoPanel.add(instructionsInfoPanel, "card3");

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

        recipeInfoPanel.add(cookingInfoPanel, "card4");

        recipeDetailsPanel.add(recipeInfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 610, 260));

        contentPanel.add(recipeDetailsPanel, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, Short.MAX_VALUE)
            .addComponent(sidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void allFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allFilterButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_allFilterButtonActionPerformed

    private void recipesSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recipesSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recipesSearchFieldActionPerformed

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

    private void removeIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeIngredientButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeIngredientButtonActionPerformed

    private void clearPlanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPlanButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearPlanButtonActionPerformed

    private void backToRecipesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToRecipesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backToRecipesButtonActionPerformed

    private void recipeDetailsFavouriteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recipeDetailsFavouriteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recipeDetailsFavouriteButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new RecipeMainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addIngredientButton;
    private javax.swing.JButton addRecipeButton;
    private javax.swing.JPanel addRecipePanel;
    private javax.swing.JButton allFilterButton;
    private javax.swing.JButton backToRecipesButton;
    private javax.swing.JPanel bakingDetailsPanel;
    private javax.swing.JButton bakingFilterButton;
    private javax.swing.JTextField bakingTimeField;
    private javax.swing.JComboBox<String> bakingTimeUnitComboBox;
    private javax.swing.JButton cancelRecipeButton;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JTextField chillTimeField;
    private javax.swing.JComboBox<String> chillTimeUnitComboBox;
    private javax.swing.JButton clearPlanButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel cookingDetailOneLabel;
    private javax.swing.JLabel cookingDetailThreeLabel;
    private javax.swing.JLabel cookingDetailTwoLabel;
    private javax.swing.JLabel cookingDetailsHeadingLabel;
    private javax.swing.JPanel cookingDetailsPanel;
    private javax.swing.JPanel cookingInfoPanel;
    private javax.swing.JTextField cookingTimeField;
    private javax.swing.JComboBox<String> cookingTimeUnitComboBox;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JButton deleteRecipeButton;
    private javax.swing.JButton detailsTabButton;
    private javax.swing.JButton editRecipeButton;
    private javax.swing.JButton favouritesButton;
    private javax.swing.JPanel favouritesListPanel;
    private javax.swing.JPanel favouritesPanel;
    private javax.swing.JScrollPane favouritesScrollPane;
    private javax.swing.JTextField favouritesSearchField;
    private javax.swing.JComboBox<String> favouritesSortComboBox;
    private javax.swing.JComboBox<String> favouritesTypeComboBox;
    private javax.swing.JComboBox<String> heatLevelComboBox;
    private javax.swing.JButton homeButton;
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
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mealPlanButton;
    private javax.swing.JScrollPane mealPlanScrollPane;
    private javax.swing.JTable mealPlanTable;
    private javax.swing.JButton myRecipesButton;
    private javax.swing.JPanel noCookDetailsPanel;
    private javax.swing.JButton noCookFilterButton;
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
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JComboBox<String> sortByComboBox;
    private javax.swing.JPanel stoveDetailsPanel;
    private javax.swing.JButton stoveTopFilterButton;
    private javax.swing.JComboBox<String> temperatureUnitComboBox;
    private javax.swing.JButton viewAllButton;
    private javax.swing.JPanel weeklyMealPlanPanel;
    // End of variables declaration//GEN-END:variables
}
