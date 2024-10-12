package chatapp;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader implements Runnable {
    ObjectInputStream ois;

    public Reader(ObjectInputStream ois){
        this.ois = ois;
        new Thread(this).start();
    }

    @Override
    public void run(){
        String msg=null;
        while(true){
            try {
                Object msgObj = ois.readObject();
                msg = (String)msgObj;
                System.out.println(msg);
                System.out.println();
            } catch (IOException | ClassNotFoundException  e) {
                throw new RuntimeException(e);
            }
        }


    }

}
