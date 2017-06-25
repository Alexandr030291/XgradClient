package Storage;

import Libs.Queue;
import Storage.Commands.AutoIn;
import Storage.Commands.GoToLocation;
import Storage.Commands.ToAttackMob;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListCommands {
    private static Queue<Command> _list_command = new Queue<>();
    private static ObservableList<String> names = FXCollections.observableArrayList();

    public static void autoIN(){
        _list_command.push(new AutoIn());
        updateNameList();
    }

    public static void gotoLocation(int id){
        _list_command.push(new GoToLocation(id));
    }

    public static void toAttackMob(int id, int timeout){
        _list_command.push(new ToAttackMob(id,timeout));
    }

    public static  void updateNameList(){
        int length = _list_command.size();
        String[] result = new String[length];
        for (int i =0; i<length;i++){
            result[i]=_list_command.getElement(i).getName();
        }
        names.setAll(FXCollections.observableArrayList(result));
    }

    public static ObservableList<String> getNameList() {
        return names;
    }
}
