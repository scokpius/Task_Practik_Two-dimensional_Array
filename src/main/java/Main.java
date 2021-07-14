import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int  size = (int) (Math.random()*8)+2;
   //     size=2;
        System.out.println(size);
        Integer myArray[][] = new Integer[size][size];
 //       Scanner scanner = new Scanner();





        // Для рандомной матрицы просмотр

//        for (int i = 0; i < myArray.length; i++) {
//            for (int j = 0; j < myArray[0].length; j++) {
//                myArray[i][j]=(int) (int) (Math.random()*9);
//                System.out.print(myArray[i][j]+" ");
//            }
//            System.out.println();
//        }


        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[0].length; j++) {
                myArray[i][j]=(int) (Math.random()*9);
            }

        }

        MyArray myArray1 = new MyArray(myArray,myArray.length,myArray[0].length);

        try(FileWriter writer = new FileWriter("matrix.txt", false))
        {
            writer.write(myArray1.toString());
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
