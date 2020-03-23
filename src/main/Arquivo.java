package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Arquivo {
    
    public static String ler(String localCompletoArquivo){
        return ler(new File(localCompletoArquivo));
    }
    
    /**
     * Lê um arquivo de texto.
     * @param file "C:\Arquivo.txt"
     * @return = Retorna o texto dentro do arquivo.
     */
    public static String ler(File file){
        try {
            String texto;
            File arquivo = file.getAbsoluteFile();
            Scanner scan = new Scanner(arquivo, "latin1");
            texto = scan.useDelimiter("\\A").next();
            scan.close();
            
            return texto;
            
        } catch (Exception e) {
            //e.printStackTrace();
            //return "ERRO NA LEITURA: " + e;
            return "";
        }
    }
    
    /**
     * Cria o diretório e o arquivo pedido.
     * @param fileOriginial Variavel file do arquivo
     * @param texto "ABCDE"
     * @return = Verdadeiro ou falso dependendo se conseguiu criar o arquivo
     */
    public static boolean salvar(File fileOriginial, String texto) {
        try{
            Writer writer;
            BufferedWriter out = null;
            
            File file = fileOriginial.getAbsoluteFile();
            
            writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.ISO_8859_1);
            writer.append(texto);
            writer.flush();
            writer.close();
            return true;
        }catch(Exception e){
            System.out.println("Ocorreu um erro na classe Arquivo: " + e);
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Cria o diretório e o arquivo pedido.
     * @param localSalvar C:\pasta\subpasta
     * @param nomeArquivo nomeArquivo.txt 
     * @param texto "ABCDE"
     * @return = Verdadeiro ou falso dependendo se conseguiu criar o arquivo
     */
    public static boolean salvar(String localSalvar,String nomeArquivo, String texto) {
        try{
            
            criarDiretorios(localSalvar);
            return salvar(localSalvar + "\\" + nomeArquivo, texto);            
        }catch(Exception e){
            System.out.println("Ocorreu um erro na classe Arquivo: " + e);
            e.printStackTrace();
            return false;
        }
    }
    public static boolean salvar(String local_completo, String texto){
        return salvar(new File(local_completo), texto);
    }
    public static boolean criarDiretorios(String nomeDiretorios){
        File dir = new File(nomeDiretorios).getAbsoluteFile();
        Boolean booleano = false;
        try
        {
            if (!dir.exists())// verifica se existe
            {
                if(dir.mkdirs()){
                    booleano = true;//diretorio criado
                    System.out.println("Diretório criado!");
                }
            }else{
                System.out.println("Diretório já existente!");
            }
                
        } catch (Exception e)
        {
            System.out.println("Erro ao criar diretório: " + nomeDiretorios + "\n Erro: " +  e);
            return booleano;
        }
        return booleano;
    }
    
    /**
     * Retorna se o arquivo está aberto ou fechado.
     * @param arquivo Objeto do tipo File
     * @return Verdadeiro se o arquivo estiver aberto ou Falso caso o arquivo esteja fechado
     */
    public static boolean aberto(File arquivo){
        return !arquivo.renameTo(arquivo);
    }
}
