package Storage;

import java.io.*;
import java.util.Properties;

public class OptionsApp {
    public enum opt{
        TIME_MIN,
        TIME_MAX,
        HEALTH_MIN,
        HEALING_MIN,
        ATTACKED
        }

    private static final String sFileName = "src/Resource/settings.properties";
    private static String sDirSeparator = System.getProperty("file.separator");
    private static Properties properties = new Properties();
    private static String sFilePath;

    private static String getName(opt name) {
        switch (name) {
            case TIME_MIN:
                return "time_min";
            case TIME_MAX:
                return "time_max";
            case ATTACKED:
                return "attacked";
            case HEALTH_MIN:
                return "health_min";
            case HEALING_MIN:
                return "healing_min";
        }
        return "error_opt";
    }

    public static String getSetting(opt name) {
        return properties.getProperty(getName(name),"UTF8");
    }

    public static void setSetting(opt name,String value){
        properties.setProperty(getName(name), String.valueOf(value));
    }

    public static void update(){
        try {
            FileOutputStream uot = new FileOutputStream(sFilePath);
            properties.save(uot, "Update");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public static void load() throws IOException {
        try {
            FileInputStream ins = new FileInputStream(sFilePath);
            properties.load(ins);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // определяем текущий каталог
        File currentDir = new File(".");
        try {
            // определяем полный путь к файлу
            sFilePath = currentDir.getCanonicalPath() + sDirSeparator + sFileName;
            load();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
