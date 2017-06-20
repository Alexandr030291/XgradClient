package Controller;

public class JSBuild {
    /*;\n в начале команд нужно для безопасности js не у всех
    стандартных команд и говорит что предыдущая строка должна быть закончена*/
    private static final String find_elements = ";\n var elem_array = " +
            "document.evaluate(xpath, document.documentElement, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);";
    private static final String var_x_path = ";\n var xpath =";
    private static final String get_item = ";\n var item=null; if (elem.snapshotLength>num_elem){"+
            " \n\t item=elem_array.snapshotItem(num_elem); \n}ele{\n\t return false; \n }";
    private static final String num_elem = ";\n var num_elem =";
    private static final String com_click = ";\n item.click();\n";
    private static final String return_true = ";\n return true;";

    public static String clickElement(String x_path,Integer num_id){
        return var_x_path + x_path+num_elem+num_id+find_elements+get_item+com_click+return_true;
    }
}
