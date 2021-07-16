import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyMatrix {
    private final static Integer elementNull=0;
    private Integer myArray[][];
    private static final Logger LOGGER = LogManager.getLogger(MyMatrix.class);

    public MyMatrix(Integer[][] myArray, int lengthColumn, int lengthRow) {
        this.myArray = new Integer[lengthColumn][lengthRow];
        for (int i = 0; i < lengthColumn; i++) {
            for (int j = 0; j < lengthColumn; j++) {
                this.myArray[i][j] = myArray[i][j];
            }
        }
        LOGGER.info("\n" + printMatrix());

        fillingTheMainDiagonal();
        LOGGER.info("\n" + printMatrix());
    }

    public Integer[][] getMyArray() {
        return myArray;
    }


    private void fillingTheMainDiagonal() {
        for (int i = 0; i < this.myArray.length; i++) {
            for (int j = 0; j < this.myArray[i].length; j++) {
                if (this.myArray[i][j].equals(elementNull)){
                    if (i != j){
                        Integer buffer = this.myArray[i][i];
                        this.myArray[i][i] = this.myArray[i][j];
                        this.myArray[i][j] = buffer;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {

        return printMatrix() ;
    }

    private String printMatrix() {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < this.myArray.length; i++) {
            for (int j = 0; j < this.myArray[i].length; j++) {
                string.append(String.format( "%2d",this.myArray[i][j])).append(" ");
            }
            string.append("\n");
        }
        return  string.toString();
    }

}
