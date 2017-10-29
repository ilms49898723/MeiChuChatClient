package com.github.ilms49898723.meichu;

import com.github.ilms49898723.meichu.connection.ChatService;

import java.awt.*;
import javax.swing.*;

public class ChatPlatform {
	
	
	
	private ChatService.Client mClient;
	
	public ChatPlatform(ChatService.Client client) {
		mClient = client;
	}
	
	public void run() {
		JFrame demo = new JFrame();
        demo.setSize(800, 600);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        JTextArea userList = new JTextArea("userList");
        //JTextArea messageList = new JTextArea("messageList");
        //JTextArea inputArea = new JTextArea("inputArea");
        JPanel p1 = new JPanel();
        
        JList list = new JList();
        
        //JPanel p2 = new JPanel();
        //JPanel p3 = new JPanel();
        //JButton inputArea = new JButton("Enter");
        
        
        
        
        
        
        Container ct = demo.getContentPane();
        ct.setLayout(new BorderLayout(20, 20));
        
        userList.setPreferredSize(new Dimension(190, 600));
        //messageList.setPreferredSize(new Dimension(570, 390));
        //inputArea.setPreferredSize(new Dimension(590, 200));
        
        p1.add(userList);
        //p2.add(messageList);
        //p3.add(inputArea);
        ct.add(p1, BorderLayout.WEST);
        //ct.add(p2, BorderLayout.EAST);
        //ct.add(p3, BorderLayout.SOUTH);
         
        /*demo.getContentPane().add(BorderLayout.EAST, checkbox);
        demo.getContentPane().add(BorderLayout.WEST, radiobutton);
        demo.getContentPane().add(BorderLayout.SOUTH, button);
        demo.getContentPane().add(BorderLayout.NORTH , label);*/
        //demo.getContentPane().add(BorderLayout.CENTER, textarea);
         
        demo.setVisible(true);
	}

}
