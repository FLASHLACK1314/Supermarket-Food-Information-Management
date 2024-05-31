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
 * 增加销售记录类
 *
 * @author FALSHLACK
 */
public class AddSalesPanel extends JPanel {
    private final JTextField foodSalesNumberField;
    private final JTextField foodNumberField;
    private final JTextField salesQuantityField;
    private final JTextField salesPriceField;
    private final JTextField staffNumberField;

    public AddSalesPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        foodSalesNumberField = UIUtils.addLabeledTextField(this, gbc, "销售编号", 0);
        foodNumberField = UIUtils.addLabeledTextField(this, gbc, "食品编号", 1);
        salesQuantityField = UIUtils.addLabeledTextField(this, gbc, "销售数量", 2);
        salesPriceField = UIUtils.addLabeledTextField(this, gbc, "销售价格", 3);
        staffNumberField = UIUtils.addLabeledTextField(this, gbc, "员工编号", 4);
        // 增加字体宽度
        Font font = new Font(null, Font.PLAIN, 15);
        UIUtils.setFont(font, foodSalesNumberField, foodNumberField, salesQuantityField, salesPriceField, staffNumberField);
        // 添加完成按钮
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton finishButton = new JButton("添加");
        // 监听添加按钮
        finishButton.addActionListener(e -> {
            // 获取文本框中的值并进行处理
            SalesDO salesDO = new SalesDO();
            FoodDO foodDO = new FoodDO();
            StaffDO staffDO = new StaffDO();
            salesDO.setFoodSalesNumber(Integer.valueOf(foodSalesNumberField.getText()));
            salesDO.setFoodNumber(Integer.valueOf(foodNumberField.getText()));
            salesDO.setSalesQuantity(salesQuantityField.getText());
            salesDO.setSalesPrice(salesPriceField.getText());
            salesDO.setStaffNumber(Integer.valueOf(staffNumberField.getText()));
            SalesImpl sales = new SalesImpl();
            FoodInventoryImpl foodInventory = new FoodInventoryImpl();
            StaffImpl staff = new StaffImpl();
            //首先检查食品编号是否存在
            foodDO.setFoodNumber(salesDO.getFoodNumber());
            System.out.println(foodDO.getFoodNumber());
            try {
                if (!foodInventory.foodInventoryFind(foodDO).isEmpty()) {
                    //食品编号存在
                    //检查员工编号是否存在
                    staffDO.setStaffNumber(salesDO.getStaffNumber());
                    if (staff.selectStaffByNumber(staffDO)!=null) {
                        //员工编号存在
                        try {
                            //存入食品销售记录
                            sales.insertSales(salesDO);
                            JOptionPane.showMessageDialog(AddSalesPanel.this, "添加成功", "消息", JOptionPane.INFORMATION_MESSAGE);
                        } catch (RuntimeException err) {
                            err.printStackTrace();
                            JOptionPane.showMessageDialog(AddSalesPanel.this, "添加失败: " + err.getMessage(), "消息", JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                        //员工编号不存在
                        JOptionPane.showMessageDialog(AddSalesPanel.this, "员工编号不存在 ", "消息", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    //食品编号不存在
                    JOptionPane.showMessageDialog(AddSalesPanel.this, "食品编号不存在 ", "消息", JOptionPane.ERROR_MESSAGE);
                }
            }catch (RuntimeException err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(AddSalesPanel.this, "系统内部错误" + err.getMessage(), "消息", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(finishButton, gbc);
        // 添加清除数据按钮
        gbc.gridy = 6;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            foodSalesNumberField.setText("");
            foodNumberField.setText("");
            salesQuantityField.setText("");
            salesPriceField.setText("");
            staffNumberField.setText("");
        });
        add(clearButton, gbc);
        // 添加返回主菜单按钮
        gbc.gridy = 7;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
    }
}
