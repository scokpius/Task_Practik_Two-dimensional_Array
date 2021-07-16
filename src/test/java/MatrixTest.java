import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class MatrixTest {
    final static String fileRead =Helper.getPath("/main/java/textFile/matrixOut.txt", Main.class);

    @Test
    public void checkNotEmptyFile(){
        Integer[][] array = new Integer[][]{{1, 0, 1}, {0, 7, 3}, {1, 1, 0}};

        writingToFile(array, fileRead);
        Assert.assertTrue("File is empty",new File(fileRead).length() > 0);

    }

    @Test
    public void checkExistsFile(){
        Assert.assertTrue("File exists",(new File(fileRead).exists()));
    }


    @Test
    public void checkForMainDiogonal(){
        MyMatrix myMatrix = new MyMatrix( new Integer[][]{{1, 0, 1}, {0, 7, 3}, {1, 1, 0}},3,3);
        Integer[] diogonal = new Integer[3];
        for (int i = 0; i < myMatrix.getMyArray().length; i++) {
            for (int j = 0; j < myMatrix.getMyArray()[0].length; j++) {
                if (i==j) diogonal[i] = myMatrix.getMyArray()[i][j];
            }
        }
        Assert.assertTrue("There is no main diogonal.", presenceOfDiagonal(diogonal));
    }

    private void writingToFile (Integer[][] array, String fileName) {
        PrintWriter writer1 = null;
        try {
            writer1 = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.createMatrix(array);
        writer1.print(Main.printStringMatrix(array)); // запись матрицы в файл
        writer1.flush();
        writer1.close();
    }

    private boolean presenceOfDiagonal(Integer[] diogonal) {
        for (int i = 0; i < diogonal.length; i++) {
            if (diogonal[i] != 0) return false;
        }
        return true;
    }


}


