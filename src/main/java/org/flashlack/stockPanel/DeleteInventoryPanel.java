package org.flashlack.stockPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.FoodDO;
import org.flashlack.mappers.impl.FoodInventoryImpl;

import javax.swing.*;
import java.awt.*;

/**
 * @author FLASHLACK
 */
public class DeleteInventoryPanel extends JPanel {
    private final JTextField foodNumberField;

    public DeleteInventoryPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        foodNumberField = UIUtils.addLabeledTextField(this, gbc, "食品编号:", 0);
        // 确认删除按钮
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = new JButton("确认删除");
        deleteButton.addActionListener(e -> {
            FoodDO foodDO = new FoodDO();
            foodDO.setFoodNumber(Integer.valueOf(foodNumberField.getText()));
            //首先去搜索是否存在这个编号
            FoodInventoryImpl foodInventory = new FoodInventoryImpl();
            if (foodInventory.getFoodDO(foodDO) != null) {
                try {
                    foodInventory.deleteFood(foodDO);
                    JOptionPane.showMessageDialog(DeleteInventoryPanel.this, "删除成功 ", "成功", JOptionPane.INFORMATION_MESSAGE);
                } catch (RuntimeException err) {
                    err.printStackTrace();
                    JOptionPane.showMessageDialog(DeleteInventoryPanel.this, "删除失败 " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(DeleteInventoryPanel.this, "编号不存在 " , "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(deleteButton, gbc);

        // 清除按钮
        gbc.gridx = 2;
        JButton clearButton = new JButton("清除");
        clearButton.addActionListener(e -> foodNumberField.setText(""));
        add(clearButton, gbc);

        // 返回查询界面按钮
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton backUpdateButton = new JButton("返回查询界面");
        backUpdateButton.addActionListener(e -> cardLayout.show(mainPanel, "QueryInventory"));
        add(backUpdateButton, gbc);

        // 返回菜单按钮
        gbc.gridx = 2;
        JButton backButton = new JButton("返回菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
    }
}

