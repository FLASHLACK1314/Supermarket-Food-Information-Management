package org.flashlack.salesPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.SalesDO;
import org.flashlack.mappers.impl.SalesImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Function;

/**
 * 查询销售界面
 *
 * @author FALSHLACK
 */
public class QuerySalesPanel extends JPanel {
    private final JCheckBox foodSalesNumberCheckBox;
    private final JTextField foodSalesNumberTextField;
    private final JCheckBox foodNumberCheckBox;
    private final JTextField foodNumberTextField;
    private final JCheckBox staffNumberCheckBox;
    private final JTextField staffNumberTextField;
    private final JTextArea resultArea;

    public QuerySalesPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);

        foodSalesNumberCheckBox = new JCheckBox();
        foodSalesNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodSalesNumberCheckBox, "食品销售编号", 0);

        foodNumberCheckBox = new JCheckBox();
        foodNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, foodNumberCheckBox, "食品编号", 1);

        staffNumberCheckBox = new JCheckBox();
        staffNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffNumberCheckBox, "人员编号", 2);

        // 增加字体宽度
        Font font = new Font(null, Font.PLAIN, 15);
        UIUtils.setFont(font, foodSalesNumberTextField, foodNumberTextField, staffNumberTextField);

        // 查询所有
        gbc.gridy = 3;
        JButton searchAllButton = new JButton("查询所有销售记录");
        searchAllButton.addActionListener(e -> {
            try {
                SalesImpl sales = new SalesImpl();
                displayResults(sales.selectAllSales());
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(QuerySalesPanel.this, "查询失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(searchAllButton, gbc);

        // 添加查询按钮
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton searchButton = new JButton("查询");
        searchButton.addActionListener(e -> {
            SalesImpl sales = new SalesImpl();
            // 获取选中的复选框对应的文本框值并进行处理
            SalesDO salesDO = new SalesDO();
            if (foodSalesNumberCheckBox.isSelected()) {
                salesDO.setFoodSalesNumber(Integer.valueOf(foodSalesNumberTextField.getText()));
                performQuery(sales::selectSalesListByNumber, salesDO);
            } else if (foodNumberCheckBox.isSelected()) {
                salesDO.setFoodNumber(Integer.valueOf(foodNumberTextField.getText()));
                performQuery(sales::selectSalesListByFood, salesDO);
            } else if (staffNumberCheckBox.isSelected()) {
                salesDO.setStaffNumber(Integer.valueOf(staffNumberTextField.getText()));
                performQuery(sales::selectSalesListByStaff, salesDO);
            }
        });
        add(searchButton, gbc);

        // 添加跳转到更新界面的按钮
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton updateButton = new JButton("更新销售记录(请记住编号)");
        updateButton.addActionListener(e -> cardLayout.show(mainPanel, "UpdateSales"));
        add(updateButton, gbc);

        // 添加跳转到删除界面的按钮
        gbc.gridy = 6;
        JButton deleteButton = new JButton("删除销售记录(请记住编号)");
        deleteButton.addActionListener(e -> cardLayout.show(mainPanel, "DeleteSales"));
        add(deleteButton, gbc);

        // 添加清除数据按钮
        gbc.gridy = 7;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            foodSalesNumberTextField.setText("");
            foodNumberTextField.setText("");
            staffNumberTextField.setText("");
        });
        add(clearButton, gbc);

        // 添加查询结果显示区域
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, gbc);

        // 添加返回主菜单按钮
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);

        // 确保勾选一个
        addCheckboxListeners();
    }

    // 勾选框监视器
    private void addCheckboxListeners() {
        foodSalesNumberCheckBox.addActionListener(e -> toggleTextFields(foodSalesNumberCheckBox));
        foodNumberCheckBox.addActionListener(e -> toggleTextFields(foodNumberCheckBox));
        staffNumberCheckBox.addActionListener(e -> toggleTextFields(staffNumberCheckBox));
    }

    // 确保只能填写一个
    private void toggleTextFields(JCheckBox selectedCheckBox) {
        JCheckBox[] checkboxes = {foodSalesNumberCheckBox, foodNumberCheckBox, staffNumberCheckBox};
        JTextField[] fields = {foodSalesNumberTextField, foodNumberTextField, staffNumberTextField};
        for (int i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i] != selectedCheckBox) {
                checkboxes[i].setSelected(false);
                fields[i].setEnabled(false);
                fields[i].setText("");
            } else {
                fields[i].setEnabled(selectedCheckBox.isSelected());
            }
        }
    }

    //查询通用方法返回结果集
    private void performQuery(Function<SalesDO, java.util.List<SalesDO>> queryFunction, SalesDO salesDO) {
        try {
            List<SalesDO> results = queryFunction.apply(salesDO);
            if (!results.isEmpty()) {
                StringBuilder resultText = new StringBuilder();
                for (SalesDO result : results) {
                    resultText.append(result.toChineseString()).append("\n\n");
                }
                resultArea.setText(resultText.toString());
            } else {
                JOptionPane.showMessageDialog(QuerySalesPanel.this, "查询结果为空", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException err) {
            err.printStackTrace();
            JOptionPane.showMessageDialog(QuerySalesPanel.this, "查询失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    //查询所有返回结果集
    private void displayResults(List<SalesDO> results) {
        if (!results.isEmpty()) {
            StringBuilder resultText = new StringBuilder();
            for (SalesDO result : results) {
                resultText.append(result.toChineseString()).append("\n\n");
            }
            resultArea.setText(resultText.toString());
        } else {
            JOptionPane.showMessageDialog(QuerySalesPanel.this, "查询结果为空", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}

