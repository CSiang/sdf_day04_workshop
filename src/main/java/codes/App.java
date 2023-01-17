package codes;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class App {
    private App() {
    }


    public static void main(String[] args) throws IOException {

        String dirPath = "data2";
        String fileName = "Cookie.txt";

        File directory = new File(dirPath);
        File file = new File(dirPath + File.separator + fileName);

        if (directory.exists()) {
            System.out.println("We found the directory.");
        } else if (!directory.exists()) {
            System.out.println("We can't find the directory.");
            System.out.println("Creating new directory...");
            directory.mkdir();
        }

        if(file.exists()) {
            System.out.println("We found the Cookie.txt!!!");
        } else if (!file.exists()) {
            System.out.println("Cookie.txt is not there....");
        }

        cookie cookie = new cookie();
        cookie.readCookieFile();
        cookie.showCookies();

        ServerSocket serSoc = new ServerSocket(7000);
        Socket soc = serSoc.accept();

        try (InputStream is = soc.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            String msgReceived="";

            while(!msgReceived.equals( "close")) {
                msgReceived = dis.readUTF();

                if(msgReceived.equalsIgnoreCase("get-cookie")) {
                    String cookieValue = cookie.returnCookie();
                    System.out.println(cookieValue);
                }

            }

        } catch (EOFException ex) {
            soc.close();
            serSoc.close();
        }

        

        






    }
}
