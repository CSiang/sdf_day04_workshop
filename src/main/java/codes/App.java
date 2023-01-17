package codes;

import java.io.File;

public final class App {
    private App() {
    }


    public static void main(String[] args) {

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




    }
}
