package fileManager;

public class Args {

    /**
     * Pega posição dentro do array do termo procurado ignorando Ucase, converte
     * tudo para lowercase para comparar
     *
     * @param args Array de String recebido nos argumentos
     * @param arg Termo procurado
     * @return O index do termo procurado no array, se não encontrar retorna -1
     */
    public static Integer indexOf(String[] args, String arg) {
        return indexOf(args, arg, 0);
    }


    /**
     * Pega posição dentro do array do termo procurado ignorando Ucase, converte
     * tudo para lowercase para comparar
     *
     * @param args Array de String recebido nos argumentos
     * @param arg Termo procurado
     * @param skip Número de vezez que irá pular
     * @return O index do termo procurado no array, se não encontrar retorna -1
     */
    public static Integer indexOf(String[] args, String arg, int skip) {
        int searchs = 0;
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].toLowerCase().equals(arg.toLowerCase())) {
                searchs++;
                if(searchs > skip){
                    return i;
                }               
            }
        }
        return -1;
    }

    /**
     * Pega posição dentro do array do filtro de string procurado ignorando
     * Ucase, converte os argumentos para lowercase para comparar, o filtro deve
     * estar em lower case
     *
     * @param args Array de String recebido nos argumentos
     * @param filter Filtro procurado
     * @param skip Número de vezez que irá pular
     * @return O index do termo procurado no array, se não encontrar retorna -1
     */
    public static Integer indexOf(String[] args, StringFilter filter, int skip) {
        int searchs = 0;
        for (int i = 0; i < args.length; i++) {
            if (filter.filterOfString(args[i].toLowerCase())) {
                searchs++;
                if (searchs > skip) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Pega posição dentro do array do filtro de string procurado ignorando
     * Ucase, converte os argumentos para lowercase para comparar, o filtro deve
     * estar em lower case
     *
     * @param args Array de String recebido nos argumentos
     * @param filter Filtro procurado
     * @return O index do termo procurado no array, se não encontrar retorna -1
     */
    public static Integer indexOf(String[] args, StringFilter filter) {
        return indexOf(args, filter, 0);
    }

    /**
     * Pega o valor do argumento pesquisado. Os argumentos devem ser definidos
     * por "-" no inicio do nome
     *
     * @param args Array de String recebido nos argumentos
     * @param arg Argumento procurado sem o "-"
     * @return O valor do argumento procurado
     */
    public static String get(String[] args, String arg) {
        //Converte o argumento pesquisado para a pesquisa
        arg = "-" + arg;

        //Pega a posição do argumento
        int index = indexOf(args, arg);

        //Se encontrar  argumento e a próxima posição existir e não começar com -
        if (index > -1 && index + 1 < args.length && !args[index + 1].startsWith("-")) {
            return args[index + 1];
        }

        return null;
    }
}
