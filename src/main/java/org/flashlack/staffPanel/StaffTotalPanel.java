package org.flashlack.staffPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 员工管理主界面类
 * @author FLASHLACK
 */
public class StaffTotalPanel extends JPanel {
    public StaffTotalPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        //标签设置
        JLabel inventoryLabel = new JLabel("供应商管理界面", SwingConstants.CENTER);
        add(inventoryLabel, BorderLayout.NORTH);
        // 添加按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton addStaffButton = new JButton("增加员工");
        JButton queryStaffButton = new JButton("查询员工");
        JButton updateStaffButton = new JButton("更新员工");
        JButton deleteStaffButton = new JButton("删除员工");
        JButton backButton = new JButton("返回主菜单");
        buttonPanel.add(addStaffButton);
        buttonPanel.add(queryStaffButton);
        buttonPanel.add(updateStaffButton);
        buttonPanel.add(deleteStaffButton);
        buttonPanel.add(backButton);
        // 创建一个容器面板，并设置边距
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
        containerPanel.add(buttonPanel, BorderLayout.CENTER);
        //将容器面板投入当前面板中
        add(containerPanel, BorderLayout.CENTER);
        //创建增加增加供应商监听器
        addStaffButton.addActionListener(e -> cardLayout.show(mainPanel, "AddStaff"));
        //创建查询供应商监听器
        queryStaffButton.addActionListener(e -> cardLayout.show(mainPanel, "QueryStaff"));
        //创建更新供应商监听器
        updateStaffButton.addActionListener(e -> cardLayout.show(mainPanel, "UpdateStaff"));
        //创建删除供应商监听器
        deleteStaffButton.addActionListener(e -> cardLayout.show(mainPanel, "DeleteStaff"));
        // 为返回按钮添加动作监听器，返回主菜单
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    }
}
