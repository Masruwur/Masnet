package chatapp;

import java.io.*;

public class UserStorage implements Runnable{
    static String database = "C:\\dsa\\prac\\TcpChat\\src\\resources\\Users.txt";
    Information info;

    public UserStorage(Information info){
        this.info = info;
        new Thread(this).start();
    }


    @Override
    public void run(){
       try{
           BufferedReader inp = new BufferedReader(new FileReader(database));
           BufferedWriter outp = new BufferedWriter(new FileWriter(database,true));

           String name = info.userName;
           String buffer=null;
           boolean stat=false;

           while((buffer = inp.readLine()) !=null){
               if(buffer.equals(name)){
                   stat = true;
                   break;
               }
           }

           if(!stat){
               outp.write(name+"\n");
           }

           inp.close();
           outp.close();

       } catch (IOException e) {
           throw new RuntimeException(e);
       }


    }
}
