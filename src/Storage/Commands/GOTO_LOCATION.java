package Storage.Commands;

import Controllers.JSBuild;
import Storage.Command;
import Storage.ElementXpath;
import Storage.ElementXpath.x_paths;

public class GOTO_LOCATION extends Command {
    public GOTO_LOCATION(int id) {
        name = "Перейти на локацию: ";
        int time_out = getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(x_paths.BTN_OPEN_MAP),0,time_out);
        time_out+= getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(getLocation(id)),0,time_out);
    }

    private x_paths getLocation(int id) {
        switch (id) {
            case 1:
                name += "Тренировочный парк";
                return x_paths.MAP_LOC_1;
            case 2:
                name += "Бестиарий";
                return x_paths.MAP_LOC_2;
            case 3:
                name += "Парадизиум";
                return x_paths.MAP_LOC_3;
            case 4:
                name += "Бар";
                return x_paths.MAP_LOC_4;
            case 5:
                name += "Башня шас";
                return x_paths.MAP_LOC_5;
            case 11:
                name += "Зона Кадаф";
                return x_paths.MAP_LOC_11;
            case 12:
                name += "Мастерские";
                return x_paths.MAP_LOC_12;
            case 13:
                name += "Орден";
                return x_paths.MAP_LOC_13;
            case 14:
                name += "Цитадель";
                return x_paths.MAP_LOC_14;
            case 15:
                name += "Эрийская обитель";
                return x_paths.MAP_LOC_15;
            case 16:
                name += "Зеленный дом";
                return x_paths.MAP_LOC_16;
            case 17:
                name += "БК";
                return x_paths.MAP_LOC_17;
            case 18:
                name += "Зона сражений";
                return x_paths.MAP_LOC_18;
            case 19:
                name += "Лабиринт";
                return x_paths.MAP_LOC_19;
            case 20:
                name += "Зона Чел";
                return x_paths.MAP_LOC_20;
            case 21:
                name += "Лаборатория";
                return x_paths.MAP_LOC_21;
            case 22:
                name += "Офис недвижемости";
                return x_paths.MAP_LOC_22;
            case 23:
                name += "Дегунинский оракул";
                return x_paths.MAP_LOC_23;
            case 24:
                name += "Знающие выселки";
                return x_paths.MAP_LOC_24;
            case 25:
                name += "Монастырь";
                return x_paths.MAP_LOC_25;
            case 26:
                name += "Гиперборея";
                return x_paths.MAP_LOC_26;
            default:
                name += "Зона Кадаф";
                return x_paths.MAP_LOC_11;
        }
    }
}
