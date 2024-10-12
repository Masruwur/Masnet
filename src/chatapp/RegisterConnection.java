package chatapp;

import java.util.HashMap;

import static resources.TextColors.*;

public class RegisterConnection  implements Runnable{
    HashMap<String,Information> clientList;
    Connection connection;

    public RegisterConnection(HashMap<String,Information> h,Connection c){
        clientList = h;
        connection = c;
        new Thread(this).start();
    }



    @Override
    public void run(){
        Object userObj = connection.read();
        String userName = (String)userObj;

        Information info = new Information(userName,connection);


        clientList.put(userName,info);
        System.out.println(Green+"client "+userName+" has been registered"+Reset);

        new UserStorage(info);
        new ServerThread(clientList,info);



    }
}
