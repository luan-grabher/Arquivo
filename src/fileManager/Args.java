package fileManager;

public class Args {

    /**
     * Pega posição dentro do array do termo procurado ignorando Ucase, converte tudo para lowercase para comparar
     *
     * @param args Array de String recebido nos argumentos
     * @param arg Termo procurado
     * @return O index do termo procurado no array, se não encontrar retorna -1
     */
    private Integer indexOf(String[] args, String arg) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().equals(arg.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Pega o valor do argumento pesquisado. Os argumentos devem ser definidos
     * por "-" no inicio do nome
     *
     * @param args Array de String recebido nos argumentos
     * @param arg Argumento procurado sem o "-"
     * @return O valor do argumento procurado
     */
    private String get(String[] args, String arg) {
        //Converte o argumento pesquisado para a pesquisa
        arg = "-" + arg;

        //Pega a posição do argumento
        int index = indexOf(args, arg);

        //Se encontrar  argumento e a próxima posição existir e não começar com -
        if (index > -1 && index + 1 < args.length && args[index + 1].startsWith("-")) {
            return args[index + 1];
        }

        return null;
    }
}
