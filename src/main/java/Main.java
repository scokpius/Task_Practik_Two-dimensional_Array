import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int size = (int) (Math.random() * 8) + 2;
        Integer[][] array = new Integer[size][size];
        Integer[][] myArrayRead;
        MyMatrix myMatrix;
        final Logger LOGGER = LogManager.getLogger(MyMatrix.class);
        Scanner in = new Scanner(System.in);
        System.out.print("Input a file name (matrixIn.txt): ");
        String fileName = in.next();
        String fileRead = Helper.getPath("/main/java/textFile/" + fileName , Main.class);
        String fileWriter = Helper.getPath("/main/java/textFile/matrixOut.txt", Main.class);
        StringBuilder text = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileRead));
             PrintWriter writer = new PrintWriter(new FileWriter(fileWriter));
             PrintWriter writer1 = new PrintWriter(new FileWriter(fileRead))) {

            createMatrix(array);// рандомное создание матрицы
            writeToFile(array, writer1); // запись матрицы в файл с указанием размеров
            readFromFile(text, reader); //считывание с файла построчно

            String line1 = text.toString();
            String[] string = line1.trim().split("\n");
            String[] length = string[0].trim().split(" ");
            // определение размеров считываемого массива
            int lengthColumn = Integer.parseInt(length[0]);
            int lengthRow = Integer.parseInt(length[0]);

            myArrayRead = convertFromStringToArray(string, lengthColumn, lengthRow);
            myMatrix = new MyMatrix(myArrayRead, lengthColumn,lengthRow);
            writer.print(myMatrix); // запись в фыйл матрицы с диогональю
        } catch (IOException e) {
            LOGGER.info("File not found");
        }
    }

    private static void readFromFile(StringBuilder text, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            text.append(line).append("\n");
        }
    }

    private static void writeToFile(Integer[][] array, PrintWriter writer1) {
        writer1.print(printStringMatrix(array)); // запись матрицы в файл
        writer1.flush();
        writer1.close();
    }

    static Integer[][] convertFromStringToArray(String[] string, int lengthColumn, int lengthRow) {
        Integer[][] myArrayRead;
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
        return myArrayRead;
    }

    protected static void createMatrix(Integer[][] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[0].length; j++) {
                int element = (int) (Math.random() * 2);
                myArray[i][j] = element % 3;
            }
            int count = 1;
            for (int j = 0; j < myArray[0].length; j++) {
                if (myArray[i][j] == 0) {
                    if (count > 1) {
                        int number;
                        do {
                            number = (int) (Math.random() * 14) - 5;
                        }while (number == 0);
                        myArray[i][j] = number;
                    }
                    count++;
                }
            }
        }
    }

    static String printStringMatrix(Integer[][] array) {
        StringBuffer string = new StringBuffer();
        string.append(array.length).append(" ").append(array[0].length).append("\n");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                string.append( array[i][j]).append(" ");
            }
            string.append("\n");
        }
        return  string.toString();
    }

}
