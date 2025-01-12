package com.milacanete.linktracker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Clase que extiende {@link BufferedReader} para facilitar la lectura de enlaces
 * desde una página web. Esta clase permite extraer y procesar enlaces
 * encontrados en el contenido HTML de una URL dada, con la etiqueta (<a>).
 */
public class LinkReader extends BufferedReader {

    /**
     * Constructor privado de la clase LinkReader.
     * @param reader El {@link Reader} que se usará para leer los datos.
     */
    private LinkReader(Reader reader) {
        super(reader);
    }

    /**
     * Método privado que busca los enlaces en una línea de texto HTML.
     * Extrae todos los elementos <a> encontrados en la línea.
     * @param line La línea de texto HTML en la que se buscarán los enlaces.
     * @return Una cadena con todos los enlaces encontrados en la línea, o {@code null} si no se encuentran.
     */
    private String searchLinks(String line) {
        if(line == null) return line;

        boolean finished = false;
        int begin, end = -1;
        StringJoiner strJoin = new StringJoiner("\n");

        while(!finished) {
            begin = line.indexOf("<a", end + 1);
            if(begin == -1)
                finished = true;
            else {
                end = line.indexOf("</a>", begin + 1);
                if(end == -1) finished = true;
                else strJoin.add(line.substring(begin, end + 4));
            }
        }

        return strJoin.length() == 0?null:strJoin.toString();
    }

    /**
     * Sobrescribe el método {@link BufferedReader#readLine()} para leer líneas
     * del flujo de entrada y buscar enlaces (<a>) en cada línea.
     * @return Una línea con enlaces encontrados o null si no se encuentran más enlaces.
     * @throws IOException Sí ocurre un error de entrada o salida al leer la línea.
     */
    @Override
    public String readLine() throws IOException {
        String line;
        String result = null;
        while(result == null && (line = super.readLine()) != null) {
            result = searchLinks(line);
        }

        return result;
    }

    /**
     * Método estático que obtiene el conjunto de caracteres (charset) a partir
     * del tipo de contenido en los encabezados HTTP de la respuesta.
     * @param contentType El tipo de contenido proporcionado por el encabezado HTTP.
     * @return El conjunto de caracteres (charset), o null si no se encuentra.
     */
    private static String getCharset(String contentType) {
        if (contentType != null) {
            for (String param : contentType.replace(" ", "").split(";")) {
                if (param.startsWith("charset=")) {
                    return param.split("=", 2)[1];
                }
            }
        }
        return null; // Probably binary content
    }

    /**
     * Método estático que obtiene todos los enlaces (<a>) de una página web especificada por su URL.
     * @param urlToParse La URL de la página web de la cual se extraerán los enlaces.
     * @return Una lista de cadenas que representan los enlaces encontrados en la página web.
     */
    public static List<String> getLinks (String urlToParse) {

        LinkReader bufInput = null;
        List<String> result = new ArrayList<>();

        try {
            URL url  = new URL(urlToParse);
            URLConnection conn = url.openConnection();
            String charset = getCharset(conn.getHeaderField("Content-Type"));
            if (charset != null)
                bufInput = new LinkReader(
                        new InputStreamReader(conn.getInputStream(), charset));
            else
                bufInput = new LinkReader(
                        new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = bufInput.readLine()) != null) {

                result.addAll(Arrays.asList(line.split("\n")));
            }
        } catch (IOException _) { }

        return result;
    }

}
