package fileManager;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Selector {

    /**
     * Verifica e mostra JFrame
     *
     * @param path Local completo do arquivo/pasta com seu nome e extensão
     * @param fileTypeWithDot Tipo do arquivo com o ponto
     * @return Se o arquivo existir e for daquele tipo de arquivo, retorna
     * verdadeiro, se não, retorna falso.
     */
    public static boolean verifyFile(String path, String fileTypeWithDot) {
        return Selector.verifyFile(path, true, fileTypeWithDot);
    }

    /**
     * Verifica e mostra JFrame
     *
     * @param path Local completo do arquivo/pasta com seu nome e extensão
     * @return Se o arquivo existir e for daquele tipo de arquivo, retorna
     * verdadeiro, se não, retorna falso.
     */
    public static boolean verifyFile(String path) {
        return Selector.verifyFile(path, true, "");
    }

    /**
     * Verifica e mostra JFrame
     *
     * @param path Local completo do arquivo com seu nome e extensão
     * @param fileTypeWithDot Tipo do arquivo com o ponto. Deixe em branco para
     * não verirficar.
     * @param showJframe True para mostrar o jframe de erro caso o retorno seja
     * falso
     * @return Se o arquivo existir e for daquele tipo de arquivo, retorna
     * verdadeiro, se não, retorna falso.
     */
    public static boolean verifyFile(String path, boolean showJframe, String fileTypeWithDot) {
        try {
            if (!fileTypeWithDot.equals("")) {
                //Converte o path para letras minúsculas para melhor cmparação
                path = path.toLowerCase();
                //Converte o tipo do arquivo para letras minusculas para melhor comparação
                fileTypeWithDot = fileTypeWithDot.toLowerCase();

                //Verifica se o path é daquele tipoi de arquivo
                if (!path.endsWith(fileTypeWithDot)) {
                    return false;
                }
            }

            //Verifica se o file existe
            //Se o arquivo existir, retorna verdadeiro, se não, falso
            if ((new File(path)).exists()) {
                return true;
            } else {
                showError("O arquivo não existe!", showJframe);
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Mostra erro por JFrame ou System out
     *
     * @param message Mensagem aser exibida
     * @param showJframe true para mostrar a mensagem de erro em jframe caso
     * ocorra erro, false para não mostrar em nenhuma ocasião
     */
    private static void showError(String message, boolean showJframe) {
        if (showJframe) {
            JOptionPane.showMessageDialog(null,
                    message,
                    " Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            System.out.println(message);
        }
    }

    /**
     * Mostra Jframe para escolher uma pasta.
     *
     * @param currentDirectoryPath Diretório que o seletor de arquivos irá abrir
     * @return File selecionado no Jframe.
     */
    public static File selectFolder(String currentDirectoryPath) {
        return selectFile(currentDirectoryPath, "", "");
    }

    /**
     * Mostra Jframe para escolher uma pasta ou arquivo.
     *
     * @param currentDirectoryPath Diretório que o seletor de arquivos irá abrir
     * @param typeName Nome exibido para o usuário do tipo de arquivo, deixe em
     * branco para escolher uma pasta
     * @param type Tipo do arquivo, acompanhado do ponto, deixe em branco para
     * escolher uma pasta
     * @return File selecionado no Jframe.
     */
    public static File selectFile(String currentDirectoryPath, String typeName, String type) {
        try {
            //Instancia o seletor de arquivos
            JFileChooser fileChooser = new JFileChooser(currentDirectoryPath);

            //Se tiver o tipo de arquivo, seleciona arquivos
            if (!typeName.equals("") && !type.equals("")) {
                fileChooser.setFileFilter(new FileNameExtensionFilter(typeName, type));
                fileChooser.setAcceptAllFileFilterUsed(false);
            } else {
                //Se não, seleciona pasta
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }

            //Se o file selecionado estiver conforme as opções dadas, retorna o file, se não null
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                return fileChooser.getSelectedFile();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retorna o File de um filtro em uma pasta
     *
     * @param folder File do local onde está o arquivo
     * @param has Argumentos separados por ; que devem estar presentes no nome
     * do arquivo. Inclua a extensão.
     * @param hasNot Argumentos separados por ; que não devem estar presentes no
     * nome
     * @return O primeiro arquivo que estar no filtro de possui e não possui no
     * nome. Caso não encontre, retorna null.
     */
    public static File getFileOnFolder(File folder, String has, String hasNot) {
        try {
            //Verifica se a pasta em que procura existe
            if (verifyFile(folder.getAbsolutePath(), false, "")) {
                //Instancia filtro de string
                StringFilter filtro = new StringFilter(has, hasNot);

                //Percorre todos arquivo da pasta
                File[] files = folder.listFiles();
                for (File file : files) {
                    if (filtro.filterOfString(file.getName())) {
                        return file;
                    }
                }
                return null;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
       
    }
}
