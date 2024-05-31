package org.flashlack;

import org.flashlack.salesPanel.*;
import org.flashlack.staffPanel.*;
import org.flashlack.stockPanel.*;
import org.flashlack.entity.UserDO;
import org.flashlack.mappers.impl.UserImpl;
import org.flashlack.supplierPanel.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author FLASHLACK
 */
public class Main {
    public static void main(String[] args) {
        // 创建窗口名称
        JFrame frame = new JFrame("超市食品信息管理系统");
        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        // 设置初始化屏幕尺寸
        frame.setSize(screenWidth / 3, screenHeight / 2);
        // 将屏幕初始化放在中间
        frame.setLocationRelativeTo(null);
        // 设置关闭监听器(点击x号进程也随之结束)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 创建卡片布局
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        // 创建主面板
        @SuppressWarnings("unused") JPanel menuPanel = createMenuPanel(cardLayout, mainPanel);
        // 创建库存管理面板
        InventoryPanel inventoryPanel = new InventoryPanel(cardLayout, mainPanel);
        mainPanel.add(inventoryPanel, "Inventory");
        //创建更新库存面板
        UpdateInventoryPanel updateInventoryPanel = new UpdateInventoryPanel(cardLayout, mainPanel);
        mainPanel.add(updateInventoryPanel, "UpdateInventory");
        // 创建添加库存类
        AddInventoryPanel addInventoryPanel = new AddInventoryPanel(cardLayout, mainPanel);
        mainPanel.add(addInventoryPanel, "AddInventory");
        //创建查询库存类
        QueryInventory queryInventory = new QueryInventory(cardLayout, mainPanel);
        mainPanel.add(queryInventory, "QueryInventory");
        //创建删除库存类
        DeleteInventoryPanel deleteInventoryPanel = new DeleteInventoryPanel(cardLayout, mainPanel);
        mainPanel.add(deleteInventoryPanel, "DeleteInventory");
        //创建销售管理面板
        SalesTotalPanel salesTotalPanel = new SalesTotalPanel(cardLayout, mainPanel);
        mainPanel.add(salesTotalPanel, "SalesTotal");
        //创建添加销售记录面板
        AddSalesPanel addSalesPanel = new AddSalesPanel(cardLayout, mainPanel);
        mainPanel.add(addSalesPanel, "AddSales");
        //创建删除销售类
        DeleteSalesPanel deleteSalesPanel = new DeleteSalesPanel(cardLayout, mainPanel);
        mainPanel.add(deleteSalesPanel, "DeleteSales");
        //创建更新销售类
        UpdateSalesPanel updateSalesPanel = new UpdateSalesPanel(cardLayout, mainPanel);
        mainPanel.add(updateSalesPanel, "UpdateSales");
        //创建查询销售类
        QuerySalesPanel querySalesPanel = new QuerySalesPanel(cardLayout, mainPanel);
        mainPanel.add(querySalesPanel, "QuerySales");
        //创建管理供应商面板
        SupplierTotalPanel supplierTotalPanel = new SupplierTotalPanel(cardLayout, mainPanel);
        mainPanel.add(supplierTotalPanel, "SupplierTotal");
        //创建添加供应商类
        AddSupplierPanel addSupplierPanel = new AddSupplierPanel(cardLayout, mainPanel);
        mainPanel.add(addSupplierPanel, "AddSupplier");
        //创建删除供应商类
        DeleteSupplierPanel deleteSupplierPanel = new DeleteSupplierPanel(cardLayout, mainPanel);
        mainPanel.add(deleteSupplierPanel, "DeleteSupplier");
        //创建更新供应商类
        UpdateSupplierPanel updateSupplierPanel = new UpdateSupplierPanel(cardLayout, mainPanel);
        mainPanel.add(updateSupplierPanel, "UpdateSupplier");
        //创建查询供应商类
        QuerySupplierPanel querySupplierPanel = new QuerySupplierPanel(cardLayout, mainPanel);
        mainPanel.add(querySupplierPanel, "QuerySupplier");
        //创建员工面板
        StaffTotalPanel staffTotalPanel = new StaffTotalPanel(cardLayout, mainPanel);
        mainPanel.add(staffTotalPanel, "StaffTotal");
        //创建添加员工类
        AddStaffPanel addStaffPanel = new AddStaffPanel(cardLayout, mainPanel);
        mainPanel.add(addStaffPanel, "AddStaff");
        //创建删除员工类
        DeleteStaffPanel deleteStaffPanel = new DeleteStaffPanel(cardLayout, mainPanel);
        mainPanel.add(deleteStaffPanel, "DeleteStaff");
        //创建更新员工类
        UpdateStaffPanel updateStaffPanel = new UpdateStaffPanel(cardLayout, mainPanel);
        mainPanel.add(updateStaffPanel, "UpdateStaff");
        //创建查询员工类
        QueryStaffPanel queryStaffPanel = new QueryStaffPanel(cardLayout, mainPanel);
        mainPanel.add(queryStaffPanel, "QueryStaff");
        //创建关于我们类
        AboutUsPanel aboutUsPanel = new AboutUsPanel(cardLayout, mainPanel);
        mainPanel.add(aboutUsPanel, "AboutUs");
        // 创建并添加帮助面板
        HelpPanel helpPanel = new HelpPanel(cardLayout, mainPanel);
        mainPanel.add(helpPanel, "Help");
        // 创建登录面板
        JPanel loginPanel = createLoginPanel(cardLayout, mainPanel);
        // 添加登录面板到卡片布局
        mainPanel.add(loginPanel, "Login");
        // 设置初始显示的面板为登录面板
        cardLayout.show(mainPanel, "Login");
        // 将主面板添加到框架
        frame.add(mainPanel);
        // 展示屏幕
        frame.setVisible(true);
    }

