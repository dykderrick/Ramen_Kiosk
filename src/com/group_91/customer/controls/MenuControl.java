package com.group_91.customer.controls;

import com.group_91.customer.boundaries.CartPanel;
import com.group_91.customer.boundaries.DiningOptions;
import com.group_91.customer.boundaries.Menu;
import com.group_91.customer.entities.OrderSuits;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Control class of {@link Menu}
 * Button listeners, JSON File I/O here.
 *
 * @author Huixin Sun, Yingke Ding
 */
public class MenuControl implements ActionListener {
    public static final int GENERAL_OPTIONS_AMOUNT = 3;  // 只有soup, noodles, 和onion

    private final JPanel menuPanel;

    private final HashMap<String, ButtonGroup> generalOptionsButtonGroups;
    private final HashMap<String, ButtonGroup> booleanOptionsButtonGroups;
    private final JSlider spicinessSlider;
    private final JButton payButton, toCartButton, viewCartButton;
    private final JLabel errorLabel;

    /**
     * Constructor.
     * @param menuPanel main panel for {@link Menu}.
     * @param generalOptionsButtonGroups String-ButtonGroup key-value pairs. String is the name for general options, and ButtonGroup is a collection of {@link JRadioButton}s.
     * @param booleanOptionsButtonGroups String-ButtonGroup key-value pairs. String is the name for boolean options, and ButtonGroup is a collection of {@link JRadioButton}s.
     * @param spicinessSlider A {@link JSlider} for changing int options of a specific suit.
     * @param payButton Select pay button.
     * @param toCartButton Select add-to-cart button.
     * @param errorLabel Show some errors on the panel if some mistakes.
     */
    public MenuControl(JPanel menuPanel, HashMap<String, ButtonGroup> generalOptionsButtonGroups, HashMap<String, ButtonGroup> booleanOptionsButtonGroups, JSlider spicinessSlider, JButton payButton, JButton toCartButton, JButton viewCartButton, JLabel errorLabel) {
        this.menuPanel = menuPanel;
        this.generalOptionsButtonGroups = generalOptionsButtonGroups;
        this.booleanOptionsButtonGroups = booleanOptionsButtonGroups;
        this.spicinessSlider = spicinessSlider;
        this.payButton = payButton;
        this.toCartButton = toCartButton;
        this.viewCartButton = viewCartButton;
        this.errorLabel = errorLabel;
    }


    /**
     * Check whether the Radio Buttons has all been selected.
     * @return true if all selected.
     */
    public boolean isAllSelected() {
        return isButtonGroupsSelected(generalOptionsButtonGroups) && isButtonGroupsSelected(booleanOptionsButtonGroups);
    }


    /**
     * Check whether some ButtonGroups has selections.
     * @param ButtonGroups HashMap.
     * @return true if valid.
     */
    private boolean isButtonGroupsSelected(HashMap<String, ButtonGroup> ButtonGroups) {

        for (Map.Entry<String, ButtonGroup> stringButtonGroupEntry : ButtonGroups.entrySet()) {
            String generalOptionName = stringButtonGroupEntry.getKey();
            ButtonGroup generalOptionButtonGroup = stringButtonGroupEntry.getValue();
            boolean currentGeneralOptionValid = false;

            for (Enumeration<AbstractButton> buttons = generalOptionButtonGroup.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton theButton = buttons.nextElement();

                if (theButton.isSelected()) {
                    currentGeneralOptionValid = true;
                }

            }

            if (!currentGeneralOptionValid) {
                return false;
            }

        }

        return true;
    }


    /**
     * Get the current pane's selections (fixed), and save them to {@link OrderSuits}.
     */
    private void setASuitFixedInfo() {
        // 记录General Options信息
        for (Map.Entry<String, ButtonGroup> stringButtonGroupEntry : generalOptionsButtonGroups.entrySet()) {
            String generalOptionName = stringButtonGroupEntry.getKey();
            ButtonGroup generalOptionButtonGroup = stringButtonGroupEntry.getValue();

            // 获取所有RadioButtons后判断是否选中
            for (Enumeration<AbstractButton> buttons = generalOptionButtonGroup.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton theButton = buttons.nextElement();

                if (theButton.isSelected()) {
                    OrderSuits.putAGeneralOptionSelected(generalOptionName, theButton.getText());
                }
            }

        }


        // 记录Boolean Options信息
        for (Map.Entry<String, ButtonGroup> stringButtonGroupEntry : booleanOptionsButtonGroups.entrySet()) {
            String booleanOptionName = stringButtonGroupEntry.getKey();
            ButtonGroup booleanOptionButtonGroup = stringButtonGroupEntry.getValue();

            for (Enumeration<AbstractButton> buttons = booleanOptionButtonGroup.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton theButton = buttons.nextElement();

                if (theButton.isSelected()) {
                    OrderSuits.putABooleanOptionSelected(booleanOptionName, theButton.getText().equals("Yes"));
                }
            }

        }


        // 记录Int Options信息
        OrderSuits.putAnIntOptionSelected("Spiciness", spicinessSlider.getValue());  // 简单点直接放Spiciness了, 后面有时间要改

    }


    /**
     * Invoked when buttons in {@link Menu} are clicked.
     *
     * @param e the clicking event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            if (isAllSelected()) {
                errorLabel.setVisible(false);
                setASuitFixedInfo();
                OrderSuits.setSuitsRecord();  // 保存这个suit

                menuPanel.removeAll();
                menuPanel.repaint();
                menuPanel.add(new DiningOptions().getDiningOptionsPanel());
                menuPanel.revalidate();
            }
            else {
                errorLabel.setVisible(true);
            }

        }
        else if (e.getSource() == toCartButton) {
            if (isAllSelected()) {
                errorLabel.setVisible(false);
                setASuitFixedInfo();
                OrderSuits.setSuitsRecord();

                JOptionPane.showMessageDialog(null, "Added to cart.");

                menuPanel.removeAll();
                menuPanel.repaint();
                menuPanel.add(new Menu().getMenuPanel());
                menuPanel.revalidate();

            }
            else {
                errorLabel.setVisible(true);
            }
        }
        else if (e.getSource() == viewCartButton) {
            menuPanel.removeAll();
            menuPanel.repaint();
            menuPanel.add(new CartPanel().getCartPanel());
            menuPanel.revalidate();
        }



    }

}
