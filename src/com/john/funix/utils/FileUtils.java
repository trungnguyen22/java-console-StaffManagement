package com.john.funix.utils;

import com.john.funix.entity.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static final String LIST_EMPLOYEE_FILE_NAME = "list_employee.txt";

    public static <T> void writeList(List<T> list) {
        try {
            FileOutputStream f = new FileOutputStream(new File(LIST_EMPLOYEE_FILE_NAME));
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

    public static <T> List<T> readList() {
        List<T> list = new ArrayList<>();
        try {
            FileInputStream fi = new FileInputStream(new File(LIST_EMPLOYEE_FILE_NAME));
            ObjectInputStream oi = new ObjectInputStream(fi);
            list = (List<T>) oi.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


}
