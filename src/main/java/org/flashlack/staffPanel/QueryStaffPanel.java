package org.flashlack.staffPanel;

import org.flashlack.UIUtils;
import org.flashlack.entity.StaffDO;
import org.flashlack.mappers.impl.StaffImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Function;

/**
 * 查询员工类界面
 * @author FLASHLACK
 */
public class QueryStaffPanel extends JPanel {
    private final JCheckBox staffNumberCheckBox;
    private final JTextField staffNumberTextField;
    private final JCheckBox staffNameCheckBox;
    private final JTextField staffNameTextField;
    private final JCheckBox staffSexCheckBox;
    private final JTextField staffSexTextField;
    private final JTextArea resultArea;

    public QueryStaffPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        // 设置布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 0, 10);

        staffNumberCheckBox = new JCheckBox();
        staffNumberTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffNumberCheckBox, "员工编号", 0);

        staffNameCheckBox = new JCheckBox();
        staffNameTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffNameCheckBox, "员工姓名", 1);

        staffSexCheckBox = new JCheckBox();
        staffSexTextField = UIUtils.addLabeledTextFieldWithCheckbox(this, gbc, staffSexCheckBox, "员工性别", 2);
        // 增加字体宽度
        Font font = new Font(null, Font.PLAIN, 15);
        UIUtils.setFont(font, staffNumberTextField, staffNameTextField, staffSexTextField);

        // 添加查询按钮
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton searchButton = new JButton("查询");
        searchButton.addActionListener(e -> {
            StaffImpl staff = new StaffImpl();
            // 获取选中的复选框对应的文本框值并进行处理
            StaffDO staffDO = new StaffDO();
            if (staffNumberCheckBox.isSelected()) {
                staffDO.setStaffNumber(Integer.valueOf(staffNumberTextField.getText()));
                performQuery(staff::selectStaffListByNumber, staffDO);
            } else if (staffNameCheckBox.isSelected()) {
                staffDO.setStaffName(staffNameTextField.getText());
                performQuery(staff::selectStaffListByName, staffDO);
            } else if (staffSexCheckBox.isSelected()) {
                staffDO.setStaffSex((staffSexTextField.getText()));
                performQuery(staff::selectStaffListBySex, staffDO);
            }
        });
        add(searchButton, gbc);

        // 添加跳转到更新界面的按钮
        gbc.gridy = 4;
        JButton updateButton = new JButton("更新员工(请记住编号)");
        updateButton.addActionListener(e -> cardLayout.show(mainPanel, "UpdateStaff"));
        add(updateButton, gbc);

        // 添加跳转到删除界面的按钮
        gbc.gridy = 5;
        JButton deleteButton = new JButton("删除员工(请记住编号)");
        deleteButton.addActionListener(e -> cardLayout.show(mainPanel, "DeleteStaff"));
        add(deleteButton, gbc);

        // 添加清除数据按钮
        gbc.gridy = 6;
        JButton clearButton = new JButton("清除数据");
        clearButton.addActionListener(e -> {
            // 清除所有文本框的数据
            staffNumberTextField.setText("");
            staffNameTextField.setText("");
            staffSexTextField.setText("");
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

        // 确保勾选一个
        addCheckboxListeners();
    }


    // 勾选框监视器
    private void addCheckboxListeners() {
        staffNumberCheckBox.addActionListener(e -> toggleTextFields(staffNumberCheckBox));
        staffNameCheckBox.addActionListener(e -> toggleTextFields(staffNameCheckBox));
        staffSexCheckBox.addActionListener(e -> toggleTextFields(staffSexCheckBox));
    }

    // 确保只能填写一个
    private void toggleTextFields(JCheckBox selectedCheckBox) {
        JCheckBox[] checkboxes = {staffNumberCheckBox, staffNameCheckBox,staffSexCheckBox};
        JTextField[] fields = {staffNumberTextField, staffNameTextField,staffSexTextField};
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
    private void performQuery(Function<StaffDO, java.util.List<StaffDO>> queryFunction, StaffDO staffDO) {
        try {
            List<StaffDO> results = queryFunction.apply(staffDO);
            if (!results.isEmpty()) {
                StringBuilder resultText = new StringBuilder();
                for (StaffDO result : results) {
                    resultText.append(result.toChineseString()).append("\n\n");
                }
                resultArea.setText(resultText.toString());
            } else {
                JOptionPane.showMessageDialog(QueryStaffPanel.this, "没有此类员工", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException err) {
            err.printStackTrace();
            JOptionPane.showMessageDialog(QueryStaffPanel.this, "查询失败: " + err.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}