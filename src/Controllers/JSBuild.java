package Controllers;

public class JSBuild {
    /*;\n в начале команд нужно для безопасности js не у всех
    стандартных команд и говорит что предыдущая строка должна быть закончена*/
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

    public static String clickElement(String x_path,Integer num_id,Integer timeout){
        String var = toVarXPath(x_path)+toVarNumId(num_id);
        var+= find_elements+get_item+com_click+return_true;
        return toAutoRun(toFunction(toVarTimeOut(timeout)+toTimeOut(toFunction(var))));
    }

    public static String setValueElement(String x_path,String value, Integer num_id,Integer timeout){
        String var = toVarXPath(x_path)+toVarNumId(num_id)+toVarValue(value);
        var+= find_elements+get_item+com_set_value+return_true;
        return toAutoRun(toFunction(toVarTimeOut(timeout)+toTimeOut(toFunction(var))));
    }

    private static String toFunction(String string){
        return start_function +string+end_function;
    }

    private static String toTimeOut(String string){
        return start_time_out +string+end_time_out;
    }

    private static String toVarXPath(String x_path){
        return var_x_path +"\""+ x_path+"\";";
    }

    private static String toVarNumId(Integer num_id){
        return var_num_elem +String.valueOf(num_id)+";";
    }

    private static String toVarValue(String value){
        return  var_value + value + ";";
    }

    private static String toVarTimeOut(Integer timeout){
        return  var_time_out + timeout + ";";
    }

    private static String toAutoRun(String string){
        return "("+string+"())";
    }

}
