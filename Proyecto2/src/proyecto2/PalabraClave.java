/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author marie
 */
public class PalabraClave {
    
    private String palabra;
    private int clave;
    private Resumen resumen;
    private Lista<Resumen> resumenes;

    public PalabraClave(String palabra, int clave, Resumen resumen) {
        this.palabra = palabra;
        this.clave = clave;
        this.resumen = resumen;
        this.resumenes = new Lista<>();
    }

    /**
     * @return the palabra
     */
    public String getPalabra() {
        return palabra;
    }

    /**
     * @return the clave
     */
    public int getClave() {
        return clave;
    }

    /**
     * @return the resumen
     */
    public Resumen getResumen() {
        return resumen;
    }

    /**
     * @param palabra the palabra to set
     */
    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(int clave) {
        this.clave = clave;
    }

    /**
     * @param resumen the resumen to set
     */
    public void setResumen(Resumen resumen) {
        this.resumen = resumen;
    }

    /**
     * @return the resumenes
     */
    public Lista<Resumen> getResumenes() {
        return resumenes;
    }

    /**
     * @param resumenes the resumenes to set
     */
    public void setResumenes(Lista<Resumen> resumenes) {
        this.resumenes = resumenes;
    }
    
}
