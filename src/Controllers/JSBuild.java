package Controllers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class JSBuild {
    private static final String start_function = "function(){ ";
    private static final String start_time_out = "setTimeout(";
    private static final String find_elements = "let elem_array = document.evaluate(xpath, document.documentElement, null, XPathResult.UNORDERED_NODE_SNAPSHOT_TYPE, null);";
    private static final String var_x_path = "let xpath = ";
    private static final String var_time_out = "let timeout = ";
    private static final String get_item = "if (elem_array.snapshotLength<=var_num_elem){return false;}let item=elem_array.snapshotItem(var_num_elem);";
    private static final String var_num_elem = "let var_num_elem = ";
    private static final String var_value = "let value = ";
    private static final String com_click = "item.click();";
    private static final String com_set_value = "item.value = value;";
    private static final String return_true = "return true;";
    private static final String end_time_out = "},timeout)";
    private static final String end_function = "}";

    @NotNull
    public static String clickElement(String x_path, Integer num_id, Integer timeout){
        String command = toVarXPath(x_path)+toVarNumId(num_id);
        command+= find_elements+get_item+com_click+return_true;
        return toAutoRun(command,timeout);
    }
    @NotNull
    public static String setValueElement(String x_path,String value, Integer num_id,Integer timeout){
        String command = toVarXPath(x_path)+toVarNumId(num_id)+toVarValue(value);
        command+= find_elements+get_item+com_set_value+return_true;
        return toAutoRun(command,timeout);
    }
    @Contract(pure = true)
    @NotNull
    private static String toFunction(String string){
        return start_function +string+end_function;
    }
    @Contract(pure = true)
    @NotNull
    private static String toTimeOut(String string){
        return start_time_out +string+end_time_out;
    }
    @Contract(pure = true)
    @NotNull
    private static String toVarXPath(String x_path){
        return var_x_path +"\""+ x_path+"\";";
    }
    @NotNull
    private static String toVarNumId(Integer num_id){
        return var_num_elem +String.valueOf(num_id)+";";
    }
    @Contract(pure = true)
    @NotNull
    private static String toVarValue(String value){
        return  var_value + value + ";";
    }
    @Contract(pure = true)
    @NotNull
    private static String toVarTimeOut(Integer timeout){
        return  var_time_out + timeout + ";";
    }
    @NotNull
    private static String toAutoRun(String string, Integer timeout){
        String result = toFunction(string);
        result =toTimeOut(result);
        result = toVarTimeOut(timeout)+result;
        result = toFunction(result);
        return "("+result+"())";
    }

}
