/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Paula Alonso y Marielena Ginez
 */
public class Funciones {
    
    
    /**
     * Metodo crear Hash Table
     * @return HashTable retorna el HashTable
     */
    public static Lista[] newHashTable() {
        Lista[] hashTable = new Lista[13];
        return hashTable;
    }
    
    
    /**
     * Metodo retorna si el HashTable esta vacio
     * @param hashTable Hash Table
     * @return vacio Toma el valor de true si esta vacio 
     */
    public Boolean esVacio(Lista[] hashTable) {
        
        Boolean vacio = true;
        for (Lista hashTable1 : hashTable) {
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
            hash += (caracter_ascii * titulo.indexOf(caracter)); // obtiene la funcion Hash del tipo n∑ codigo ASCII * indice de letra. Ej: (101*0 + 102*1 + 103*2)
        }
        resumen.setClave(hash);
        hash = hash%13; // obtiene el modulo de 13 (size del Hash Table) para determinar la posicion en el arreglo
        return hash; 
    }
    
    /**
     * Metodo insertar articulo en el HashTable
     * @param resumen Articulo a insertar
     * @param hashTable Hash Table
     */
    public static void Insert(Resumen resumen, Lista[] hashTable) {
        
        int hash = hashFunction(resumen);
        if (hashTable[hash]==null) {
            Lista<Resumen> lista = new Lista<>();
            lista.InsertInFinal(resumen);
            hashTable[hash] = lista;
            resumen.setPosicion(hash);
        }else{
            
            if (hashTable[hash].Buscar(resumen.getTitulo())!=null) {
                JOptionPane.showMessageDialog(null, "El artículo " + resumen.getTitulo()+ " ya se encuentra cargado");
            } else {
                hashTable[hash].InsertInFinal(hash);
            }
        }
    }
    
    public static Nodo Buscar(int clave, Nodo[] hashTable){
        int indice = clave%13;
        Nodo busqueda = hashTable[indice];
        if (busqueda!= null) {
            if (busqueda.getpNext()!=null) {
                Resumen resumen = (Resumen) busqueda.getData();
                while (resumen.getClave()!=clave && busqueda.getpNext()!=null) {
                    busqueda = busqueda.getpNext();
                    resumen = (Resumen) busqueda.getData();
                }
                if (resumen.getClave()!=clave) {busqueda = null;}
            }
        } else {
            JOptionPane.showMessageDialog(null, "El artículo no existe");
        }
        return busqueda; 
    }
    
    public static int getFrecuencia(String resumen, String palabra) {
        int frecuencia = 0;
        String[] arr = resumen.split(" ");
        for (int i=0;i<arr.length;i++) {
            String first = String.valueOf(arr[i].charAt(0));
            String last = String.valueOf(arr[i].charAt(arr[i].length()-1));
            String alpha = "[!¡.,()|<>¿?{}\"\\\\[\\\\]]";
           
            if (first.matches(alpha) || first.equals("\n")) {
                arr[i] = arr[i].replace(first, "");
            }
            if (last.matches(alpha) || last.equals("\n")) {
                arr[i] = arr[i].replace(last, "");
            }
            if (arr[i].equalsIgnoreCase(palabra)) {
                frecuencia += 1;
            }
        }
            
        return frecuencia;
    }
    
    /**
     * Metodo seleccionar archivo txt
     */

    private static Component areaTexto;

    public static File FileChooser() {

     File fichero = null;

     JFileChooser fileChooser = new JFileChooser();
     FileNameExtensionFilter filter = new FileNameExtensionFilter(".TXT","txt");
     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
     fileChooser.setFileFilter(filter);
     int seleccion = fileChooser.showOpenDialog(areaTexto);
     if (seleccion == JFileChooser.APPROVE_OPTION){
        fichero = fileChooser.getSelectedFile();
        String path=fichero.getAbsolutePath();
        if(!path.contains("txt")) {
            JOptionPane.showMessageDialog(null, "Por favor elija un archivo del tipo txt");
            return null;
            }
     }

     return fichero;

    }
   
    /**
     * Metodo leer Txt elegido para cargar
     * @param archivo archivo txt a cargar
     * 
     */
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

                 nombre_resumen += datos[0].trim();

                 // En el segundo arreglo lo primero siempre son los autores  y despues el resumen y palabras claves

                 String[] datos2 = datos[1].split("Resumen");
                 autores += datos2[0];
                 
                 String[] datos3 = datos2[1].split("\n");
                 String[] pc = datos3[2].split(":");
                 resumen += datos3[1].trim();
                 palabras_claves+= pc[1];

                 

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
       
     /**
     * Metodo guardar Txt de la información cargada
     * @param hashTable tabla de dispersión con los resumenes cargados
     */
     
       public void GuardarTxt(Lista[] hashTable){
           String cadena = "";
           if(!esVacio(hashTable)){
               for (int i = 0; i<hashTable.length;i++){
                   if(hashTable[i]!=null){
                       Nodo<Resumen> aux = hashTable[i].getFirst();
                           while(aux!=null){
                              cadena += aux.getData().getTitulo()+"#"+aux.getData().getAutores()+"#"+aux.getData().getCuerpo()+"#"+aux.getData().getPalabras_claves()+"\n"; 
                              aux = aux.getpNext();
                       }
                   }
               }
           }
           try{
               PrintWriter pw=new PrintWriter("test\\resumen.txt");
               pw.print(cadena);
               JOptionPane.showMessageDialog(null, "Guardado exitoso");
           }catch(Exception e){
               JOptionPane.showMessageDialog(null,"Error!!!");
           }
       }

}
