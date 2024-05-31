package org.flashlack;

import org.flashlack.mappers.impl.FoodInventoryImpl;
import org.flashlack.mappers.impl.SalesImpl;
import org.flashlack.mappers.impl.StaffImpl;
import org.flashlack.mappers.impl.SupplierImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 清空数据库类
 *
 * @author FLASHLACK
 */
public class ClearDatabasePanel extends JPanel {
    public ClearDatabasePanel(CardLayout cardLayout, JPanel mainPanel) {
        FoodInventoryImpl foodInventory = new FoodInventoryImpl();
        SalesImpl sales = new SalesImpl();
        StaffImpl staff = new StaffImpl();
        SupplierImpl supplier = new SupplierImpl();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 清空库存按钮
        JButton clearInventoryButton = new JButton("清空库存");
        clearInventoryButton.addActionListener(e -> {
            try {
                foodInventory.deleteAllFoodInventory();
                JOptionPane.showMessageDialog(ClearDatabasePanel.this, "清空库存表成功", "消息", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(ClearDatabasePanel.this, "清空失败" + ex.getMessage(), "消息", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridy = 1;
        add(clearInventoryButton, gbc);

        // 清空销售按钮
        JButton clearSalesButton = new JButton("清空销售记录");
        clearSalesButton.addActionListener(e -> {
            try {
                sales.deleteAllSales();
                JOptionPane.showMessageDialog(ClearDatabasePanel.this, "清空销售记录表成功", "消息", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(ClearDatabasePanel.this, "清空失败" + ex.getMessage(), "消息", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridy = 2;
        add(clearSalesButton, gbc);

        // 清空供应商按钮
        JButton clearSupplierButton = new JButton("清空供应商");
        clearSupplierButton.addActionListener(e -> {
            try {
                supplier.deleteSupplier();
                JOptionPane.showMessageDialog(ClearDatabasePanel.this, "清空供应商表成功", "消息", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(ClearDatabasePanel.this, "清空失败" + ex.getMessage(), "消息", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridy = 3;
        add(clearSupplierButton, gbc);

        // 清空人员按钮
        JButton clearStaffButton = new JButton("清空员工");
        clearStaffButton.addActionListener(e -> {
            try {
                staff.deleteStaff();
                JOptionPane.showMessageDialog(ClearDatabasePanel.this, "清空员工表成功", "消息", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(ClearDatabasePanel.this, "清空失败" + ex.getMessage(), "消息", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridy = 4;
        add(clearStaffButton, gbc);

        // 返回主菜单按钮
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        gbc.gridy = 5;
        add(backButton, gbc);
    }
}