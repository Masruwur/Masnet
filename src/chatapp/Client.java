package chatapp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import static resources.TextColors.*;


public class Client {
    public static void main(String[] args) throws IOException {
        try{
            Socket socket = new Socket("192.168.0.118",12345);
            System.out.println(Green+"connected to server"+Reset);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Scanner scn = new Scanner(System.in);

            System.out.print(Yellow+"Enter Username : "+Reset);
            String userName = scn.nextLine();
            System.out.println(Yellow+"Welcome "+userName);
            System.out.println("Enter receiver and your message in seperate lines" +
                    " and press enter to send messages"+Reset);
            System.out.println();

            oos.writeObject(userName);

            new Reader(ois);
            new Writer(oos,userName);

        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println(Red+"Server inactive"+Reset);
        }

    }
}