    /**
     * 创建主菜单的方法
     *
     * @param cardLayout ——布局
     * @param mainPanel  —— 主页
     * @return menuPanel
     */
    private static JPanel createMenuPanel(CardLayout cardLayout, JPanel mainPanel) {
        JPanel menuPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("超市食品信息管理系统", SwingConstants.CENTER);
        menuPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 10, 10));
        JButton button1 = new JButton("库存管理");
        JButton button2 = new JButton("销售管理");
        JButton button3 = new JButton("供应商管理");
        JButton button4 = new JButton("员工管理");
        JButton button5 = new JButton("清空数据库");
        JButton button6 = new JButton("关于我们");
        JButton button7 = new JButton("帮助");
        JButton button8 = new JButton("退出");
        // 添加按钮
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
        // 为库存管理按钮添加动作监听器，切换到库存管理面板
        button1.addActionListener(e -> cardLayout.show(mainPanel, "Inventory"));
        //为销售管理按钮添加监听器
        button2.addActionListener(e -> cardLayout.show(mainPanel, "SalesTotal"));
        //为供应商管理添加监听器，切换到供应商管理面板
        button3.addActionListener(e -> cardLayout.show(mainPanel, "SupplierTotal"));
        //为员工管理添加监听器
        button4.addActionListener(e -> cardLayout.show(mainPanel, "StaffTotal"));
        //为关于我们创建监听器
        button6.addActionListener(e -> cardLayout.show(mainPanel, "AboutUs"));
        // 为帮助按钮添加动作监听器，切换到帮助面板
        button7.addActionListener(e -> cardLayout.show(mainPanel, "Help"));
        // 为退出按钮添加动作监听器，退出程序
        button8.addActionListener(e -> System.exit(0));
        return menuPanel;
    }

    /**
     * 登录面板
     *
     * @param cardLayout —— 布局
     * @param mainPanel  —— 主页
     * @return loginPanel
     */
    private static JPanel createLoginPanel(CardLayout cardLayout, JPanel mainPanel) {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("用户名:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("密码:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("登录");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        // 点击登录按钮
        loginButton.addActionListener(e -> {
            UserDO userDO = new UserDO();
            userDO.setUserId(usernameField.getText())
                    .setPassword(new String(passwordField.getPassword()));
            UserImpl user = new UserImpl();
            try {
                // 登录
                UserDO loginSuccess = user.loginSelect(userDO);
                if (loginSuccess != null) {
                    if (loginSuccess.getPassword().equals(new String(passwordField.getPassword()))) {
                        cardLayout.show(mainPanel, "Menu");
                    }else {
                        // 登录失败，显示错误信息
                        JOptionPane.showMessageDialog(loginPanel, "用户名或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // 登录失败，显示错误信息
                    JOptionPane.showMessageDialog(loginPanel, "用户名不存在", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            } catch (RuntimeException err) {
                err.printStackTrace();
                // 显示系统错误信息
                JOptionPane.showMessageDialog(loginPanel, "系统错误，请稍后再试", "登录失败", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginPanel.add(loginButton, gbc);
        return loginPanel;
    }
}
