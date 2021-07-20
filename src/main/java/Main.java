import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;

import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(MyMatrix.class);

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        System.out.print("Input a file name (matrixIn.txt): ");
        String fileName = in.next();
        String fileRead = Helper.getPath("/main/java/textFile/" + fileName , Main.class);
        String fileWriter = Helper.getPath("/main/java/textFile/matrixOut.txt", Main.class);


        // createMatrix() рандомно созданет и возвращает матрицу
        writeToFile(createMatrix(), fileRead);// записывает матрицу в файл
        // readFromFile(fileRead); считывает матрицу с файла и преобразовывает
        matrixDiagonalWriteToFile(readFromFile(fileRead), fileWriter);

    }

    private static void matrixDiagonalWriteToFile(MyMatrix myMatrix, String fileWriter) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileWriter))) {
            writer.print(myMatrix); // запись в фыйл матрицы с диогональю
        } catch (IOException e) {
            LOGGER.info("File not found");
        }
    }

    private static MyMatrix  readFromFile(String fileRead) {
        StringBuilder text = new StringBuilder();
        MyMatrix myMatrix = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileRead))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
            Integer[][] arrayRead;
            String line1 = text.toString();
            String[] string = line1.trim().split("\n");
            Integer[] size = determiningSizeTheReadArray(string);
            // преобразовывает текст а Integer матрицу
            arrayRead = convertFromStringToArray(string, size[0], size[1]);
            myMatrix = new MyMatrix(arrayRead, size[0], size[1]);
        } catch (IOException e) {
            LOGGER.info("File not found");
        }
        return myMatrix;
    }

    private static Integer[] determiningSizeTheReadArray(String[] string){
        String[] length = string[0].trim().split(" ");
        Integer[] arraySize = new Integer[length.length];
        // определение размеров считываемого массива
        arraySize[0] = Integer.parseInt(length[0]);
        arraySize[1]  = Integer.parseInt(length[1]);
        return arraySize;
    }

    private static void writeToFile(Integer[][] array, String fileRead) {
        try (PrintWriter writer1 = new PrintWriter(new FileWriter(fileRead))) {
            writer1.print(printStringMatrix(array)); // запись матрицы в файл
            writer1.flush();
        } catch (IOException e) {
            LOGGER.info("File not found");
        }
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

    protected static Integer[][] createMatrix() {
        int size = (int) (Math.random() * 8) + 2;
        Integer[][] array = new Integer[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int element = (int) (Math.random() * 2);
                array[i][j] = element % 3;
            }
            int count = 1;
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 0) {
                    if (count > 1) {
                        int number;
                        do {
                            number = (int) (Math.random() * 14) - 5;
                        }while (number == 0);
                        array[i][j] = number;
                    }
                    count++;
                }
            }
        }
        return array;
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
