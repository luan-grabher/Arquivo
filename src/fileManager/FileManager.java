package fileManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FileManager {

    /**
     * Exclui todos arquivos e pastas
     * @param folder Pasta que irá excluir
     */
    public static void cleanFolder(File folder) {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : folder.listFiles()) {

            // if it is a subfolder,e.g Rohan and Ritik,
            // recursiley call function to empty subfolder
            if (subfile.isDirectory()) {
                cleanFolder(subfile);
            }

            // delete files and empty subfolders
            subfile.delete();
        }
    }

    /**
     * Solicita arquivo CSV para o usuário e diz que o arquivo não é valido caso
     * não exista ou o usuário não escolha.
     *
     * @param fileName Nome do arquivo sem a extensão
     * @param fileType Extensão do arquivo sem o Ponto
     * @return O arquivo escolhido
     * @throws java.lang.Exception Causa um erro dizendo que o arquivo nao é
     * valido
     */
    public static File getFileFromUser(String fileName, String fileType) throws Exception {
        JOptionPane.showMessageDialog(null, "Escolha o arquivo " + fileName + ":");
        File file = Selector.selectFile("", fileName + " - ." + fileType, "." + fileType);
        if (file == null || Selector.verifyFile(file.getPath(), true, "." + fileType)) {
            return file;
        } else {
            throw new Exception("O arquivo " + fileName + " não é válido");
        }
    }

    /**
     * Lê um arquivo de texto.
     *
     * @param file "C:\Arquivo.txt"
     * @return = Retorna o texto dentro do arquivo.
     */
    public static String getText(String completePath) {
        return getText(new File(completePath));
    }

    /**
     * Lê um arquivo de texto.
     *
     * @param file "C:\Arquivo.txt"
     * @return = Retorna o texto dentro do arquivo.
     */
    public static String getText(File file, String charset) {
        try {
            if (!file.exists()) {
                throw new Exception("O arquivo '" + file.getAbsolutePath() + "' não existe!");
            }

            String text;
            file = file.getAbsoluteFile();
            Scanner scan = new Scanner(file, charset);
            text = scan.useDelimiter("\\A").next();
            scan.close();

            return text;

        } catch (Exception e) {
            e.printStackTrace();
            //return "ERRO NA LEITURA: " + e;
            return "";
        }
    }

    public static String getText(File file) {
        return getText(file, "latin1");
    }

    /**
     * Cria o diretório e o arquivo pedido.
     *
     * @param file Variavel file do arquivo
     * @param text "ABCDE"
     * @return = Verdadeiro ou falso dependendo se conseguiu criar o arquivo
     */
    public static boolean save(File file, String text) {
        try {
            Writer writer;

            file = file.getAbsoluteFile();

            writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.ISO_8859_1);
            writer.append(text);
            writer.flush();
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na classe Arquivo: " + e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cria o diretório e o arquivo pedido.
     *
     * @param folder C:\pasta\subpasta
     * @param fileName nomeArquivo.txt
     * @param text "ABCDE"
     * @return = Verdadeiro ou falso dependendo se conseguiu criar o arquivo
     */
    public static boolean save(File folder, String fileName, String text) {
        try {

            createFolder(folder);
            return save(folder + "\\" + fileName, text);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro na classe Arquivo: " + e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean save(String completePath, String text) {
        return FileManager.save(new File(completePath), text);
    }

    public static boolean createFolder(File folder) {

        File dir = folder.getAbsoluteFile();
        Boolean booleano = false;
        try {
            if (!dir.exists())// verifica se existe
            {
                if (dir.mkdirs()) {
                    booleano = true;//diretorio criado
                    System.out.println("Diretório criado!");
                }
            } else {
                System.out.println("Diretório já existente!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar diretório: " + folder + "\n Erro: " + e);
            return booleano;
        }
        return booleano;
    }

    /**
     * Retorna o objeto de arquivo se o arquivo existir, se não, mostra um erro.
     *
     * @param path local da pasta ou arquivo
     * @return o objeto de arquivo se o arquivo existir, se não, mostra um erro
     */
    public static File getFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file;
        } else {
            throw new Error("O arquivo/pasta '" + path + "' não foi encontrado!");
        }
    }

    /**
     * Retorna se o arquivo está aberto ou fechado.
     *
     * @param text Objeto do tipo File
     * @return Verdadeiro se o arquivo estiver aberto ou Falso caso o arquivo
     * esteja fechado
     */
    public static boolean isOpen(File text) {
        return !text.renameTo(text);
    }
}
