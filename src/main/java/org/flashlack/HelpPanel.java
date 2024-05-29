package org.flashlack;

import javax.swing.*;
import java.awt.*;

/**
 * @author FLASHLACK
 */
public class HelpPanel extends JPanel {

    public HelpPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        // 创建一个 JTextArea 用于显示文本
        JTextArea textArea = new JTextArea();
        textArea.setText("这是帮助界面的内容。\n");
        // 设置为不可编辑
        textArea.setEditable(false);
        // 自动换行
        textArea.setLineWrap(true);
        // 以单词为换行边界
        textArea.setWrapStyleWord(true);
        // 创建一个 JScrollPane 包装 JTextArea，使其具有滚动功能
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JLabel helpLabel = new JLabel("帮助界面", SwingConstants.CENTER);
        add(helpLabel, BorderLayout.NORTH);

        JButton backButton = new JButton("返回主菜单");
        add(backButton, BorderLayout.SOUTH);
        // 为返回按钮添加动作监听器，返回主菜单
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    }
}