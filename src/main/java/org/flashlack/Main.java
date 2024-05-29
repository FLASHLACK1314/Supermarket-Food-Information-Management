package org.flashlack;

import org.flashlack.StockPanel.AddInventoryPanel;
import org.flashlack.StockPanel.InventoryPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author FLASHLACK
 */
public class Main {
    public static void main(String[] args) {
        //创建窗口名称
        JFrame frame = new JFrame("超市食品信息管理系统");
        //获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        //设置初始化屏幕尺寸
        frame.setSize(screenWidth / 3, screenHeight / 2);
        //将屏幕初始化放在中间
        frame.setLocationRelativeTo(null);
        //设置关闭监听器(点击x号进程也随之结束)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 创建卡片布局
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        // 创建主面板
        JPanel menuPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("超市食品信息管理系统", SwingConstants.CENTER);
        menuPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 10, 10));
        JButton button1 = new JButton("库存管理");
        JButton button2 = new JButton("销售管理");
        JButton button3 = new JButton("供应商管理");
        JButton button4 = new JButton("员工管理");
        JButton button5 = new JButton("查看总利润");
        JButton button6 = new JButton("数据库连接");
        JButton button7 = new JButton("帮助");
        JButton button8 = new JButton("退出");
        //添加按钮
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        // 创建一个容器面板，设置边距
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
        containerPanel.add(buttonPanel, BorderLayout.CENTER);
        // 将容器面板(这样子确保有边距)添加到主菜单面板
        menuPanel.add(containerPanel, BorderLayout.CENTER);
        // 添加主菜单面板到卡片布局
        mainPanel.add(menuPanel, "Menu");
        //创建库存管理面板
        InventoryPanel inventoryPanel = new InventoryPanel(cardLayout, mainPanel);
        mainPanel.add(inventoryPanel, "Inventory");
        //创建添加库存类
        AddInventoryPanel addInventoryPanel = new AddInventoryPanel(cardLayout, mainPanel);
        mainPanel.add(addInventoryPanel, "AddInventory");
        // 创建并添加帮助面板
        HelpPanel helpPanel = new HelpPanel(cardLayout, mainPanel);
        mainPanel.add(helpPanel, "Help");
        // 为库存管理按钮添加动作监听器，切换到库存管理面板
        button1.addActionListener(e -> cardLayout.show(mainPanel, "Inventory"));
        // 为帮助按钮添加动作监听器，切换到帮助面板
        button7.addActionListener(e -> cardLayout.show(mainPanel, "Help"));
        // 为退出按钮添加动作监听器，退出程序
        button8.addActionListener(e -> System.exit(0));
        // 设置初始显示的面板
        cardLayout.show(mainPanel, "Menu");
        // 将主面板添加到框架
        frame.add(mainPanel);
        //展示屏幕
        frame.setVisible(true);
    }
}