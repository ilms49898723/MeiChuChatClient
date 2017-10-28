package com.github.ilms49898723.meichu.handler;

import com.github.ilms49898723.meichu.connection.ChatMessage;
import com.github.ilms49898723.meichu.connection.ChatService;
import org.apache.thrift.TException;

import java.util.List;

public class ChatServiceHandler {
    private ChatService.Client mClient;

    public ChatServiceHandler(ChatService.Client client) {
        mClient = client;
    }

    public void start() {
        try {
            mClient.messagePost(new ChatMessage("my", "hello", System.currentTimeMillis() / 1000));
            List<ChatMessage> msg = mClient.messageGet(System.currentTimeMillis() / 1000);
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
