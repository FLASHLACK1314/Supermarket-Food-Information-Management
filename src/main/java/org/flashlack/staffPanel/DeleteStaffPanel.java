package org.flashlack.staffPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.StaffDO;
import org.flashlack.mappers.impl.StaffImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 删除员工类
 * @author FLASHLAKC
 */
public class DeleteStaffPanel extends JPanel {
    private final JTextField staffNumberField;

    public DeleteStaffPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        staffNumberField = UIUtils.addLabeledTextField(this, gbc, "员工编号:", 0);

        // 确认删除按钮
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = new JButton("确认删除");
        deleteButton.addActionListener(e -> {
            StaffDO staffDO = new StaffDO();
            StaffImpl staff = new StaffImpl();

            // 设置员工编号
            staffDO.setStaffNumber(Integer.valueOf(staffNumberField.getText()));
            try {
                // 检查员工是否存在
                if (staff.selectStaffByNumber(staffDO) != null) {
                    // 尝试删除员工
                    try {
                        staff.deleteStaffByNumber(staffDO);
                        JOptionPane.showMessageDialog(DeleteStaffPanel.this, "删除成功 ", "成功", JOptionPane.INFORMATION_MESSAGE);
                    } catch (RuntimeException err) {
                        JOptionPane.showMessageDialog(DeleteStaffPanel.this, "系统删除失败" + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(DeleteStaffPanel.this, "编号不存在 ", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }catch (RuntimeException err) {
                JOptionPane.showMessageDialog(DeleteStaffPanel.this, "系统错误" + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(deleteButton, gbc);

        // 清除按钮
        gbc.gridx = 2;
        JButton clearButton = new JButton("清除");
        clearButton.addActionListener(e -> staffNumberField.setText(""));
        add(clearButton, gbc);

        // 返回菜单按钮
        gbc.gridy = 3;
        gbc.gridx = 1;
        JButton backButton = new JButton("返回菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);
    }
}