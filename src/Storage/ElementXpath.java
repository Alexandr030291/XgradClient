package Storage;

public class ElementXpath {
    public enum x_paths{
        BTN_A_ATTACK,
        BTN_TO_ATTACK,
        BTN_BATTLE,
        BTN_OPEN_MAP,
        BTN_PLAY,
        BTN_SIGN_IN,
        INPUT_LOGIN,
        INPUT_PASSWORD,
        MAP_LOC_1,
        MAP_LOC_2,
        MAP_LOC_3,
        MAP_LOC_4,
        MAP_LOC_5,
        MAP_LOC_6,
        MAP_LOC_7,
        MAP_LOC_8,
        MAP_LOC_9,
        MAP_LOC_10,
        MAP_LOC_11,
        MAP_LOC_12,
        MAP_LOC_13,
        MAP_LOC_14,
        MAP_LOC_15,
        MAP_LOC_16,
        MAP_LOC_17,
        MAP_LOC_18,
        MAP_LOC_19,
        MAP_LOC_20,
        MAP_LOC_21,
        MAP_LOC_22,
        MAP_LOC_23,
        MAP_LOC_24,
        MAP_LOC_25,
        MAP_LOC_26,
        UNKNOWN
    }

    public static String getXPath(x_paths paths) {
        switch (paths){
            case BTN_A_ATTACK: return "//*[contains(text(),'Атака')]/..";
            case BTN_TO_ATTACK: return "//a[contains(text(),'АТАКОВАТЬ')][contains(@style,'none')]";
            case BTN_BATTLE: return "//a[contains(text(),'Бой')]";
            case BTN_SIGN_IN: return "//*[contains(@value,'ВОЙТИ')]";
            case BTN_PLAY: return "//*[contains(text(),'играть')]";
            case BTN_OPEN_MAP: return "//*[contains(@onclick,'map')]";
            case INPUT_LOGIN:return "//*[contains(@id,'email')]";
            case INPUT_PASSWORD:return "//*[contains(@id,'password')]";
            case MAP_LOC_1: return "//area[contains(@data-id,'1')]";
            case MAP_LOC_2: return "//area[contains(@data-id,'2')]";
            case MAP_LOC_3: return "//area[contains(@data-id,'3')]";
            case MAP_LOC_4: return "//area[contains(@data-id,'4')]";
            case MAP_LOC_5: return "//area[contains(@data-id,'5')]";
            case MAP_LOC_6: return "//area[contains(@data-id,'6')]";
            case MAP_LOC_7: return "//area[contains(@data-id,'7')]";
            case MAP_LOC_8: return "//area[contains(@data-id,'8')]";
            case MAP_LOC_9: return "//area[contains(@data-id,'9')]";
            case MAP_LOC_10: return "//area[contains(@data-id,'10')]";
            case MAP_LOC_11: return "//area[contains(@data-id,'11')]";
            case MAP_LOC_12: return "//area[contains(@data-id,'12')]";
            case MAP_LOC_13: return "//area[contains(@data-id,'13')]";
            case MAP_LOC_14: return "//area[contains(@data-id,'14')]";
            case MAP_LOC_15: return "//area[contains(@data-id,'15')]";
            case MAP_LOC_16: return "//area[contains(@data-id,'16')]";
            case MAP_LOC_17: return "//area[contains(@data-id,'17')]";
            case MAP_LOC_18: return "//area[contains(@data-id,'18')]";
            case MAP_LOC_19: return "//area[contains(@data-id,'19')]";
            case MAP_LOC_20: return "//area[contains(@data-id,'20')]";
            case MAP_LOC_21: return "//area[contains(@data-id,'21')]";
            case MAP_LOC_22: return "//area[contains(@data-id,'22')]";
            case MAP_LOC_23: return "//area[contains(@data-id,'23')]";
            case MAP_LOC_24: return "//area[contains(@data-id,'24')]";
            case MAP_LOC_25: return "//area[contains(@data-id,'25')]";
            case MAP_LOC_26: return "//area[contains(@data-id,'26')]";
        }
        return "";
    }}
