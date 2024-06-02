package org.flashlack;

import javax.swing.*;
import java.awt.*;

/**
 * 介绍我们
 * @author FLASHLACK
 */
public class AboutUsPanel extends JPanel {
    public AboutUsPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        // 标签设置
        JLabel titleLabel = new JLabel("关于我们", SwingConstants.CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 文本区域
        JTextArea aboutText = new JTextArea();
        aboutText.setText(
                "欢迎使用此软件！\n" +
                        "\n" +
                        "此软件是一个全面的供应链管理系统，旨在帮助企业高效地管理供应商、库存和销售。\n" +
                        "主要功能包括：\n" +
                        "- 增加、查询、更新和删除供应商信息\n" +
                        "- 管理食品库存\n" +
                        "- 记录和查询销售数据\n\n" +
                        "如果您有任何问题或建议，请随时联系我。(flashlack1314@outlook.com)"
        );
        aboutText.setEditable(false);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setFont(new Font(null, Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(aboutText);
        add(scrollPane, BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("返回主菜单");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}