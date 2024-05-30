package org.flashlack.supplierPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author FLASHLACK
 */
public class SupplierTotalPanel extends JPanel {
    public SupplierTotalPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        //标签设置
        JLabel inventoryLabel = new JLabel("供应商管理界面", SwingConstants.CENTER);
        add(inventoryLabel, BorderLayout.NORTH);
        // 添加按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton addStockButton = new JButton("增加供应商");
        JButton queryStockButton = new JButton("查询供应商");
        JButton updateStockButton = new JButton("更新供应商");
        JButton deleteStockButton = new JButton("删除供应商");
        JButton backButton = new JButton("返回主菜单");
        buttonPanel.add(addStockButton);
        buttonPanel.add(queryStockButton);
        buttonPanel.add(updateStockButton);
        buttonPanel.add(deleteStockButton);
        buttonPanel.add(backButton);
        // 创建一个容器面板，并设置边距
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
        containerPanel.add(buttonPanel, BorderLayout.CENTER);
        //将容器面板投入当前面板中
        add(containerPanel, BorderLayout.CENTER);
        //创建增加增加供应商监听器
        addStockButton.addActionListener(e -> cardLayout.show(mainPanel, "AddSupplier"));
        //创建查询供应商监听器
        queryStockButton.addActionListener(e -> cardLayout.show(mainPanel, "QuerySupplier"));
        //创建更新供应商监听器
        updateStockButton.addActionListener(e -> cardLayout.show(mainPanel, "UpdateSupplier"));
        //创建删除供应商监听器
        deleteStockButton.addActionListener(e -> cardLayout.show(mainPanel, "DeleteSupplier"));
        // 为返回按钮添加动作监听器，返回主菜单
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    }
}

