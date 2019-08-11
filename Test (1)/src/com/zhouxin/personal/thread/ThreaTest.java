package com.zhouxin.personal.thread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreaTest
{
    public static void main(String[] args)
    {
        System.out.println("这是一个学习多线程的测试示例!");
        new GUI().setVisible(true);
    }
}

class GUI extends JFrame
{
    public GUI()
    {
        this.setTitle("多线程");
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(null);
        JButton jButton = new JButton("点我");
        this.getContentPane().add(jButton);
        jButton.setLocation(200, 150);
        jButton.setSize(100, 50);

        jButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Thread(new SingleThread()).run();
            }
        });
    }
}
