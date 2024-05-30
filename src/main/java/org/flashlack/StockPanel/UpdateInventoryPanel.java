package org.flashlack.StockPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.FoodDO;
import org.flashlack.mappers.impl.FoodInventoryImpl;

import javax.swing.*;
import java.awt.*;

/**
 * @author FLAHLACK
 */
public class UpdateInventoryPanel extends JPanel {
    private final JCheckBox foodNumberCheckBox;
    private final JTextField foodNumberField;
    private final JCheckBox foodNameCheckBox;
    private final JTextField foodNameField;
    private final JCheckBox foodCategoryCheckBox;
    private final JTextField foodCategoryField;
    private final JCheckBox foodPriceCheckBox;
    private final JTextField foodPriceField;
    private final JCheckBox stockQuantityCheckBox;
    private final JTextField stockQuantityField;
    private final JCheckBox supplierNumberCheckBox;
    private final JTextField supplierNumberField;

    public UpdateInventoryPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 1, 5, 1);

        foodNumberCheckBox = new JCheckBox();
        foodNumberField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodNumberCheckBox, "食品编号:", 0);

        foodNameCheckBox = new JCheckBox();
        foodNameField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodNameCheckBox, "食品名称:", 1);

        foodCategoryCheckBox = new JCheckBox();
        foodCategoryField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodCategoryCheckBox, "食品类别:", 2);

        foodPriceCheckBox = new JCheckBox();
        foodPriceField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodPriceCheckBox, "食品进价:", 3);

        stockQuantityCheckBox = new JCheckBox();
        stockQuantityField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, stockQuantityCheckBox, "库存量:", 4);

        supplierNumberCheckBox = new JCheckBox();
        supplierNumberField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, supplierNumberCheckBox, "供应商名称:", 5);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton updateButton = new JButton("更新");
        //更新监视器
        updateButton.addActionListener(e -> {
            FoodDO foodDO = new FoodDO();
            //首先获取值
            if (foodNumberCheckBox.isSelected()) {
                foodDO.setFoodNumber(Integer.valueOf(foodNumberField.getText()));
            }
            if (foodNameCheckBox.isSelected()) {
                foodDO.setFoodName(foodNameField.getText());
            }
            if (foodCategoryCheckBox.isSelected()) {
                foodDO.setFoodCategory(foodCategoryField.getText());
            }
            if (foodPriceCheckBox.isSelected()) {
                foodDO.setFoodPrice(foodPriceField.getText());
            }
            if (stockQuantityCheckBox.isSelected()) {
                foodDO.setStockQuantity(stockQuantityField.getText());
            }
            if (supplierNumberCheckBox.isSelected()) {
                foodDO.setSupplierNumber(Integer.valueOf(supplierNumberField.getText()));
            }
            //进行查询
            FoodInventoryImpl foodInventory = new FoodInventoryImpl();
            try{
                FoodDO odlFood = foodInventory.getFoodDO(foodDO);
                if (odlFood!=null) {
                    //进行新值与旧值的交换修改
                    if (foodDO.getFoodName() == null){
                        foodDO.setFoodName(odlFood.getFoodName());
                    }
                    if (foodDO.getFoodCategory() == null){
                        foodDO.setFoodCategory(odlFood.getFoodCategory());
                    }
                    if (foodDO.getFoodPrice() == null){
                        foodDO.setFoodPrice(odlFood.getFoodPrice());
                    }
                    if (foodDO.getStockQuantity() == null){
                        foodDO.setStockQuantity(odlFood.getStockQuantity());
                    }
                    if (foodDO.getSupplierNumber() == null){
                        foodDO.setSupplierNumber(odlFood.getSupplierNumber());
                    }
                    //交换好后进行数据库操作
                    try{
                       foodInventory.updateFood(foodDO);
                        JOptionPane.showMessageDialog(UpdateInventoryPanel.this, "更新成功 " , "错误", JOptionPane.INFORMATION_MESSAGE);
                    }catch (RuntimeException err){
                        err.printStackTrace();
                        JOptionPane.showMessageDialog(UpdateInventoryPanel.this, "查询失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(UpdateInventoryPanel.this, "没有此编号", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }catch (RuntimeException err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(UpdateInventoryPanel.this, "查询失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }

        });
        add(updateButton, gbc);
        // 返回按钮
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JButton backButton = new JButton("返回查询界面");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "QueryInventoryPanel"));
        add(backButton, gbc);
        //确保勾上以后才能修改
        addCheckboxListeners();
    }

    //勾选框监视器
    private void addCheckboxListeners() {
        // 监听复选框状态改变事件
        foodNumberCheckBox.addActionListener(e -> foodNumberField.setEnabled(foodNumberCheckBox.isSelected()));
        foodNameCheckBox.addActionListener(e -> foodNameField.setEnabled(foodNameCheckBox.isSelected()));
        foodCategoryCheckBox.addActionListener(e -> foodCategoryField.setEnabled(foodCategoryCheckBox.isSelected()));
        foodPriceCheckBox.addActionListener(e -> foodPriceField.setEnabled(foodPriceCheckBox.isSelected()));
        stockQuantityCheckBox.addActionListener(e -> stockQuantityField.setEnabled(stockQuantityCheckBox.isSelected()));
        supplierNumberCheckBox.addActionListener(e -> supplierNumberField.setEnabled(supplierNumberCheckBox.isSelected()));
    }
}
