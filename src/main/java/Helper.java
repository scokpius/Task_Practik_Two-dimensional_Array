import java.io.File;

public class Helper {
    static String getPath(String fileName, Class<?> aClass) {
        String pathDir = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String pathFolder = aClass.getName().replace(aClass.getSimpleName(), "").replace(".", File.separator);
        return pathDir+pathFolder+fileName;
    }

}
