package com.github.ilms49898723.meichu;

import com.github.ilms49898723.meichu.connection.ChatService;
import com.github.ilms49898723.meichu.handler.ChatServiceHandler;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Server {
    public Server(String ip, int port) {
        try {
            TTransport transport = new TSocket(ip, port);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            // IMPORTANT
            ChatService.Client client = new ChatService.Client(protocol);
            // IMPORTANT
            ChatServiceHandler handler = new ChatServiceHandler(client);
            handler.start();
        } catch (TException ex) {
            ex.printStackTrace();
        }
    }
}
