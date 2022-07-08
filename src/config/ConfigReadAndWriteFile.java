package config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigReadAndWriteFile<T> {
    public static String PATH = "C:\\Users\\Khoaddow\\Documents\\Project\\Exercise\\CASE-STUDY-MD2\\src\\data\\";
   String  COMMA_DELIMITER="";

    public List<T> readFromFile(String path){
        List<T> tList = new ArrayList<>();
        try {
            File file=new File(path);
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tList = (List<T>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tList;
    }
//    public List<Room> readFromFileCSV(String path){
//        List<Room> tList = new ArrayList<>();
//        BufferedReader br = null;
//        try {
//
//            String line;
//            File file =new File(path);
//            FileReader fileReader=new FileReader(file);
//            br = new BufferedReader(fileReader);
//            // How to read file in java line by line?
//            while ((line = br.readLine()) != null) {
//                Room room =new Room()
//                tList.add()
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (br != null)
//                    br.close();
//            } catch (IOException crunchifyException) {
//                crunchifyException.printStackTrace();
//            }
//        }
//        return tList;
//    }

    public void writeToFile(String path, List<T> tList){
        try {
            File file=new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public  List<String> parseCsvLine(String csvLine) {
        List<String> result = new ArrayList<String>();
        if (csvLine != null) {
            String[] splitData = csvLine.split(COMMA_DELIMITER);
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }

}

