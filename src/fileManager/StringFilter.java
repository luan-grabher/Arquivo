package fileManager;

import java.util.Map;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

public class StringFilter {

    private Map<String, String> has = new CaseInsensitiveMap<>();
    private Map<String, String> hasNot = new CaseInsensitiveMap<>();

    /**
     * Inicia Classe sem definir os filtros
     */
    public StringFilter() {
    }

    /**
     * Inicia Classe com os filtros definidos em uma string unica separada com #
     * o 'possui' e 'não possui'
     *
     * @param filter filtros definidos em uma string unica separada com # o
     * 'possui' e 'não possui'
     */
    public StringFilter(String filter) {
        try {
            //Divide em possui e nao possui
            String[] splitHasAndHasNot = filter.split("#");

            //Coloca a lista que deve possuir
            putStringListOnMap(splitHasAndHasNot[0], has);

            //Se tiver não possui
            if (splitHasAndHasNot.length == 2) {
                //Coloca a lista de nao possui
                putStringListOnMap(splitHasAndHasNot[1], hasNot);
            }
        } catch (Exception e) {
        }
    }

    /**
     * Inicia Classe com os filtros definidos em strings
     *
     * @param hasList Lista de termos separados por ; que devem estar no filtro
     * @param hasNotList Lista de termos separados por ; que NÃO devem estar no
     * filtro
     */
    public StringFilter(String hasList, String hasNotList) {
        putStringListOnMap(hasList, has);
        putStringListOnMap(hasNotList, hasNot);
    }

    public Map<String, String> getHas() {
        return has;
    }

    public Map<String, String> getHasNot() {
        return hasNot;
    }        

    public void setHas(Map<String, String> has) {
        this.has = has;
        has.remove("");
    }

    public void setHasNot(Map<String, String> hasNot) {
        this.hasNot = hasNot;
        hasNot.remove("");
    }

    /**
     * Define o filtro que deve ter na string a partir de uma lista de termos
     * separados por ponto e virgula
     *
     * @param stringList Lista de termos
     */
    public void addStringsOnHasMap(String stringList) {
        putStringListOnMap(stringList, has);
    }

    /**
     * Define o filtro que não deve ter na string a partir de uma lista de
     * termos separados por ponto e virgula
     *
     * @param stringList Lista de termos
     */
    public void addStringsOnHasNotMap(String stringList) {
        putStringListOnMap(stringList, hasNot);
    }

    /**
     * Adiciona as strings de uma lista de strings separadas por ponto e virgula
     * no mapa definido.
     *
     * @param stringList Lista de termos
     */
    private void putStringListOnMap(String stringList, Map<String, String> map) {
        if(stringList != null){
            String[] strings = stringList.split(";");
            for (String string : strings) {
                map.put(string, string);
            }
            
            map.remove("");
        }
    }

    /**
     * Verifica se o filtro é um filtro válido da string informada
     *
     * @param string String que deve estar adequada ao filtro
     * @return True se o filtro é um filtro válido da string informada, false se não 
     */
    public boolean filterOfString(String string) {
        //É comparado em lower case porque o mapa no put define as strings como lowercase na key
        has.remove("");
        hasNot.remove("");
        
        //Se a string não tiver algum dos termos que deve possuir, retorna falso
        if (!has.entrySet().stream().noneMatch((entry) -> (!string.toLowerCase().contains(entry.getKey())))) {
            return false;
        }
        //Se a string não tiver nenhum termo que NAO deve possui, retorna true, se não false
        return hasNot.entrySet().stream().noneMatch((entry) -> (string.toLowerCase().contains(entry.getKey())));
    }

    /**
     * Converte o mapa de strings em uma string separada pelo separador
     * informado.
     *
     * @param separator A String que irá separar as strings
     * @param map O mapa a ser convertido para string
     * @return retorna o mapa de strings em uma string separada pelo separador
     * informado.
     */
    public String printMap(Map<String, String> map, String separator) {
        //Se tiver algo dentro da lista
        if (map.size() > 0) {
            //Instancia string builder
            StringBuilder sb = new StringBuilder();
            //percorre mapa
            map.entrySet().forEach((entry) -> {
                sb.append(entry.getKey()).append(separator);
            });

            //Retorna a lista em string e remove ultimo separador inserido
            return sb.toString().substring(0, sb.toString().length() - separator.length());
        } else {
            return "";
        }
    }

}
