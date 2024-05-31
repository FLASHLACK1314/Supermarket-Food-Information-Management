package org.flashlack.stockPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.FoodDO;
import org.flashlack.entity.SupplierDO;
import org.flashlack.mappers.impl.FoodInventoryImpl;
import org.flashlack.mappers.impl.SupplierImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 添加库存类
 *
 * @author FLASHLACK
 */
public class AddInventoryPanel extends JPanel {
    private final JTextField foodNumberField;
    private final JTextField foodNameField;
    private final JTextField foodCategoryField;
    private final JTextField foodPriceField;
    private final JTextField stockQuantityField;
    private final JTextField supplierNumberField;


    public AddInventoryPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        foodNumberField = UIUtils.addLabeledTextField(this, gbc, "食品编号:", 0);
        foodNameField = UIUtils.addLabeledTextField(this, gbc, "食品名称:", 1);
        foodCategoryField = UIUtils.addLabeledTextField(this, gbc, "食品类别:", 2);
        foodPriceField = UIUtils.addLabeledTextField(this, gbc, "食品进价:", 3);
        stockQuantityField = UIUtils.addLabeledTextField(this, gbc, "库存数量:", 4);
        supplierNumberField = UIUtils.addLabeledTextField(this, gbc, "供应商名称:", 5);

        // 增加字体宽度
        Font font = new Font(null, Font.PLAIN, 15);
        UIUtils.setFont(font, foodNumberField, foodNameField, foodCategoryField, foodPriceField, stockQuantityField, supplierNumberField);
        // 添加完成按钮
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton finishButton = new JButton("添加");
        //监听添加按钮
        finishButton.addActionListener(e -> {
            // 获取文本框中的值并进行处理
            FoodDO foodDO = new FoodDO();
            foodDO.setFoodNumber(Integer.valueOf(foodNumberField.getText()))
                    .setFoodName(foodNameField.getText())
                    .setFoodCategory(foodCategoryField.getText())
                    .setFoodPrice(foodPriceField.getText())
                    .setFoodPrice(foodPriceField.getText())
                    .setStockQuantity(stockQuantityField.getText())
                    .setSupplierNumber(Integer.valueOf(supplierNumberField.getText()));
            FoodInventoryImpl foodInventory = new FoodInventoryImpl();
            SupplierDO supplierDO = new SupplierDO();
            SupplierImpl supplier = new SupplierImpl();
            supplierDO.setSupplierNumber(foodDO.getSupplierNumber());
            try {
                if (supplier.selectSupplier(supplierDO)!=null) {
                    try {
                        foodInventory.foodInventoryInsert(foodDO);
                        // 添加成功，显示成功消息
                        JOptionPane.showMessageDialog(AddInventoryPanel.this, "添加成功", "消息", JOptionPane.INFORMATION_MESSAGE);
                    } catch (RuntimeException err) {
                        err.printStackTrace();
                        // 添加失败，显示错误消息
                        JOptionPane.showMessageDialog(AddInventoryPanel.this, "添加失败:", "失败", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(AddInventoryPanel.this, "供应商表中没有此供应商，请先添加供应商", "消息", JOptionPane.ERROR_MESSAGE);
                }
            }catch (RuntimeException err){
                JOptionPane.showMessageDialog(AddInventoryPanel.this, "系统内部错误"
                        + err.getMessage(), "失败", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(finishButton, gbc);
        // 添加清除数据按钮
        gbc.gridy = 7;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            foodNumberField.setText("");
            foodNameField.setText("");
            foodCategoryField.setText("");
            foodPriceField.setText("");
            stockQuantityField.setText("");
            supplierNumberField.setText("");
        });
        add(clearButton, gbc);
        // 添加返回主菜单按钮
        gbc.gridy = 8;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
    }
}
