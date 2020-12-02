
package Testes;

import fileManager.StringFilter;


public class test {


    public static void main(String[] args) {
        String str = "caixa FIXO OUT-2020 -  francine.xlsx";
        StringFilter filtro = new StringFilter("CAIXA;fixo;.xlsx");
        
        System.out.println(filtro.filterOfString(str));
    }
    
}
