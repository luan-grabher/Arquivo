
package Testes;

import fileManager.FileManager;
import fileManager.StringFilter;


public class test {


    public static void main(String[] args) {
        String str = "CAIXA FIXO OUT-2020 -  francine.xlsx";
        StringFilter filtro = new StringFilter("CAIXA;FIXO;.xlsx");
        
        System.out.println(filtro.filterOfString(str));
    }
    
}
