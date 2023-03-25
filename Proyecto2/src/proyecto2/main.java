/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author alons
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String key;

        // TODO code application logic here

        
        Path currentRelativePath = Paths.get("test");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);
//        File fichero = Funciones.FileChooser();
//        Resumen resumen = Funciones.LeerTxt(fichero);
//        Lista[] hashTable = Funciones.newHashTable();
//        System.out.println(resumen.getTitulo()+"\n"+resumen.getAutores()+"\n"+ resumen.getCuerpo()+"\n"+resumen.getPalabras_claves());
//        Funciones.Insert(resumen, hashTable);
//        String conteo = Funciones.contarPalabras(resumen);
//        System.out.println(conteo);
//        Lista resumenes = Funciones.getResumenes(hashTable);
//        resumenes.OrdenarCrec();
//        String titulos = resumenes.getTitulos();
//        int i = 0;


    }
    
}
