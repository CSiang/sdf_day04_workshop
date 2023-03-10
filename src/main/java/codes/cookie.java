package codes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cookie {
    String dirPath = "data2";
    String fileName = "cookie.txt";

    List <String> cookieItems = null;

    public void readCookieFile () throws FileNotFoundException {

        cookieItems = new ArrayList<String> ();

        File file = new File(dirPath + File.separator + fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String readString;

        
        try{ while((readString = br.readLine()) != null) {
            cookieItems.add(readString);
            } 
            br.close();
            } 
        catch (IOException ex) {
            ex.printStackTrace();
        } 
        

       

    }

    public String returnCookie() {

        Random rand = new Random();

        if(cookieItems != null) {
            return cookieItems.get(rand.nextInt(cookieItems.size()));
        } else {
            return "There is no cookie found.";
        }
    }

    public void showCookies() {
        if (cookieItems != null) {
            cookieItems.forEach(c -> System.out.println(c));
        }

    }

        

    
    
}
