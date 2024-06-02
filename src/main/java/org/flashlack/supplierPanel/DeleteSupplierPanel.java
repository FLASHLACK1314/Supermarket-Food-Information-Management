package org.flashlack.supplierPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.FoodDO;
import org.flashlack.entity.SupplierDO;
import org.flashlack.mappers.impl.FoodInventoryImpl;
import org.flashlack.mappers.impl.SupplierImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 删除供应商类
 *
 * @author FLASHLACK
 */
public class DeleteSupplierPanel extends JPanel {
    private final JTextField supplierNumberField;

    public DeleteSupplierPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        supplierNumberField = UIUtils.addLabeledTextField(this, gbc, "供应商编号:", 0);
        // 确认删除按钮
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = new JButton("确认删除");
        deleteButton.addActionListener(e -> {
            SupplierDO supplierDO = new SupplierDO();
            FoodDO foodDO = new FoodDO();
            SupplierImpl supplier = new SupplierImpl();
            FoodInventoryImpl foodInventory = new FoodInventoryImpl();
            //首先去搜索是否存在这个编号
            supplierDO.setSupplierNumber(Integer.valueOf(supplierNumberField.getText()));
            foodDO.setSupplierNumber(Integer.valueOf(supplierNumberField.getText()));
            if (supplier.selectSupplier(supplierDO) != null) {
                //是否存在外键
                try {
                    if (foodInventory.foodInventoryFindBySupplierNumber(foodDO).isEmpty()) {
                        try {
                            supplier.deleteSupplier(supplierDO);
                            JOptionPane.showMessageDialog(DeleteSupplierPanel.this, "删除成功 ", "成功", JOptionPane.INFORMATION_MESSAGE);
                        }catch (RuntimeException err){
                            JOptionPane.showMessageDialog(DeleteSupplierPanel.this, "系统删除失败" + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(DeleteSupplierPanel.this, "库存表中存在供应商编号不可删除", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (RuntimeException err) {
                    err.printStackTrace();
                    JOptionPane.showMessageDialog(DeleteSupplierPanel.this, "系统内部错误" + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(DeleteSupplierPanel.this, "编号不存在 ", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(deleteButton, gbc);
        // 清除按钮
        gbc.gridx = 2;
        JButton clearButton = new JButton("清除");
        clearButton.addActionListener(e -> supplierNumberField.setText(""));
        add(clearButton, gbc);

        // 返回查询界面按钮
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton backUpdateButton = new JButton("返回查询界面");
        backUpdateButton.addActionListener(e -> cardLayout.show(mainPanel, "QuerySupplier"));
        add(backUpdateButton, gbc);

        // 返回菜单按钮
        gbc.gridx = 2;
        JButton backButton = new JButton("返回菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
    }
}
