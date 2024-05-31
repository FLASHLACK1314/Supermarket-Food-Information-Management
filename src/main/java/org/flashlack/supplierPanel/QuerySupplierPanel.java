package org.flashlack.supplierPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.SupplierDO;
import org.flashlack.mappers.impl.SupplierImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Function;

/**
 * 查询供应商类
 *
 * @author FLASHLACK
 */
public class QuerySupplierPanel extends JPanel {
    private final JCheckBox supplierNumberCheckBox;
    private final JTextField supplierNumberTextField;
    private final JCheckBox supplierNameCheckBox;
    private final JTextField supplierNameTextField;
    private final JTextArea resultArea;

    public QuerySupplierPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);

        supplierNumberCheckBox = new JCheckBox();
        supplierNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, supplierNumberCheckBox, "供应商编号", 0);

        supplierNameCheckBox = new JCheckBox();
        supplierNameTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, supplierNameCheckBox, "供应商名称", 1);
        // 增加字体宽度
        Font font = new Font(null, Font.PLAIN, 15);
        UIUtils.setFont(font, supplierNumberTextField, supplierNameTextField);
        // 添加查询按钮
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton searchButton = new JButton("查询");
        searchButton.addActionListener(e -> {
            SupplierImpl supplier = new SupplierImpl();
            // 获取选中的复选框对应的文本框值并进行处理
            SupplierDO supplierDO = new SupplierDO();
            if (supplierNumberCheckBox.isSelected()) {
                supplierDO.setSupplierNumber(Integer.valueOf(supplierNumberTextField.getText()));
                performQuery(supplier::selectSupplierList, supplierDO);
            } else if (supplierNameCheckBox.isSelected()) {
                supplierDO.setSupplierName(supplierNameTextField.getText());
                performQuery(supplier::selectSupplierByName, supplierDO);
            }
        });
        add(searchButton, gbc);
        // 添加跳转到更新界面的按钮
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton updateButton = new JButton("更新供应商(请记住编号)");
        updateButton.addActionListener(e -> cardLayout.show(mainPanel, "UpdateSupplier"));
        add(updateButton, gbc);

        // 添加跳转到删除界面的按钮
        gbc.gridy = 5;
        JButton deleteButton = new JButton("删除供应商(请记住编号)");
        deleteButton.addActionListener(e -> cardLayout.show(mainPanel, "DeleteSupplier"));
        add(deleteButton, gbc);

        // 添加清除数据按钮
        gbc.gridy = 6;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            supplierNumberTextField.setText("");
            supplierNameTextField.setText("");
        });
        add(clearButton, gbc);

        // 添加查询结果显示区域
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, gbc);

        // 添加返回主菜单按钮
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
        //确保勾选一个
        addCheckboxListeners();
    }

    // 勾选框监视器
    private void addCheckboxListeners() {
        supplierNumberCheckBox.addActionListener(e -> toggleTextFields(supplierNumberCheckBox));
        supplierNameCheckBox.addActionListener(e -> toggleTextFields(supplierNameCheckBox));
    }

    // 确保只能填写一个
    private void toggleTextFields(JCheckBox selectedCheckBox) {
        JCheckBox[] checkboxes = {supplierNumberCheckBox, supplierNameCheckBox};
        JTextField[] fields = {supplierNumberTextField, supplierNameTextField};
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
    private void performQuery(Function<SupplierDO, java.util.List<SupplierDO>> queryFunction, SupplierDO supplierDO) {
        try {
            List<SupplierDO> results = queryFunction.apply(supplierDO);
            if (!results.isEmpty()) {
                StringBuilder resultText = new StringBuilder();
                for (SupplierDO result : results) {
                    resultText.append(result.toChineseString()).append("\n\n");
                }
                resultArea.setText(resultText.toString());
            } else {
                JOptionPane.showMessageDialog(QuerySupplierPanel.this, "查询结果为空", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException err) {
            err.printStackTrace();
            JOptionPane.showMessageDialog(QuerySupplierPanel.this, "查询失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }


}
