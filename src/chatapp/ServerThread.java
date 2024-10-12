package chatapp;

import java.io.IOException;
import java.util.HashMap;

import static resources.TextColors.*;

public class ServerThread implements Runnable{
    HashMap<String,Information> clientList;
    Information info;

    public ServerThread(HashMap<String,Information> h,Information i){
        clientList = h;
        info = i;
        new Thread(this).start();
    }

    @Override
    public void run(){
        while(true){
            Object dataObj = info.getConnection().read();
            String data = (String)dataObj;

            if(data==null) break;

            String[] segments = data.split("%");

            String sender = segments[0];
            String receiver = segments[1];
            String message="";
            if(segments.length==3){
                message = segments[2];
            }


            if(receiver.equals("Online")){

                var keys = clientList.keySet();
                if(keys.size()==1){
                    clientList.get(sender).getConnection().write("No user except you online");
                    continue;
                }

                String list="Online users : \n";
                for(String key : keys ){
                    list = list + key + "\n";
                }

                clientList.get(sender).getConnection().write(list);
                continue;
            }

            String output = Yellow+sender+" : "+Reset+message;

            if(receiver.equals("All")){
                for(String online : clientList.keySet()){
                    if(!online.equals(sender)){
                        clientList.get(online).getConnection().write(output);
                    }

                }
            }
            else{
                Information receipent = clientList.get(receiver);
                if(receipent!=null){
                    receipent.getConnection().write(output);
                }
                else{
                    clientList.get(sender).getConnection().write(Red+"User offline or does not exist"+Reset);
                }

            }

        }

        try{
            info.getConnection().getSocket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
