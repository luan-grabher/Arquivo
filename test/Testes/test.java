
package Testes;

import fileManager.FileManager;


public class test {


    public static void main(String[] args) {
        String lugar = "//zac/Robos/Retornos de Tarefas/15716.html";
        
        String texto = FileManager.getText(lugar);
        
        System.out.print(texto);
    }
    
}
