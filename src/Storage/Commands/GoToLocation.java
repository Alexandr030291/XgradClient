package Storage.Commands;

import Controllers.JSBuild;
import Storage.Command;
import Storage.ElementXpath;
import Storage.ElementXpath.x_paths;

public class GoToLocation extends Command {
    public GoToLocation(int id) {
        name = "Перейти на локацию: "+ getNameLocation(id);
        int time_out = getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(x_paths.BTN_OPEN_MAP),0,time_out);
        time_out+= getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(getLocation(id)),0,time_out);
    }

    private x_paths getLocation(int id) {
        switch (id) {
            case 1:
                return x_paths.MAP_LOC_1;
            case 2:
                return x_paths.MAP_LOC_2;
            case 3:
                return x_paths.MAP_LOC_3;
            case 4:
                return x_paths.MAP_LOC_4;
            case 5:
                return x_paths.MAP_LOC_5;
            case 11:
                return x_paths.MAP_LOC_11;
            case 12:
                return x_paths.MAP_LOC_12;
            case 13:
                return x_paths.MAP_LOC_13;
            case 14:
                return x_paths.MAP_LOC_14;
            case 15:
                return x_paths.MAP_LOC_15;
            case 16:
                return x_paths.MAP_LOC_16;
            case 17:
                return x_paths.MAP_LOC_17;
            case 18:
                return x_paths.MAP_LOC_18;
            case 19:
                return x_paths.MAP_LOC_19;
            case 20:
                return x_paths.MAP_LOC_20;
            case 21:
                return x_paths.MAP_LOC_21;
            case 22:
                return x_paths.MAP_LOC_22;
            case 23:
                return x_paths.MAP_LOC_23;
            case 24:
                return x_paths.MAP_LOC_24;
            case 25:
                return x_paths.MAP_LOC_25;
            case 26:
                return x_paths.MAP_LOC_26;
            default:
                return x_paths.MAP_LOC_11;
        }
    }

    public static String getNameLocation(int id) {
        switch (id) {
            case 1:
                return "Тренировочный парк";
            case 2:
                return "Бестиарий";
            case 3:
                return "Парадизиум";
            case 4:
                return "Бар";
            case 5:
                return "Башня шас";
            case 11:
                return "Зона Кадаф";
            case 12:
                return "Мастерские";
            case 13:
                return "Орден";
            case 14:
                return "Цитадель";
            case 15:
                return "Эрийская обитель";
            case 16:
                return "Зеленный дом";
            case 17:
                return "БК";
            case 18:
                return "Зона сражений";
            case 19:
                return "Лабиринт";
            case 20:
                return "Зона Чел";
            case 21:
                return "Лаборатория";
            case 22:
                return "Офис недвижемости";
            case 23:
                return "Дегунинский оракул";
            case 24:
                return "Знающие выселки";
            case 25:
                return "Монастырь";
            case 26:
                return "Гиперборея";
            default:
                return "";
        }
    }
}
