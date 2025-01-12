package com.milacanete.linktracker.utils;

import com.milacanete.linktracker.model.WebPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clase {@code FileUtils} proporciona el método {@link #loadPages(Path)} para cargar
 * páginas web desde un archivo de texto y procesarlas en una lista de objetos WebPage.
 */
public class FileUtils {

    // Logger de la clase para registro de errores
    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    // Expresión regular para validar URLs
    private static final String URL_REGEX = "^(http|https)://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}.*$";

    /**
     * Lee un archivo de texto y transforma su contenido en una lista de objetos {@code WebPage}.
     * Cada línea del archivo debe tener el formato "nombre_pagina; url".
     * Si una línea es inválida, se registra el error y se omite.
     *
     * @param file el path del archivo a leer.
     * @return una lista de objetos WebPage. Si ocurre un error, devuelve una lista vacía.
     */
    public static List<WebPage> loadPages(Path file) {
        // Bloque try-with-resources para asegurar el cierre del Stream
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(String::trim) // Elimina espacios innecesarios al inicio y al final
                    .filter(line -> line.contains(";")) // Asegura que la línea contiene un ";"
                    .map(line -> line.split(";", 2)) // Divide en nombre y URL
                    .filter(parts -> {
                        // Verifica que la línea tenga exactamente 2 partes y que la segunda parte sea una URL
                        return parts.length == 2 && parts[1].matches(URL_REGEX); // Filtra las líneas inválidas
                    })
                    .map(parts -> new WebPage(parts[0].trim(), parts[1].trim())) // Crea el objeto WebPage
                    .collect(Collectors.toList()); // Convierte el resultado a una lista
        } catch (IOException e) {
            //LOGGER.log(Level.SEVERE, "Error al leer el archivo: {0}", e.getMessage());
            return List.of(); // Devuelve una lista vacía en caso de error
        }
    }
}
