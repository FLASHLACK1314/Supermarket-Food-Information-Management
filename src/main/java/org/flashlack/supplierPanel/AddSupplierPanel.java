package org.flashlack.supplierPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.SupplierDO;
import org.flashlack.mappers.impl.SupplierImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 添加供应商类
 * @author FLASHLACK
 */
public class AddSupplierPanel extends JPanel {
    private final JTextField supplierNumberField;
    private final JTextField supplierNameField;
    private final JTextField supplierPhoneField;

    public AddSupplierPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        supplierNumberField = UIUtils.addLabeledTextField(this, gbc, "供应商编号", 0);
        supplierNameField = UIUtils.addLabeledTextField(this, gbc, "供应商名称", 1);
        supplierPhoneField = UIUtils.addLabeledTextField(this, gbc, "供应商联系方式", 2);
        // 增加字体宽度
        Font font = new Font(null, Font.PLAIN, 15);
        UIUtils.setFont(font, supplierNumberField, supplierNameField, supplierPhoneField);
        // 添加完成按钮
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton finishButton = new JButton("添加");
        //监听添加按钮
        finishButton.addActionListener(e -> {
            // 获取文本框中的值并进行处理
            SupplierDO supplierDO = new SupplierDO();
            supplierDO.setSupplierNumber(Integer.valueOf(supplierNumberField.getText()));
            supplierDO.setSupplierName(supplierNameField.getText());
            supplierDO.setSupplierPhone(supplierPhoneField.getText());
            SupplierImpl supplier = new SupplierImpl();
            //检查编号
            try{
                if (supplier.selectSupplier(supplierDO)!=null){
                    JOptionPane.showMessageDialog(AddSupplierPanel.this, "编号重复", "消息", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }catch (RuntimeException err){
                JOptionPane.showMessageDialog(AddSupplierPanel.this, "系统内部错误"
                        + err.getMessage(), "失败", JOptionPane.ERROR_MESSAGE);
            }
            try {
                supplier.insertSupplier(supplierDO);
                JOptionPane.showMessageDialog(AddSupplierPanel.this, "添加成功", "消息", JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(AddSupplierPanel.this, "系统内部错误"+err.getMessage(), "消息", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(finishButton, gbc);
        // 添加清除数据按钮
        gbc.gridy = 4;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            supplierNumberField.setText("");
            supplierNameField.setText("");
            supplierPhoneField.setText("");
        });
        add(clearButton, gbc);
        // 添加返回主菜单按钮
        gbc.gridy = 5;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
    }
}
