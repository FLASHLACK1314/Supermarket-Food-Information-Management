package org.flashlack.staffPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.StaffDO;
import org.flashlack.mappers.impl.StaffImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 添加员工类
 * @author FALSHLACK
 */
public class AddStaffPanel extends JPanel {
    private final JTextField staffNumberField;
    private final JTextField staffNameField;
    private final JTextField staffSexField;
    private final JTextField staffPhoneField;

    public AddStaffPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // 创建并添加输入框
        staffNumberField = UIUtils.addLabeledTextField(this, gbc, "员工编号", 0);
        staffNameField = UIUtils.addLabeledTextField(this, gbc, "员工姓名", 1);
        staffSexField = UIUtils.addLabeledTextField(this, gbc, "员工性别", 2);
        staffPhoneField = UIUtils.addLabeledTextField(this, gbc, "员工联系方式", 3);

        // 增加字体宽度
        Font font = new Font(null, Font.PLAIN, 15);
        UIUtils.setFont(font, staffNumberField, staffNameField, staffSexField, staffPhoneField);

        // 添加完成按钮
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton finishButton = new JButton("添加");

        // 监听添加按钮
        finishButton.addActionListener(e -> {
            // 获取文本框中的值并进行处理
            StaffDO staffDO = new StaffDO();
            staffDO.setStaffNumber(Integer.valueOf(staffNumberField.getText()));
            staffDO.setStaffName(staffNameField.getText());
            staffDO.setStaffSex(staffSexField.getText());
            staffDO.setStaffPhone(staffPhoneField.getText());
            StaffImpl staff = new StaffImpl();
            //检查编号
            try{
                if (staff.selectStaffByNumber(staffDO)!=null){
                    JOptionPane.showMessageDialog(AddStaffPanel.this, "编号重复", "消息", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }catch (RuntimeException err){
                JOptionPane.showMessageDialog(AddStaffPanel.this, "系统内部错误"
                        + err.getMessage(), "失败", JOptionPane.ERROR_MESSAGE);
            }
            try {
                staff.insertStaff(staffDO);
                JOptionPane.showMessageDialog(AddStaffPanel.this, "添加成功", "消息", JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(AddStaffPanel.this, "添加失败,可能为编号重复" + err.getMessage(), "消息", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(finishButton, gbc);

        // 添加清除数据按钮
        gbc.gridy = 5;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            staffNumberField.setText("");
            staffNameField.setText("");
            staffSexField.setText("");
            staffPhoneField.setText("");
        });
        add(clearButton, gbc);

        // 添加返回主菜单按钮
        gbc.gridy = 6;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
    }
}
