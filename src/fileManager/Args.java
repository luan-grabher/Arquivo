package fileManager;

public class Args {

    /**
     * Retorna a posição do regex, argumento ou filtro dentro de um array de Strings.
     * @param array Array com strings, o index retornado será o que está aqui.
     * @param regex Regex para filtrar, sempre irá ignorar os cases e sempre irá procurar aquele regex está no meio do texto, não verifica se o texto é exatamente o regex
     * @param arg Argumento procurado, o texto deverá ser igual a ele, igora os cases
     * @param filter Filtro com possui e não possui, os filtros devem estar em lower case
     * @param skip Quantos encontros irá pular até poder fazer o retorno
     * @return Retorna a posição do regex, argumento ou filtro dentro de um array de Strings
     */
    public static Integer indexOf(String[] array, String regex, String arg, StringFilter filter, int skip) {
        int searchs = 0;
        for (int i = 0; i < array.length; i++) {
            if ((regex != null && array[i].matches("(?i).*?" + regex + ".*?"))
                    | (arg != null && array[i].toLowerCase().equals(arg.toLowerCase()))
                    | (filter != null && filter.filterOfString(array[i].toLowerCase()))) {
                searchs++;
                if (searchs > skip) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Pega posição dentro do array do termo procurado ignorando Ucase, converte
     * tudo para lowercase para comparar
     *
     * @param args Array de String recebido nos argumentos
     * @param arg Termo procurado
     * @return O index do termo procurado no array, se não encontrar retorna -1
     */
    public static Integer indexOf(String[] args, String arg) {
        return indexOf(args,null, arg, null, 0);
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
        return indexOf(args, null, arg, null, skip);
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
       return indexOf(args, null, null, filter, skip);
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
        return indexOf(args, null, null, filter, 0);
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
