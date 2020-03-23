
package Testes;

import main.Arquivo;


public class test {


    public static void main(String[] args) {
        String lugar = "//zac/Robos/Retornos de Tarefas/15716.html";
        
        String texto = Arquivo.ler(lugar);
        
        System.out.print(texto);
    }
    
}
