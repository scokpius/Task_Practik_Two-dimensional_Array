import java.io.*;
import java.util.Arrays;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int size = (int) (Math.random() * 8) + 2;
        Integer[][] array = new Integer[size][size];
        Integer[][] myArrayRead;
        MyArray myArray;

        Scanner in = new Scanner(System.in);
        System.out.print("Input a file name: ");
        String fileName = in.next();
        //Наименование вводимого имение файла - matrixOut.txt
        String fileRead = Helper.getPath("/main/java/textFile/" + fileName , Main.class);
        String fileWriter = Helper.getPath("/main/java/textFile/matrixIn.txt", Main.class);
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileRead));
             PrintWriter writer = new PrintWriter(new FileWriter(fileWriter));
             PrintWriter writer1 = new PrintWriter(new FileWriter(fileRead))) {

            createMatrix(array);// рандомное создание матрицы
            writer1.print(printStringMatrix(array)); // запись матрицы в файл
            writer1.flush();
            writer1.close();
            //считывание с файла построчно
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }

            String line1 = text.toString();

            String[] string = line1.trim().split("\n");
            String[] length = string[0].trim().split(" ");
            // определение длинны считываемого массива
            int lengthColumn = Integer.parseInt(length[0]);
            int lengthRow = Integer.parseInt(length[0]);
            myArrayRead = new Integer[lengthColumn][lengthRow];

            for (int i = 0; i < string.length-1; i++) {
                string[i] = string[i+1];
            }
            String[] stringArrayColumn = Arrays.copyOf(string, string.length-1);
            for (int i = 0; i < stringArrayColumn.length; i++) {
                String[] stringArrayRow = stringArrayColumn[i].trim().split(" ");
                for (int j = 0; j < stringArrayRow.length; j++) {
                    myArrayRead[i][j] = Integer.parseInt(stringArrayRow[j]);
                }
            }
            myArray = new MyArray(myArrayRead, lengthColumn,lengthRow);
            writer.print(myArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createMatrix(Integer[][] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[0].length; j++) {
                int element = (int) (Math.random() * 2);
                myArray[i][j] = element % 3;
            }
            int count = 1;
            for (int j = 0; j < myArray[0].length; j++) {
                if (myArray[i][j] == 0) {
                    if (count > 1) {
                        myArray[i][j] = (int) (Math.random() * 9) + 1;
                    }
                    count++;
                }
            }
        }
    }
    private static String printStringMatrix(Integer[][] array) {
        StringBuffer string = new StringBuffer();
        string.append(array.length).append(" ").append(array[0].length).append("\n");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                string.append(array[i][j]).append(" ");
            }
            string.append("\n");
        }
        return  string.toString();
    }

}
