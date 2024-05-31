package org.flashlack.staffPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.StaffDO;
import org.flashlack.mappers.impl.StaffImpl;

import javax.swing.*;
import java.awt.*;

/**
 * 更新员工类
 *
 * @author FLASHLACK
 */
public class UpdateStaffPanel extends JPanel {
    private final JCheckBox staffNumberCheckBox;
    private final JTextField staffNumberTextField;
    private final JCheckBox staffNameCheckBox;
    private final JTextField staffNameTextField;
    private final JCheckBox staffSexCheckBox;
    private final JTextField staffSexTextField;
    private final JCheckBox staffPhoneCheckBox;
    private final JTextField staffPhoneTextField;

    public UpdateStaffPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 1, 5, 1);

        staffNumberCheckBox = new JCheckBox();
        staffNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffNumberCheckBox, "员工编号", 0);

        staffNameCheckBox = new JCheckBox();
        staffNameTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffNameCheckBox, "新员工姓名", 1);

        staffSexCheckBox = new JCheckBox();
        staffSexTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffSexCheckBox, "新员工性别", 2);

        staffPhoneCheckBox = new JCheckBox();
        staffPhoneTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffPhoneCheckBox, "新员工联系方式", 3);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton updateButton = new JButton("更新");
        // 更新监视器
        updateButton.addActionListener(e -> {
            StaffDO staffDO = new StaffDO();
            StaffImpl staff = new StaffImpl();
            // 获取值
            if (staffNumberCheckBox.isSelected()) {
                staffDO.setStaffNumber(Integer.valueOf(staffNumberTextField.getText()));
            }
            if (staffNameCheckBox.isSelected()) {
                staffDO.setStaffName(staffNameTextField.getText());
            }
            if (staffSexCheckBox.isSelected()) {
                staffDO.setStaffSex(staffSexTextField.getText());
            }
            if (staffPhoneCheckBox.isSelected()) {
                staffDO.setStaffPhone(staffPhoneTextField.getText());
            }
            // 查询并更新
            try {
                StaffDO oldStaff = staff.selectStaffByNumber(staffDO);
                if (oldStaff != null) {
                    // 交换值
                    if (staffDO.getStaffName() == null) {
                        staffDO.setStaffName(oldStaff.getStaffName());
                    }
                    if (staffDO.getStaffSex() == null) {
                        staffDO.setStaffSex(oldStaff.getStaffSex());
                    }
                    if (staffDO.getStaffPhone() == null) {
                        staffDO.setStaffPhone(oldStaff.getStaffPhone());
                    }
                    // 修改
                    try {
                        staff.updateStaff(staffDO);
                        JOptionPane.showMessageDialog(UpdateStaffPanel.this, "更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    } catch (RuntimeException err) {
                        err.printStackTrace();
                        JOptionPane.showMessageDialog(UpdateStaffPanel.this, "更新失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(UpdateStaffPanel.this, "没有此编号", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } catch (RuntimeException err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(UpdateStaffPanel.this, "查询失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(updateButton, gbc);

        // 返回按钮
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton backUpdateButton = new JButton("返回查询界面");
        backUpdateButton.addActionListener(e -> cardLayout.show(mainPanel, "QueryStaff"));
        add(backUpdateButton, gbc);

        // 清除数据按钮
        gbc.gridy = 6;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            staffNumberTextField.setText("");
            staffNameTextField.setText("");
            staffSexTextField.setText("");
            staffPhoneTextField.setText("");
        });
        add(clearButton, gbc);

        // 返回主菜单按钮
        gbc.gridy = 7;
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        add(backButton, gbc);

        // 勾选后才可以修改
        addCheckboxListeners();
    }

    // 勾选框监视器
    private void addCheckboxListeners() {
        // 监听复选框状态改变事件
        staffNumberCheckBox.addActionListener(e -> staffNumberTextField.setEnabled(staffNumberCheckBox.isSelected()));
        staffNameCheckBox.addActionListener(e -> staffNameTextField.setEnabled(staffNameCheckBox.isSelected()));
        staffSexCheckBox.addActionListener(e -> staffSexTextField.setEnabled(staffSexCheckBox.isSelected()));
        staffPhoneCheckBox.addActionListener(e -> staffPhoneTextField.setEnabled(staffPhoneCheckBox.isSelected()));
    }
}
