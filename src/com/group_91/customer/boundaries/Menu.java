package com.group_91.customer.boundaries;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.group_91.customer.controls.MenuControl;
import com.group_91.customer.entities.AddOnsInfo;
import com.group_91.customer.entities.OrderSuits;
import com.group_91.customer.entities.Restaurant;
import com.group_91.customer.entities.SuitInfo;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.group_91.customer.controls.MenuControl.GENERAL_OPTIONS_AMOUNT;

/**
 * Interface for Menu.
 *
 * @author Huixin Sun, Yingke Ding
 */
public class Menu extends JFrame {
    private JPanel menuPanel;

    // v3: 重构ButtonGroup
    private HashMap<String, ButtonGroup> generalOptionsButtonGroups;
    private HashMap<String, ButtonGroup> booleanOptionsButtonGroups;
    private JSlider spicinessSlider;
    private JButton payButton, toCartButton, viewCartButton;
    private JLabel errorLabel;


    public Menu() throws HeadlessException {

        // 1. Interface Code.
        generalOptionsButtonGroups = new HashMap<>();
        booleanOptionsButtonGroups = new HashMap<>();

        menuPanel = new JPanel();
        menuPanel.setSize(500, 575);
        menuPanel.setBorder(BorderFactory.createTitledBorder(" "));
        menuPanel.setLayout(null);
        menuPanel.setOpaque(false);

        // General Options Interface
        ArrayList<String> generalOptionsNames = new ArrayList<>(SuitInfo.getGeneralOptions().keySet());

        for (int i = 0; i < GENERAL_OPTIONS_AMOUNT; ++i) {
            String generalOptionName = generalOptionsNames.get(i);
            JLabel generalOptionNameLabel = new JLabel(generalOptionName, JLabel.LEFT);
            JSONArray generalOptionChoicesNamesAndStatuses = SuitInfo.getGeneralChoicesNameAndStatus(generalOptionsNames.get(i));

            ButtonGroup generalOptionButtonGroup = new ButtonGroup();


            // General option's name label.
            // e.g. soup
            generalOptionNameLabel.setBounds(55 + 140 * i, 40, 100, 30);
            generalOptionNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
            menuPanel.add(generalOptionNameLabel);


            // 3 or more choices for an option.
            for (int j = 0; j < generalOptionChoicesNamesAndStatuses.size(); ++j) {
                String choiceName = (String) ((JSONObject) generalOptionChoicesNamesAndStatuses.get(j)).get("name");
                boolean choiceStatus = (boolean) ((JSONObject) generalOptionChoicesNamesAndStatuses.get(j)).get("status");

                JRadioButton radioButton = new JRadioButton(choiceName);  // choiceName displayed next to the RadioButton
                radioButton.setName(choiceName);  // The name of the RadioButton. Easy to find for ActionListener.
                radioButton.setBounds(50 + 140 * i, 85 + 30 * j, 120, 20);
                radioButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
                radioButton.setOpaque(false);
                radioButton.setEnabled(choiceStatus);  // IF CHOICE NOT AVAILABLE, DISABLE BUTTON

                generalOptionButtonGroup.add(radioButton);
                menuPanel.add(radioButton);

            }

            generalOptionsButtonGroups.put(generalOptionName, generalOptionButtonGroup);

        }


        // Boolean Options Interface
        int booleanOptionsNum = SuitInfo.getBooleanOptions().size();

        for (int i = 0; i < booleanOptionsNum; ++i) {
            String booleanOptionChoiceName = (String) ((JSONObject) SuitInfo.getBooleanOptions().get(i)).get("name");
            boolean choiceStatus = (boolean) ((JSONObject) SuitInfo.getBooleanOptions().get(i)).get("status");
            JLabel booleanOptionChoiceLabel = new JLabel(booleanOptionChoiceName, JLabel.LEFT);

            // JLabels
            booleanOptionChoiceLabel.setBounds(55, 180 + 30 * i, 100, 30);
            booleanOptionChoiceLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
            menuPanel.add(booleanOptionChoiceLabel);


            // JRadioButtons
            ButtonGroup booleanOptionButtonGroup = new ButtonGroup();

            JRadioButton yesButton = new JRadioButton("Yes");
            yesButton.setBounds(190, 185 + 30 * i, 70, 20);
            yesButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
            yesButton.setEnabled(choiceStatus);
            yesButton.setOpaque(false);

            JRadioButton noButton = new JRadioButton("No");
            noButton.setBounds(330, 185 + 30 * i, 70, 20);
            noButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
            noButton.setEnabled(choiceStatus);
            noButton.setSelected(!choiceStatus);
            noButton.setOpaque(false);

            booleanOptionButtonGroup.add(yesButton);
            booleanOptionButtonGroup.add(noButton);

            menuPanel.add(yesButton);
            menuPanel.add(noButton);

            booleanOptionsButtonGroups.put(booleanOptionChoiceName, booleanOptionButtonGroup);

        }


        // Int Option Interface (Only one intOption) in v3
        String intOptionName = (String) SuitInfo.getIntOptions().get(0);
        JLabel spicinessLabel = new JLabel(intOptionName, JLabel.LEFT);

        spicinessLabel.setBounds(55, 280, 100, 30);
        spicinessLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        spicinessLabel.setForeground(Color.red);
        menuPanel.add(spicinessLabel);

        spicinessSlider = new JSlider(0, 5);
        spicinessSlider.setMajorTickSpacing(1);
        spicinessSlider.setPaintLabels(true);
        spicinessSlider.setBounds(190, 270, 180, 50);
        spicinessSlider.setPaintTicks(true);
        spicinessSlider.setSnapToTicks(true);  // discrete slider
        spicinessSlider.setOpaque(false);

        menuPanel.add(spicinessSlider);


        errorLabel = new JLabel("Please select all fixed options!");
        errorLabel.setBounds(160, 160, 250, 30);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        menuPanel.add(errorLabel);


        // Add on Info
        new AddOnsInfo(Restaurant.getRestaurantID());  // 让AddOn信息活起来

        JLabel j1 = new JLabel("Add On", JLabel.LEFT);
        j1.setBounds(55, 320, 70, 30);
        j1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        j1.setBackground(Color.BLUE);
        menuPanel.add(j1);

        JLabel j2 = new JLabel("Price", JLabel.LEFT);
        j2.setBounds(195, 320, 70, 30);
        j2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        j2.setForeground(Color.black);
        menuPanel.add(j2);

        JLabel j3 = new JLabel("Amount", JLabel.LEFT);
        j3.setBounds(335, 320, 70, 30);
        j3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        j3.setForeground(Color.black);
        menuPanel.add(j3);


        ArrayList<String> addOnsNamesArray = new ArrayList<>(AddOnsInfo.getAddOnsInfo().keySet());   // add_on名字集
        ArrayList<Object> addOnsPricesArray = new ArrayList<>(AddOnsInfo.getAddOnsInfo().values());  // add_on价格集, 价格为BigDecimal
        OrderSuits.initAddOnsSelected(addOnsNamesArray);  // 如果该类第一次调用, 就会init, 否则不init. init后每个add on都为0

        JLabel[] addOnsNamesLabel = new JLabel[addOnsNamesArray.size()];
        JLabel[] addOnsPricesLabel = new JLabel[addOnsPricesArray.size()];
        JLabel[] addOnsNumsLabel = new JLabel[addOnsNamesLabel.length];
        JButton[] minusButtons = new JButton[addOnsNamesLabel.length];
        JButton[] plusButtons = new JButton[addOnsNamesLabel.length];

        for (int i = 0; i < addOnsNamesLabel.length; ++i) {
            // Names
            addOnsNamesLabel[i] = new JLabel(addOnsNamesArray.get(i), JLabel.LEFT);
            addOnsNamesLabel[i].setBounds(55, 350 + 40 * i, 100, 30);
            addOnsNamesLabel[i].setFont(new Font("Times New Roman", Font.BOLD, 12));
            addOnsNamesLabel[i].setForeground(Color.black);
            menuPanel.add(addOnsNamesLabel[i]);

            // Prices
            double price = ((BigDecimal) addOnsPricesArray.get(i)).doubleValue();

            if (price > 0.0d) {
                addOnsPricesLabel[i] = new JLabel(String.valueOf(price), JLabel.CENTER);
                addOnsPricesLabel[i].setBounds(160, 350 + 40 * i, 100, 30);
                addOnsPricesLabel[i].setFont(new Font("Times New Roman", Font.BOLD, 12));
                addOnsPricesLabel[i].setBackground(Color.black);
                menuPanel.add(addOnsPricesLabel[i]);


                // Nums
                int thisAddOnNum = OrderSuits.getAddOnsSelected().get(addOnsNamesArray.get(i));

                addOnsNumsLabel[i] = new JLabel(String.valueOf(thisAddOnNum), JLabel.CENTER);
                addOnsNumsLabel[i].setBounds(315, 350 + 40 * i, 100, 30);
                addOnsNumsLabel[i].setFont(new Font("Times New Roman", Font.BOLD, 12));
                addOnsNumsLabel[i].setBackground(Color.black);
                menuPanel.add(addOnsNumsLabel[i]);


                // Minus Button
                String addOnName = addOnsNamesArray.get(i);
                minusButtons[i] = new JButton("-");
                minusButtons[i].setFont(new Font("Times New Roman", Font.BOLD, 8));
                minusButtons[i].setBounds(315, 358 + 40 * i, 40, 15);

                // 小功能就不放到Control里了
                int finalI = i;
                minusButtons[i].addActionListener(e -> {
                    int originalNum = OrderSuits.getAddOnsSelected().get(addOnName);

                    if (originalNum > 0) {
                        OrderSuits.getAddOnsSelected().put(addOnName, originalNum - 1);
                        addOnsNumsLabel[finalI].setText("" + (originalNum - 1));
                    }

                });

                menuPanel.add(minusButtons[i]);


                // Plus Button
                plusButtons[i] = new JButton("+");
                plusButtons[i].setFont(new Font("Times New Roman", Font.BOLD, 8));
                plusButtons[i].setBounds(375, 358 + 40 * i, 40, 15);

                // 小功能就不放到control里了
                int finalI2 = i;
                plusButtons[i].addActionListener(e -> {
                    int originalNum = OrderSuits.getAddOnsSelected().get(addOnName);

                    OrderSuits.getAddOnsSelected().put(addOnName, originalNum + 1);
                    addOnsNumsLabel[finalI2].setText("" + (originalNum + 1));
                });

                menuPanel.add(plusButtons[i]);

            }
            else {
                addOnsNamesLabel[i].setVisible(false);
            }

        }


        payButton = new JButton("Pay now");
        payButton.setBounds(315, 510, 100, 40);
        menuPanel.add(payButton);

        toCartButton = new JButton("Add to Cart");
        toCartButton.setBounds(55, 510, 100, 40);
        menuPanel.add(toCartButton);


        viewCartButton = new JButton("View Cart");
        viewCartButton.setBounds(190, 510, 100, 40);
        menuPanel.add(viewCartButton);





        // Register listener here.
        MenuControl control = new MenuControl(menuPanel, generalOptionsButtonGroups, booleanOptionsButtonGroups, spicinessSlider, payButton, toCartButton, viewCartButton, errorLabel);
        payButton.addActionListener(control);
        toCartButton.addActionListener(control);
        viewCartButton.addActionListener(control);


        menuPanel.setBackground(Color.white);

    }


