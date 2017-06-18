package Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ElementXpath {
    public enum x_paths{
        BTN_ATTACK,
        AUTO_IN,
        UNKNOWN
    }

    public static String getXPath(x_paths paths) {
        switch (paths) {
            case BTN_ATTACK: return "btn_attack";
            case AUTO_IN: return "auto_in";
        }
        return "error_command";
    }

    private static final String sFileName = "src/Resource/ElementXpath.properties";
    private static String sDirSeparator = System.getProperty("file.separator");
    private static Properties properties = new Properties();

    public static void main(String[] args) {
        // определяем текущий каталог
        File currentDir = new File(".");
        try {
            // определяем полный путь к файлу
            String sFilePath = currentDir.getCanonicalPath() + sDirSeparator + sFileName;
            FileInputStream ins = new FileInputStream(sFilePath);
            properties.load(ins);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
