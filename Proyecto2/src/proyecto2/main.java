/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;

import java.io.File;

/**
 *
 * @author alons
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here

        File fichero = Funciones.FileChooser();
        Resumen resumen = Funciones.LeerTxt(fichero);
        Lista[] hashTable = Funciones.newHashTable(13);
        System.out.println(resumen.getTitulo()+"\n"+resumen.getAutores()+"\n"+ resumen.getCuerpo()+"\n"+resumen.getPalabras_claves());

        String conteo = Funciones.contarPalabras(resumen);
        System.out.println(conteo);
        Lista resumenes = Funciones.getResumenes(hashTable);
        resumenes.OrdenarCrec();
        String titulos = resumenes.getTitulos();
        int i = 0;
    }
    
}