    /**
     * Get the last selected menu pane.
     * @return JPanel.
     */
    public JPanel getOriginalPane() {

        for (Map.Entry<String, ButtonGroup> stringButtonGroupEntry : generalOptionsButtonGroups.entrySet()) {
            String generalOptionName = stringButtonGroupEntry.getKey();
            String generalOptionSelected = OrderSuits.getGeneralOptionsSelected().get(generalOptionName);
            ButtonGroup generalOptionButtonGroup = stringButtonGroupEntry.getValue();

            for (Enumeration<AbstractButton> buttons = generalOptionButtonGroup.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton theButton = buttons.nextElement();

                if (theButton.getText().equals(generalOptionSelected)) {
                    theButton.setSelected(true);
                }
            }

        }


        for (Map.Entry<String, ButtonGroup> stringButtonGroupEntry : booleanOptionsButtonGroups.entrySet()) {
            String booleanOptionName = stringButtonGroupEntry.getKey();
            boolean booleanOptionSelected = OrderSuits.getBooleanOptionsSelected().get(booleanOptionName);
            String booleanOptionSelectedString;
            if (booleanOptionSelected) {
                booleanOptionSelectedString = "Yes";
            }
            else {
                booleanOptionSelectedString = "No";
            }
            ButtonGroup booleanOptionButtonGroup = stringButtonGroupEntry.getValue();

            for (Enumeration<AbstractButton> buttons = booleanOptionButtonGroup.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton theButton = buttons.nextElement();

                if (theButton.getText().equals(booleanOptionSelectedString)) {
                    theButton.setSelected(true);
                }
            }

        }

        spicinessSlider.setValue(OrderSuits.getIntOptionsSelected().get("Spiciness"));

        return menuPanel;
    }


