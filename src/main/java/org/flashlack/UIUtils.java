package org.flashlack;
import javax.swing.*;
import java.awt.*;

/**
 * 方法类
 * @author FALSHLACK
 */
public class UIUtils {

    /**
     * 添加标签和对应的文本框到面板中
     *
     * @param panel 要添加组件的面板
     * @param gbc 布局约束
     * @param labelText 标签的文本
     * @param yPos 标签和文本框所在的行位置
     * @return 创建的 JTextField
     */
    public static JTextField addLabeledTextField(JPanel panel, GridBagConstraints gbc, String labelText, int yPos) {
        // 设置标签
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel label = new JLabel(labelText);
        panel.add(label, gbc);

        // 设置文本框
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField textField = new JTextField();
        panel.add(textField, gbc);

        return textField;
    }

    /**
     * 设置组件的字体
     *
     * @param font 字体对象
     * @param components 要设置字体的组件
     */
    public static void setFont(Font font, JComponent... components) {
        for (JComponent component : components) {
            component.setFont(font);
        }
    }

    /**
     * 添加复选框再添加标签和对应文本框
     * @param panel 要添加组件的面板
     * @param gbc 布局约束
     * @param labelText 标签的文本
     * @param yPos 标签和文本框所在的行位置
     * @return 创建的 JTextField
     */
    public static JTextField addLabeledTextFieldWithCheckbox(JPanel panel, GridBagConstraints gbc, JCheckBox checkBox, String labelText, int yPos) {
        // 设置复选框
        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(checkBox, gbc);

        // 设置标签
        gbc.gridx = 1;
        JLabel label = new JLabel(labelText);
        panel.add(label, gbc);

        // 设置文本框
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField textField = new JTextField();
        // 默认禁用文本框
        textField.setEnabled(false);
        panel.add(textField, gbc);

        return textField;
    }
}
