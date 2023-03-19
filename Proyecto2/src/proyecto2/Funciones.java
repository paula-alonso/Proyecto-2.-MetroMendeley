/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Paula Alonso y Marielena Ginez
 */
public class Funciones {
    
    
    /**
     * Metodo crear Hash Table
     * @return HashTable retorna el HashTable
     */
    public static Nodo[] newHashTable() {
        Nodo[] hashTable = new Nodo[13];
        return hashTable;
    }
    
    
    /**
     * Metodo retorna si ek HashTable esta vacio
     * @param hashTable Hash Table
     * @return vacio Toma el valor de true si esta vacio 
     */
    public Boolean esVacio(Nodo[] hashTable) {
        
        Boolean vacio = true;
        for (Nodo hashTable1 : hashTable) {
            if (hashTable1 != null) {
                vacio = false;
                break;
            }
        }
        return vacio;
    }
    
    /**
     * Metodo retornar HashFunction
     * @param titulo El titulo del articulo
     * @param resumen Objeto resumen
     * @return hash El indice del articulo en el Hash Table
     */
    private static int hashFunction(Resumen resumen) {
        int hash = 0;
        String titulo = resumen.getTitulo();
        for (int i = 0; i<titulo.length(); i++) {
            char caracter = titulo.charAt(i);
            int caracter_ascii = caracter;
            hash += (caracter_ascii * titulo.indexOf(caracter));
        }
        resumen.setClave(hash);
        hash = hash%13;
        return hash;
    }
    
    /**
     * Metodo insertar articulo en el HashTable
     * @param resumen Articulo a insertar
     * @param hashTable Hash Table
     */
    public static void Insert(Resumen resumen, Nodo[] hashTable) {
        
        int hash = hashFunction(resumen);
        if (hashTable[hash]==null) {
            hashTable[hash] = new Nodo(resumen);
            resumen.setPosicion(hash);
        }else{
            Nodo aux = hashTable[hash];
            while (aux.getpNext()!=null) {
                aux = aux.getpNext();
            }
            Resumen resumen_prev = (Resumen) aux.getData();
            if (!resumen.getTitulo().equals(resumen_prev.getTitulo())) {
                aux.setpNext(new Nodo(resumen));
                resumen.setPosicion(hash);
            } else {
                JOptionPane.showMessageDialog(null, "El artículo " + resumen.getTitulo()+ " ya se encuentra cargado");
            }
        }
    }

    
    private static Component areaTexto;

    public static File FileChooser() {

     File fichero = null;

     JFileChooser fileChooser = new JFileChooser();
     int seleccion = fileChooser.showSaveDialog(areaTexto);
     if (seleccion == JFileChooser.APPROVE_OPTION)
     {
    fichero = fileChooser.getSelectedFile();}

     return fichero;

    }

    public static void LeerFichero(File archivo) {

       if (archivo == null) {JOptionPane.showMessageDialog(null, "No ha seleccionado ningún archivo");} 

       else {

       FileReader fr = null;
       BufferedReader br = null;

       try {
          // Apertura del fichero y creacion de BufferedReader para poder
          // hacer una lectura comoda (disponer del metodo readLine()).

          fr = new FileReader (archivo);
          br = new BufferedReader(fr);

          // Lectura del fichero/////////////////////////////////////////////////////////////////////

       }
       catch(Exception e){
          e.printStackTrace();
       }finally{
          // En el finally cerramos el fichero, para asegurarnos
          // que se cierra tanto si todo va bien como si salta 
          // una excepcion.
          try{                    
             if( null != fr ){   
                fr.close();     
             }                  
          }catch (Exception e2){ 
             e2.printStackTrace();
          }
       }
       }
    }

       public static Resumen LeerTxt(File archivo){

         Resumen r = new Resumen();  

         String line;
         String resumentxt = "";
         //String path = "test\\resumen.txt";
         File file = archivo;
         try{
             if (!file.exists()){
                 file.createNewFile();
             }else{
                 FileReader fr = new FileReader(file);
                 BufferedReader br = new BufferedReader(fr);
                 while((line = br.readLine()) != null){
                     if (!line.isEmpty()){
                         resumentxt += line + "\n";
                     }
                 }
                 if(!"".equals(resumentxt)){

                 String nombre_resumen = "";
                 String autores = "";
                 String resumen = "";
                 String palabras_claves = "";
                 String[] datos = resumentxt.split("Autores");

                 // la posicion 0 siempre es el titulo del resumen

                 if(datos[0].contains("\n")){
                     datos[0].replace("\n", "");
                 }
                 nombre_resumen += datos[0];

                 // En el segundo arreglo lo primero siempre son los autores  y despues el resumen y palabras claves

                 String[] datos2 = datos[1].split("Resumen");
                 autores += datos2[0];
                 String[] datos3 = datos2[1].split("Palabras Claves:");
                 if(datos3[0].contains("\n")){
                     datos3[0].replace("\n", "");
                 }
                 resumen += datos3[0];
                 palabras_claves+= datos3[1];

                 r.setTitulo(nombre_resumen);
                 r.setAutores(autores);
                 r.setCuerpo(resumen);
                 r.setPalabras_claves(palabras_claves);

                     }

                 br.close();
                 JOptionPane.showMessageDialog(null, "Lectura exitosa");
             }
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);}



             return r;


       }

}
