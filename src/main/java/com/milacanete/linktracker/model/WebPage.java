package com.milacanete.linktracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa una página web con su nombre, URL y una lista de enlaces encontrados en la página.
 * Utilizada para almacenar y gestionar información relevante de una página web.
 */
public class WebPage {
    /** Nombre de la página web */
    private String PageName;
    /** URL de la página web */
    private String PageUrl;
    /** Lista de enlaces encontrados en la página web */
    private List<String> linksPage;

    /**
     * Constructor que inicializa los atributos de la clase.
     * @param pageName El nombre de la página web.
     * @param pageUrl La URL de la página web.
     */
    public WebPage(String pageName, String pageUrl) {
        PageName = pageName;
        PageUrl = pageUrl;
        this.linksPage = new ArrayList<>();
    }

    /**
     * Obtiene el nombre de la página web.
     * @return El nombre de la página web.
     */
    public String getPageName() {
        return PageName;
    }

    /**
     * Establece el nombre de la página web.
     * @param pageName El nuevo nombre de la página web.
     */
    public void setPageName(String pageName) {
        PageName = pageName;
    }

    /**
     * Obtiene la URL de la página web.
     * @return La URL de la página web en formato String.
     */
    public String getPageUrl() {
        return PageUrl;
    }

    /**
     * Establece la URL de la página web.
     * @param pageUrl La nueva URL de la página web en formato String.
     */
    public void setPageUrl(String pageUrl) {
        PageUrl = pageUrl;
    }

    /**
     * Obtiene la lista de enlaces encontrados en la página web.
     * @return Una lista de cadenas que representan los enlaces de la página web.
     */
    public List<String> getLinksPage() {
        return linksPage;
    }

    /**
     * Establece la lista de enlaces de la página web.
     * @param linksPage La lista de enlaces a establecer.
     */
    public void setLinksPage(List<String> linksPage) {
        this.linksPage = linksPage;
    }
}
