package chatapp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import static resources.TextColors.Green;
import static resources.TextColors.Reset;

public class Writer implements Runnable{
    ObjectOutputStream oos;
    String userName;

    public Writer(ObjectOutputStream oos,String userName){
        this.oos = oos;
        this.userName = userName;
        new Thread(this).start();
    }


    @Override
    public void run(){
        Scanner scn = new Scanner(System.in);

        String receiver=null;
        String message=null;

        while(true){
            //System.out.println("Enter receipent : ");
            receiver = scn.nextLine();
            //System.out.println("Enter message : ");
            message = scn.nextLine();

            String output = userName+"%"+receiver+"%"+message;

            try{
                oos.writeObject(output);
                System.out.println(Green+"message sent to server"+Reset);
                System.out.println();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try{
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
