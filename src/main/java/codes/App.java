package codes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

            try(OutputStream os = soc.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

            while(!msgReceived.equals( "close")) {
                msgReceived = dis.readUTF();

                if(msgReceived.equalsIgnoreCase("get-cookie")) {
                    String cookieValue = cookie.returnCookie();
                    System.out.println(cookieValue);

                    dos.writeUTF(cookieValue);
                    dos.flush();
                }
            }
            dos.close();
            bos.close();
            os.close();

        }} catch (EOFException ex) {
            System.out.println("Server shut down.");
            soc.close();
            serSoc.close();
        }
    }
}
