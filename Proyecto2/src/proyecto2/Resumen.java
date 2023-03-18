/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author alons
 */
public class Resumen {
    private String titulo;
    private String autores;
    private String cuerpo;
    private String palabras_claves;

    public Resumen(String titulo, String autores, String cuerpo, String palabras_claves) {
        this.titulo = titulo;
        this.autores = autores;
        this.cuerpo = cuerpo;
        this.palabras_claves = palabras_claves;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autores
     */
    public String getAutores() {
        return autores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(String autores) {
        this.autores = autores;
    }

    /**
     * @return the cuerpo
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * @param cuerpo the cuerpo to set
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * @return the palabras_claves
     */
    public String getPalabras_claves() {
        return palabras_claves;
    }

    /**
     * @param palabras_claves the palabras_claves to set
     */
    public void setPalabras_claves(String palabras_claves) {
        this.palabras_claves = palabras_claves;
    }
    
    
}
