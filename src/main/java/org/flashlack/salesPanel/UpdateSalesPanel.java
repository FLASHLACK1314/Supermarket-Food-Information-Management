package org.flashlack.salesPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.FoodDO;
import org.flashlack.entity.SalesDO;
import org.flashlack.entity.StaffDO;
import org.flashlack.mappers.impl.FoodInventoryImpl;
import org.flashlack.mappers.impl.SalesImpl;
import org.flashlack.mappers.impl.StaffImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 更新销售记录
 *
 * @author 26473
 */
public class UpdateSalesPanel extends JPanel {
    private final JCheckBox foodSalesNumberCheckBox;
    private final JTextField foodSalesNumberTextField;
    private final JCheckBox foodNumberCheckBox;
    private final JTextField foodNumberTextField;
    private final JCheckBox salesQuantityCheckBox;
    private final JTextField salesQuantityTextField;
    private final JCheckBox salesPriceCheckBox;
    private final JTextField salesPriceTextField;
    private final JCheckBox staffNumberCheckBox;
    private final JTextField staffNumberTextField;

    public UpdateSalesPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 1, 5, 1);

        foodSalesNumberCheckBox = new JCheckBox();
        foodSalesNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodSalesNumberCheckBox, "销售编号", 0);

        foodNumberCheckBox = new JCheckBox();
        foodNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodNumberCheckBox, "新食品编号", 1);

        salesQuantityCheckBox = new JCheckBox();
        salesQuantityTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, salesQuantityCheckBox, "新销售数量", 2);

        salesPriceCheckBox = new JCheckBox();
        salesPriceTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, salesPriceCheckBox, "新销售价格", 3);

        staffNumberCheckBox = new JCheckBox();
        staffNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffNumberCheckBox, "新员工编号", 4);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton updateButton = new JButton("更新");
        // 更新监视器
        updateButton.addActionListener(e -> {
            SalesDO salesDO = new SalesDO();
            SalesImpl sales = new SalesImpl();
            // 首先获取值
            if (foodSalesNumberCheckBox.isSelected()) {
                salesDO.setFoodSalesNumber(Integer.valueOf(foodSalesNumberTextField.getText()));
            }
            if (foodNumberCheckBox.isSelected()) {
                salesDO.setFoodNumber(Integer.valueOf(foodNumberTextField.getText()));
                //检查新的编号是否存在
                FoodInventoryImpl foodInventory = new FoodInventoryImpl();
                FoodDO foodDO = new FoodDO();
                foodDO.setFoodNumber(salesDO.getFoodNumber());
                try {
                    if (foodInventory.getFoodDO(foodDO) == null) {
                        JOptionPane.showMessageDialog(UpdateSalesPanel.this, "新食品编号不存在", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UpdateSalesPanel.this, "查询编号失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (salesQuantityCheckBox.isSelected()) {
                salesDO.setSalesQuantity(salesQuantityTextField.getText());
            }
            if (salesPriceCheckBox.isSelected()) {
                salesDO.setSalesPrice(salesPriceTextField.getText());
            }
            if (staffNumberCheckBox.isSelected()) {
                salesDO.setStaffNumber(Integer.valueOf(staffNumberTextField.getText()));
                StaffDO staffDO = new StaffDO();
                StaffImpl staff = new StaffImpl();
                staffDO.setStaffNumber(salesDO.getStaffNumber());
                try {
                    if (staff.selectStaffByNumber(staffDO) == null) {
                        JOptionPane.showMessageDialog(UpdateSalesPanel.this, "新人员编号不存在", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(UpdateSalesPanel.this, "查询编号失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
            // 查看是否存在
            try {
                SalesDO oldSales = sales.selectSalesByNumber(salesDO);
                if (oldSales != null) {
                    // 进行值的交换
                    if (salesDO.getFoodNumber() == null) {
                        salesDO.setFoodNumber(oldSales.getFoodNumber());
                    }
                    if (salesDO.getSalesQuantity() == null) {
                        salesDO.setSalesQuantity(oldSales.getSalesQuantity());
                    }
                    if (salesDO.getSalesPrice() == null) {
                        salesDO.setSalesPrice(oldSales.getSalesPrice());
                    }
                    if (salesDO.getStaffNumber() == null) {
                        salesDO.setStaffNumber(oldSales.getStaffNumber());
                    }
                    // 进行修改
                    try {
                        sales.updateSales(salesDO);
                        JOptionPane.showMessageDialog(UpdateSalesPanel.this, "更新成功", "信息", JOptionPane.INFORMATION_MESSAGE);
                    } catch (RuntimeException err) {
                        err.printStackTrace();
                        JOptionPane.showMessageDialog(UpdateSalesPanel.this, "更新失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(UpdateSalesPanel.this, "没有此编号", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } catch (RuntimeException err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(UpdateSalesPanel.this, "更新失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(updateButton, gbc);

        // 返回按钮
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton backUpdateButton = new JButton("返回查询界面");
        backUpdateButton.addActionListener(e -> cardLayout.show(mainPanel, "QuerySales"));
        add(backUpdateButton, gbc);

        // 添加清除数据按钮
        gbc.gridy = 7;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            foodSalesNumberTextField.setText("");
            foodNumberTextField.setText("");
            salesQuantityTextField.setText("");
            salesPriceTextField.setText("");
            staffNumberTextField.setText("");
        });
        add(clearButton, gbc);

        // 添加返回主菜单按钮
        gbc.gridy = 8;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);

        // 勾选后才可以修改
        addCheckboxListeners();
    }

    // 勾选框监视器
    private void addCheckboxListeners() {
        // 监听复选框状态改变事件
        foodSalesNumberCheckBox.addActionListener(e -> foodSalesNumberTextField.setEnabled(foodSalesNumberCheckBox.isSelected()));
        foodNumberCheckBox.addActionListener(e -> foodNumberTextField.setEnabled(foodNumberCheckBox.isSelected()));
        salesQuantityCheckBox.addActionListener(e -> salesQuantityTextField.setEnabled(salesQuantityCheckBox.isSelected()));
        salesPriceCheckBox.addActionListener(e -> salesPriceTextField.setEnabled(salesPriceCheckBox.isSelected()));
        staffNumberCheckBox.addActionListener(e -> staffNumberTextField.setEnabled(staffNumberCheckBox.isSelected()));
    }
}
