package org.flashlack.salesPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.SalesDO;
import org.flashlack.mappers.impl.SalesImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 删除销售记录界面
 *
 * @author FALSHLACK
 */
public class DeleteSalesPanel extends JPanel {
    private final JTextField foodSalesNumberField;

    public DeleteSalesPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        foodSalesNumberField = UIUtils.addLabeledTextField(this, gbc, "销售编号:", 0);

        // 确认删除按钮
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = new JButton("确认删除");
        deleteButton.addActionListener(e -> {
            SalesDO salesDO = new SalesDO();
            SalesImpl sales = new SalesImpl();

            // 获取
            salesDO.setFoodSalesNumber(Integer.valueOf(foodSalesNumberField.getText()));

            if (sales.selectSalesByNumber(salesDO) != null) {
                // 如果销售记录存在
                try {
                    sales.deleteSales(salesDO);
                    JOptionPane.showMessageDialog(DeleteSalesPanel.this, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                } catch (RuntimeException err) {
                    JOptionPane.showMessageDialog(DeleteSalesPanel.this, "删除失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(DeleteSalesPanel.this, "销售编号不存在", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(deleteButton, gbc);

        // 清除按钮
        gbc.gridx = 2;
        JButton clearButton = new JButton("清除");
        clearButton.addActionListener(e -> foodSalesNumberField.setText(""));
        add(clearButton, gbc);

        // 返回查询界面按钮
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton backUpdateButton = new JButton("返回查询界面");
        backUpdateButton.addActionListener(e -> cardLayout.show(mainPanel, "QuerySales"));
        add(backUpdateButton, gbc);

        // 返回菜单按钮
        gbc.gridx = 2;
        JButton backButton = new JButton("返回菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
    }
}
