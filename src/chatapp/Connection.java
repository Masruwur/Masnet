package chatapp;

import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import static resources.TextColors.*;

public class Connection {
    Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public Connection(Socket socket) throws IOException{
        this.socket = socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public Socket getSocket(){
        return socket;
    }

    public void write(Object message){
        try{
            oos.writeObject(message);
        } catch (IOException e) {
            System.out.println(Red+"Failed to write - user might be disconnected"+Reset);
        }
    }

    public Object read(){
        Object message = null;
        try{
            message = ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(Red+"Failed to read - user might be disconnected"+Reset);
        }

        return message;
    }
}
