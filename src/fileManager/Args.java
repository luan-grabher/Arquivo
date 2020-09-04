package fileManager;

public class Args {

    public static final int INDEX_OF_SEARCH_TYPE_REGEX_BETWEEN = 0;
    public static final int INDEX_OF_SEARCH_TYPE_REGEX_EQUALS = 1;
    public static final int INDEX_OF_SEARCH_TYPE_EQUALS = 2;
    public static final int INDEX_OF_SEARCH_TYPE_EQUALS_IGNORE_CASE = 3;
    public static final int INDEX_OF_SEARCH_TYPE_STRINGFILTER = 4;

    /**
     * Retorna a posição da procura no array de strings informado
     *
     * Se o tipo for REGEX_BETWEEN, vai verificar se a procura está no meio de
     * algum vetor, e irá ignorar os cases.
     *
     * Se o tipo for REGEX_EQUALS, vai verificar se a procura é um match exato
     * de algum vetor.
     *
     * Se o tipo for EQUALS, vai procurar um vetor exatamente igual a procura.
     * Diferencia minusculas de maiusculas.
     *
     * Se o tipo for EQUALS_IGNORE_CASE, você já imagina né?
     *
     * Se o tipo for STRINGFILTER, irá criar uma classe StringFilter com a
     * String e verificar se é filtro de algum vetor em lowercase.
     *
     * @param array Array com strings, o index retornado será o que está aqui.
     * @param searchType Tipo da procura, na classe Args, existe variaveis de
     * ajuda.
     * @param search A procura em si, em String
     * @return Retorna a posição da procura dentro do array de Strings
     *
     */
    public static Integer indexOf(String[] array, int searchType, String search) {
        return indexOf(array, searchType, search, 0);
    }

    /**
     * Retorna a posição da procura no array de strings informado
     *
     * Se o tipo for REGEX_BETWEEN, vai verificar se a procura está no meio de
     * algum vetor, e irá ignorar os cases.
     *
     * Se o tipo for REGEX_EQUALS, vai verificar se a procura é um match exato
     * de algum vetor.
     *
     * Se o tipo for EQUALS, vai procurar um vetor exatamente igual a procura.
     * Diferencia minusculas de maiusculas.
     *
     * Se o tipo for EQUALS_IGNORE_CASE, você já imagina né?
     *
     * Se o tipo for STRINGFILTER, irá criar uma classe StringFilter com a
     * String e verificar se é filtro de algum vetor em lowercase.
     *
     * @param array Array com strings, o index retornado será o que está aqui.
     * @param searchType Tipo da procura, na classe Args, existe variaveis de
     * ajuda.
     * @param search A procura em si, em String
     * @param skip Quantos encontros irá pular até poder fazer o retorno. Defina
     * 0 para não pular nenhum.
     * @return Retorna a posição da procura dentro do array de Strings
     *
     */
    public static Integer indexOf(String[] array, int searchType, String search, int skip) {
        int searchs = 0;
        for (int i = 0; i < array.length; i++) {
            if ((searchType == INDEX_OF_SEARCH_TYPE_REGEX_BETWEEN && array[i].matches("(?i).*?" + search + ".*?"))
                    || (searchType == INDEX_OF_SEARCH_TYPE_REGEX_EQUALS && array[i].matches(search))
                    || (searchType == INDEX_OF_SEARCH_TYPE_EQUALS && array[i].equals(search))
                    || (searchType == INDEX_OF_SEARCH_TYPE_EQUALS_IGNORE_CASE && array[i].toLowerCase().equals(search.toLowerCase()))
                    || (searchType == INDEX_OF_SEARCH_TYPE_STRINGFILTER && (new StringFilter(search)).filterOfString(array[i].toLowerCase()))) {
                searchs++;
                if (searchs > skip) {
                    return i;
                }
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
    public static String get(String[] args, String arg) {
        //Converte o argumento pesquisado para a pesquisa
        arg = "-" + arg;

        //Pega a posição do argumento
        int index = indexOf(args, INDEX_OF_SEARCH_TYPE_EQUALS_IGNORE_CASE, arg, 0);

        //Se encontrar  argumento e a próxima posição existir e não começar com -
        if (index > -1 && index + 1 < args.length && !args[index + 1].startsWith("-")) {
            return args[index + 1];
        }

        return null;
    }
}
