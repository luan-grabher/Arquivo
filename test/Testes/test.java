
package Testes;

import fileManager.CSV;
import java.io.File;
import java.util.List;
import java.util.Map;


public class test {


    public static void main(String[] args) {
        File file = new File("C:\\Users\\ti01\\Downloads\\teste.csv");
        
        List<Map<String, String>> rows = CSV.getMap(file);
        
        rows.get(0).forEach((h,val)->{
            System.out.print(h + " - ");
        });
        
        System.out.println("");
        
        for (Map<String, String> row : rows) {
            row.forEach((h,v)->{
                System.out.print(v + " - ");
            });
            
            System.out.println("");
        }
        
        System.out.println("");
    }
    
}
