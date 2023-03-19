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
        Lista[] hashTable = Funciones.newHashTable();
        System.out.println(resumen.getTitulo()+"\n"+resumen.getAutores()+"\n"+ resumen.getCuerpo()+"\n"+resumen.getPalabras_claves());
        Funciones.Insert(resumen, hashTable);
        Funciones.Insert(resumen, hashTable);
        Funciones.Insert(resumen, hashTable);
        int frecuencia = Funciones.getFrecuencia(resumen.getCuerpo(), "Meyerhof");
        int i =0;
    }
    
}
