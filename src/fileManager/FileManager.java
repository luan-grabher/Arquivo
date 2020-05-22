package fileManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class FileManager {
    
    public static String getText(String completePath){
        return getText(new File(completePath));
    }
    
    /**
     * Lê um arquivo de texto.
     * @param file "C:\Arquivo.txt"
     * @return = Retorna o texto dentro do arquivo.
     */
    public static String getText(File file){
        try {
            String text;
            file = file.getAbsoluteFile();
            Scanner scan = new Scanner(file, "latin1");
            text = scan.useDelimiter("\\A").next();
            scan.close();
            
            return text;
            
        } catch (Exception e) {
            //e.printStackTrace();
            //return "ERRO NA LEITURA: " + e;
            return "";
        }
    }
    
    /**
     * Cria o diretório e o arquivo pedido.
     * @param file Variavel file do arquivo
     * @param text "ABCDE"
     * @return = Verdadeiro ou falso dependendo se conseguiu criar o arquivo
     */
    public static boolean save(File file, String text) {
        try{
            Writer writer;
            BufferedWriter out = null;
            
            file = file.getAbsoluteFile();
            
            writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.ISO_8859_1);
            writer.append(text);
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
     * @param folder C:\pasta\subpasta
     * @param fileName nomeArquivo.txt 
     * @param text "ABCDE"
     * @return = Verdadeiro ou falso dependendo se conseguiu criar o arquivo
     */
    public static boolean save(File folder,String fileName, String text) {
        try{
            
            createFolder(folder);
            return save(folder + "\\" + fileName, text);            
        }catch(Exception e){
            System.out.println("Ocorreu um erro na classe Arquivo: " + e);
            e.printStackTrace();
            return false;
        }
    }
    public static boolean save(String completePath, String text){
        return FileManager.save(new File(completePath), text);
    }
    public static boolean createFolder(File folder){
        
        File dir = folder.getAbsoluteFile();
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
            System.out.println("Erro ao criar diretório: " + folder + "\n Erro: " +  e);
            return booleano;
        }
        return booleano;
    }
    
    /**
     * Retorna se o arquivo está aberto ou fechado.
     * @param text Objeto do tipo File
     * @return Verdadeiro se o arquivo estiver aberto ou Falso caso o arquivo esteja fechado
     */
    public static boolean isOpen(File text){
        return !text.renameTo(text);
    }
}
