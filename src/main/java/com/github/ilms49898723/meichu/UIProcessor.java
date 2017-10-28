package com.github.ilms49898723.meichu;


import com.github.ilms49898723.meichu.connection.ChatMessage;
import com.github.ilms49898723.meichu.connection.ChatService;
import com.github.ilms49898723.meichu.connection.UserService;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UIProcessor extends JFrame {
    private final Object mLock = new Object();

    private ChatService.Client mChat;
    private UserService.Client mUser;

    private JTabbedPane mTabbedPane;
    private JTextArea mChatField;
    private JTextArea mUserField;
    private JTextField mInputField;

    public static UIProcessor getInstance(ChatService.Client chat, UserService.Client user) {
        return new UIProcessor(chat, user);
    }

    private UIProcessor(ChatService.Client chat, UserService.Client user) {
        mChat = chat;
        mUser = user;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mTabbedPane = new JTabbedPane();
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.PAGE_AXIS));
        mChatField = new JTextArea();
        mChatField.setEditable(false);
        mChatField.setPreferredSize(new Dimension(500, 550));
        JScrollPane chatScroll = new JScrollPane(this.mChatField);
        mInputField = new JTextField();
        mInputField.setPreferredSize(new Dimension(500, 40));
        chatPanel.add(chatScroll);
        chatPanel.add(mInputField);
        mTabbedPane.add("Chat", chatPanel);
        JPanel userPanel = new JPanel();
        mUserField = new JTextArea();
        mUserField.setEditable(false);
        mUserField.setPreferredSize(new Dimension(500, 550));
        JScrollPane userScroll = new JScrollPane(this.mUserField);
        userPanel.add(userScroll);
        mTabbedPane.add("Users", userPanel);
        add(mTabbedPane);
        pack();
        setVisible(true);
        mInputField.addActionListener((e) -> sendMessage());
        new Thread(this::fetchChatInfo).run();
        new Thread(this::fetchUserInfo).run();
    }

    private void sendMessage() {
        String text = mInputField.getText();
        mInputField.setText("");
        synchronized (mLock) {
            try {
                mChat.messagePost(new ChatMessage("Anonymous", text, 1));
            } catch (TException e) {
                e.printStackTrace();
            }
        }
    }

    private void fetchUserInfo() {
        while (true) {
            try {
                List<String> userlist = mUser.getUserList();
                System.out.println(userlist);
                StringBuilder display = new StringBuilder();
                for (String user : userlist) {
                    display.append(user).append("\n");
                }
                mUserField.setText(display.toString());
                Thread.sleep(2000);
            } catch (TException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void fetchChatInfo() {
        while (true) {
            try {
                List<ChatMessage> messages;
                synchronized (mLock) {
                    messages = mChat.messageGet(0);
                }
                StringBuilder builder = new StringBuilder();
                for (ChatMessage message : messages) {
                    builder.append("[").append(message.name).append("]: ").append(message.message).append("\n");
                }
                mChatField.setText(builder.toString());
                Thread.sleep(200000);
            } catch (TException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
