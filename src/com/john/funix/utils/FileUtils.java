package com.john.funix.utils;

import com.john.funix.entity.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static final String LIST_EMPLOYEE_FILE_NAME = "list_employee.txt";

    public static <T> void writeFile(List<T> list, String fileName) {
        try {
            FileOutputStream f = new FileOutputStream(new File(fileName));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(list);
            o.close();
            f.close();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        }
    }

    public static <T> List<T> readFile(String fileName) {
        List<T> list = new ArrayList<>();
        try {
            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);
            list = (List<T>) oi.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void clearFile(String fileName) {
        writeFile(new ArrayList<>(), fileName);
    }

    public static <T> void writeListAsString(T item, String fileName) {
        try {
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
            fw.write(item.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readListString(String fileName) {
        List<String> listString = new ArrayList<>();
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                listString.add(line);
                // System.out.println(line);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listString;
    }

}
