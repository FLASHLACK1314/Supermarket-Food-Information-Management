package org.flashlack.supplierPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.SupplierDO;
import org.flashlack.mappers.impl.SupplierImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 更新库存类
 *
 * @author FLAHSLACK
 */
public class UpdateSupplierPanel extends JPanel {
    private final JCheckBox supplierNumberCheckBox;
    private final JTextField supplierNumberTextField;
    private final JCheckBox supplierNameCheckBox;
    private final JTextField supplierNameTextField;
    private final JCheckBox supplierPhoneCheckBox;
    private final JTextField supplierPhoneTextField;

    public UpdateSupplierPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 1, 5, 1);

        supplierNumberCheckBox = new JCheckBox();
        supplierNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, supplierNumberCheckBox, "供应商编号", 0);

        supplierNameCheckBox = new JCheckBox();
        supplierNameTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, supplierNameCheckBox, "新供应商名称", 1);

        supplierPhoneCheckBox = new JCheckBox();
        supplierPhoneTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, supplierPhoneCheckBox, "新供应商联系方式", 2);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton updateButton = new JButton("更新");
        //更新监视器
        updateButton.addActionListener(e -> {
            SupplierDO supplierDO = new SupplierDO();
            SupplierImpl supplier = new SupplierImpl();
            //首先获取值
            if (supplierNumberCheckBox.isSelected()) {
                supplierDO.setSupplierNumber(Integer.valueOf(supplierNumberTextField.getText()));
            }
            if (supplierNameCheckBox.isSelected()) {
                supplierDO.setSupplierName(supplierNameTextField.getText());
            }
            if (supplierPhoneCheckBox.isSelected()) {
                supplierDO.setSupplierPhone(supplierPhoneTextField.getText());
            }
            //进行查询报错
            try{
                SupplierDO odlSupplier = supplier.selectSupplier(supplierDO);
                if (odlSupplier!= null){
                    //进行值的交换
                    if (supplierDO.getSupplierName() == null){
                        supplierDO.setSupplierName(odlSupplier.getSupplierName());
                    }
                    if (supplierDO.getSupplierPhone() == null){
                        supplierDO.setSupplierPhone(odlSupplier.getSupplierPhone());
                    }
                    //进行修改
                    try {
                        supplier.updateSupplier(supplierDO);
                        JOptionPane.showMessageDialog(UpdateSupplierPanel.this, "更新成功 " , "错误", JOptionPane.INFORMATION_MESSAGE);
                    }catch (RuntimeException err){
                        err.printStackTrace();
                        JOptionPane.showMessageDialog(UpdateSupplierPanel.this, "更新失败 " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(UpdateSupplierPanel.this, "没有此编号 " , "错误", JOptionPane.ERROR_MESSAGE);
                }
            }catch (RuntimeException err){
                err.printStackTrace();
                JOptionPane.showMessageDialog(UpdateSupplierPanel.this, "查询失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(updateButton, gbc);
        // 返回按钮
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton backUpdateButton = new JButton("返回查询界面");
        backUpdateButton.addActionListener(e -> cardLayout.show(mainPanel, "QueryInventory"));
        add(backUpdateButton, gbc);

        // 添加清除数据按钮
        gbc.gridy = 5;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            supplierNumberTextField.setText("");
            supplierNameTextField.setText("");
            supplierPhoneTextField.setText("");
        });
        add(clearButton, gbc);

        // 添加返回主菜单按钮
        gbc.gridy = 6;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);

        //勾选后才可以修改
        addCheckboxListeners();
    }
    //勾选框监视器
    private void addCheckboxListeners() {
        // 监听复选框状态改变事件
        supplierNumberCheckBox.addActionListener(e -> supplierNumberTextField.setEnabled(supplierNumberCheckBox.isSelected()));
        supplierNameCheckBox.addActionListener(e -> supplierNameTextField.setEnabled(supplierNameCheckBox.isSelected()));
        supplierPhoneCheckBox.addActionListener(e -> supplierPhoneTextField.setEnabled(supplierPhoneCheckBox.isSelected()));
    }

}
