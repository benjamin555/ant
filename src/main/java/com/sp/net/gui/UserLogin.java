package com.sp.net.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserLogin extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton bt1;
	JButton bt2;

	JLabel label0;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JTextField tf;
	JTextField tf0;
	JPasswordField psf;

	public UserLogin() {

		this.setVisible(true);
		this.setSize(250, 220);
		this.setVisible(true);
		this.setLocation(400, 200);

		label0 = new JLabel("域名：");
		label1 = new JLabel("快捷登陆");
		label2 = new JLabel("账号：");
		label3 = new JLabel("密码：");
		tf0 = new JTextField();
		tf = new JTextField();
		psf = new JPasswordField();
		bt1 = new JButton("登录");
		bt1.addActionListener(this);
		bt2 = new JButton("取消");
		bt2.addActionListener(this);
		// 为指定的 Container 创建 GroupLayout
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		//创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGap(5);//添加间隔
		hGroup.addGroup(layout.createParallelGroup().addComponent(label0).addComponent(label2).addComponent(label3)
				.addComponent(bt1));
		hGroup.addGap(5);
		hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(tf0).addComponent(psf)
				.addComponent(tf).addComponent(bt2));
		hGroup.addGap(5);
		layout.setHorizontalGroup(hGroup);
		//创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(label1));
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(label0).addComponent(tf0));
		vGroup.addGap(10);
		vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(tf));
		vGroup.addGap(5);
		vGroup.addGroup(layout.createParallelGroup().addComponent(label3).addComponent(psf));

		vGroup.addGroup(layout.createParallelGroup().addComponent(bt1).addComponent(bt2));
		vGroup.addGap(10);
		//设置垂直组
		layout.setVerticalGroup(vGroup);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("登录")) {
			JOptionPane
					.showMessageDialog(this, "您的用户名为" + tf.getText() + "n" + "你的密码为" + String.valueOf(psf.getText()));
		} else if (e.getActionCommand().equals("取消")) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new UserLogin();
	}
}