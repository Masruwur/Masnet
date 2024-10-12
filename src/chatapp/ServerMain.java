package chatapp;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.HashMap;

import static resources.TextColors.*;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println(Green+"Server Started"+Reset);
        System.out.println(InetAddress.getLocalHost());
        HashMap<String,Information> clientList = new HashMap<>();

        while(true){
            Socket socket = serverSocket.accept();
            Connection connection = new Connection(socket);
            System.out.println(Green+"client connected"+Reset);
            new RegisterConnection(clientList,connection);
        }

    }
}
