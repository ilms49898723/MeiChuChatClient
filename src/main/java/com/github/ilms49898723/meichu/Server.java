package com.github.ilms49898723.meichu;

import com.github.ilms49898723.meichu.connection.ChatService;
import com.github.ilms49898723.meichu.connection.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Server {
    public Server(String chatip, int chatport, String userip, int userport) {
        ChatService.Client chat = connectChatService(chatip, chatport);
//        UserService.Client user = connectUserService(userip, userport);
        UIProcessor.getInstance(chat, null);
    }

    private ChatService.Client connectChatService(String ip, int port) {
        try {
            TTransport transport = new TSocket(ip, port);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            ChatService.Client client = new ChatService.Client(protocol);
            return client;
        } catch (TException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private UserService.Client connectUserService(String ip, int port) {
        try {
            TTransport transport = new TSocket(ip, port);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            UserService.Client client = new UserService.Client(protocol);
            return client;
        } catch (TException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
