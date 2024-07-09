package com.zhr.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test1 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(488, 430);
        jFrame.setTitle("事件演示");
        jFrame.setDefaultCloseOperation(3);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setAlwaysOnTop(true);
        jFrame.setLayout(null);

        // 创建一个按钮
        JButton jButton = new JButton("点击我");

        // 设置按钮的位置和大小
        jButton.setBounds(0, 0, 100, 50);
        // 给按钮添加动作监听器  动作包括鼠标左键点击，键盘空格
        // jButton.addActionListener(new MyActionListener());
        // 采用匿名内部类去实现
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被点击了");
            }
        });

        // 把按钮添加到窗体上
        jFrame.getContentPane().add(jButton);


        jFrame.setVisible(true);
    }
}
