package org.flashlack.salesPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 销售管理主界面
 *
 * @author FALSHLACK
 */
public class SalesTotalPanel extends JPanel {
    public SalesTotalPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        // 标签设置
        JLabel salesLabel = new JLabel("销售管理界面", SwingConstants.CENTER);
        add(salesLabel, BorderLayout.NORTH);
        // 添加按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton addSalesButton = new JButton("增加销售");
        JButton querySalesButton = new JButton("查询销售");
        JButton updateSalesButton = new JButton("更新销售");
        JButton deleteSalesButton = new JButton("删除销售");
        JButton backButton = new JButton("返回主菜单");
        buttonPanel.add(addSalesButton);
        buttonPanel.add(querySalesButton);
        buttonPanel.add(updateSalesButton);
        buttonPanel.add(deleteSalesButton);
        buttonPanel.add(backButton);
        // 创建一个容器面板，并设置边距
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
        containerPanel.add(buttonPanel, BorderLayout.CENTER);
        // 将容器面板投入当前面板中
        add(containerPanel, BorderLayout.CENTER);
        // 创建增加销售监听器
        addSalesButton.addActionListener(e -> cardLayout.show(mainPanel, "AddSales"));
        // 创建查询销售监听器
        querySalesButton.addActionListener(e -> cardLayout.show(mainPanel, "QuerySales"));
        // 创建更新销售监听器
        updateSalesButton.addActionListener(e -> cardLayout.show(mainPanel, "UpdateSales"));
        // 创建删除销售监听器
        deleteSalesButton.addActionListener(e -> cardLayout.show(mainPanel, "DeleteSales"));
        // 为返回按钮添加动作监听器，返回主菜单
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    }
}