    public JPanel getOriginalPane(JSONObject suitInfo) {
        for (Map.Entry<String, ButtonGroup> stringButtonGroupEntry : generalOptionsButtonGroups.entrySet()) {
            String generalOptionName = stringButtonGroupEntry.getKey();
            String generalOptionSelected = (String) suitInfo.get(generalOptionName);

            ButtonGroup generalOptionButtonGroup = stringButtonGroupEntry.getValue();

            for (Enumeration<AbstractButton> buttons = generalOptionButtonGroup.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton theButton = buttons.nextElement();

                if (theButton.getText().equals(generalOptionSelected)) {
                    theButton.setSelected(true);
                }
            }

        }

        for (Map.Entry<String, ButtonGroup> stringButtonGroupEntry : booleanOptionsButtonGroups.entrySet()) {
            String booleanOptionName = stringButtonGroupEntry.getKey();
            boolean booleanOptionSelected = (boolean) suitInfo.get(booleanOptionName);

            String booleanOptionSelectedString;
            if (booleanOptionSelected) {
                booleanOptionSelectedString = "Yes";
            }
            else {
                booleanOptionSelectedString = "No";
            }
            ButtonGroup booleanOptionButtonGroup = stringButtonGroupEntry.getValue();

            for (Enumeration<AbstractButton> buttons = booleanOptionButtonGroup.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton theButton = buttons.nextElement();

                if (theButton.getText().equals(booleanOptionSelectedString)) {
                    theButton.setSelected(true);
                }
            }

        }

        spicinessSlider.setValue((Integer) suitInfo.get("Spiciness"));

        return menuPanel;
    }


    /**
     * Get this panel.
     * @return JPanel
     */
    public JPanel getMenuPanel() {
        return menuPanel;
    }

}
