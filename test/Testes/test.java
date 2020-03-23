
package Testes;

import main.Arquivo;


public class test {


    public static void main(String[] args) {
        Arquivo arq = new Arquivo();
        
        String lugar = "//zac/Robos/Retornos de Tarefas/15716.html";
        
        String texto = arq.ler(lugar);
        
        System.out.print(texto);
    }
    
}
