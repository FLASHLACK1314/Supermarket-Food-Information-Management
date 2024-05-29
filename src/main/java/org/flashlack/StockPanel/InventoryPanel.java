package org.flashlack.StockPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 库存管理大页面
 *
 * @author FLASHLACK
 */
public class InventoryPanel extends JPanel {
    public InventoryPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        //标签设置
        JLabel inventoryLabel = new JLabel("库存界面", SwingConstants.CENTER);
        add(inventoryLabel, BorderLayout.NORTH);
        // 添加按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton addStockButton = new JButton("增加库存");
        JButton queryStockButton = new JButton("查询库存");
        JButton updateStockButton = new JButton("更新库存");
        JButton deleteStockButton = new JButton("删除库存");
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
        //创建增加库存监听器
        addStockButton.addActionListener(e -> cardLayout.show(mainPanel, "AddInventory"));
        //创建查询库存监听器
        queryStockButton.addActionListener(e -> cardLayout.show(mainPanel, "QueryInventory"));
        //创建更新库存监听器
        updateStockButton.addActionListener(e -> cardLayout.show(mainPanel, "UpdateInventory"));
        //创建删除库存监听器
        deleteStockButton.addActionListener(e -> cardLayout.show(mainPanel, "DeleteInventory"));
        // 为返回按钮添加动作监听器，返回主菜单
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    }
}
