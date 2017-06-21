package Controllers;

public class JSBuild {
    /*;\n в начале команд нужно для безопасности js не у всех
    стандартных команд и говорит что предыдущая строка должна быть закончена*/
    private static final String start_funcrion = "(function(){";
    private static final String find_elements = ";let elem_array = document.evaluate(xpath, document.documentElement, null, XPathResult.UNORDERED_NODE_SNAPSHOT_TYPE, null);";
    private static final String var_x_path = ";let xpath =";
    private static final String get_item = ";let item=null; if (elem_array.snapshotLength>num_elem){item=elem_array.snapshotItem(num_elem);}else{return false;}";
    private static final String num_elem = ";let num_elem =";
    private static final String com_click = ";item.click();";
    private static final String return_true = ";return true;";
    private static final String end_function = "}())";

    public static String clickElement(String x_path,Integer num_id){
        return start_funcrion+var_x_path +"\""+ x_path+"\""+num_elem+String.valueOf(num_id)+find_elements+get_item+com_click+return_true+end_function;
    }

    public  static String alert(){return "alert(0)";}

    public  static String getDocument(){
        return start_funcrion +"return document.documentElement"+end_function;
    }

    public static String testGetText(String string){
        return start_funcrion +"return \""+string+"\";"+end_function;
    }
}
