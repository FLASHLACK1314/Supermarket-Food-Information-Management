package org.flashlack.StockPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.FoodDO;
import org.flashlack.mappers.impl.FoodInventoryImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Function;

/**
 * @author FLASHLACK
 */
public class QueryInventory extends JPanel {
    private final JCheckBox foodNumberCheckBox;
    private final JTextField foodNumberField;
    private final JCheckBox foodNameCheckBox;
    private final JTextField foodNameField;
    private final JCheckBox foodCategoryCheckBox;
    private final JTextField foodCategoryField;
    private final JCheckBox supplierNameCheckBox;
    private final JTextField supplierNameField;
    private final JTextArea resultArea;

    public QueryInventory(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // 设置复选框和文本框
        foodNumberCheckBox = new JCheckBox();
        foodNumberField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodNumberCheckBox, "食品编号:", 0);

        foodNameCheckBox = new JCheckBox();
        foodNameField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodNameCheckBox, "食品名称:", 1);

        foodCategoryCheckBox = new JCheckBox();
        foodCategoryField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodCategoryCheckBox, "食品类别:", 2);

        supplierNameCheckBox = new JCheckBox();
        supplierNameField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, supplierNameCheckBox, "供应商名称:", 3);

        // 增加字体宽度
        Font font = new Font(null, Font.PLAIN, 15);
        UIUtils.setFont(font, foodNumberField, foodNameField, foodCategoryField, supplierNameField);

        // 添加查询按钮
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton searchButton = new JButton("查询");
        searchButton.addActionListener(e -> {
            FoodInventoryImpl foodInventory = new FoodInventoryImpl();
            // 获取选中的复选框对应的文本框值并进行处理
            FoodDO foodDO = new FoodDO();
            if (foodNumberCheckBox.isSelected()) {
                foodDO.setFoodNumber(foodNumberField.getText());
                performQuery(foodInventory::foodInventoryFind, foodDO);
            } else if (foodNameCheckBox.isSelected()) {
                foodDO.setFoodName(foodNameField.getText());
                performQuery(foodInventory::foodInventoryFindByName, foodDO);
            } else if (foodCategoryCheckBox.isSelected()) {
                foodDO.setFoodCategory(foodCategoryField.getText());
                performQuery(foodInventory::foodInventoryFindByCategory, foodDO);
            } else if (supplierNameCheckBox.isSelected()) {
                foodDO.setSupplierName(supplierNameField.getText());
                performQuery(foodInventory::foodInventoryFindBySupplierName, foodDO);
            }
        });
        add(searchButton, gbc);
        // 添加查询结果显示区域
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, gbc);

        // 添加返回主菜单按钮
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);

        // 添加复选框的监听器，确保每次只能选中一个复选框
        addCheckboxListeners();
    }
    private void addCheckboxListeners() {
        foodNumberCheckBox.addActionListener(e -> toggleTextFields(foodNumberCheckBox));
        foodNameCheckBox.addActionListener(e -> toggleTextFields(foodNameCheckBox));
        foodCategoryCheckBox.addActionListener(e -> toggleTextFields(foodCategoryCheckBox));
        supplierNameCheckBox.addActionListener(e -> toggleTextFields(supplierNameCheckBox));
    }

    private void toggleTextFields(JCheckBox selectedCheckBox) {
        JCheckBox[] checkboxes = {foodNumberCheckBox, foodNameCheckBox, foodCategoryCheckBox, supplierNameCheckBox};
        JTextField[] fields = {foodNumberField, foodNameField, foodCategoryField, supplierNameField};
        for (int i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i] != selectedCheckBox) {
                checkboxes[i].setSelected(false);
                fields[i].setEnabled(false);
                fields[i].setText("");
            } else {
                fields[i].setEnabled(selectedCheckBox.isSelected());
            }
        }
    }

    //查询通用方法返回结果集
    private void performQuery(Function<FoodDO, List<FoodDO>> queryFunction, FoodDO foodDO) {
        try {
            List<FoodDO> results = queryFunction.apply(foodDO);
            if (!results.isEmpty()) {
                StringBuilder resultText = new StringBuilder();
                for (FoodDO result : results) {
                    resultText.append(result.toChineseString()).append("\n\n");
                }
                resultArea.setText(resultText.toString());
            } else {
                JOptionPane.showMessageDialog(QueryInventory.this, "查询结果为空", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException err) {
            err.printStackTrace();
            JOptionPane.showMessageDialog(QueryInventory.this, "查询失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
