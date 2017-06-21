package Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ElementXpath {
    public enum x_paths{
        BTN_ATTACK,
        OPEN_MAP,
        BTN_SIGN_IN,
        INPUT_LOGIN,
        INPUT_PASSWORD,
        BTN_PLAY,
        UNKNOWN
    }

    public static String getXPath(x_paths paths) {
        switch (paths){
            case BTN_SIGN_IN: return "//*[contains(@value,'ВОЙТИ')]";
            case OPEN_MAP: return "//*[contains(@onclick,'map')]";
            case BTN_ATTACK: return "//*[contains(text(),'Атака')]/..";
            case INPUT_LOGIN:return "//*[contains(@id,'email')]";
            case INPUT_PASSWORD:return "//*[contains(@id,'password')]";
            case BTN_PLAY: return "//*[contains(text(),'играть')]";
        }
        return "";
    }}
