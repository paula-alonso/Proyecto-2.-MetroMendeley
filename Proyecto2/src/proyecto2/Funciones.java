/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import Ventanas.Buscar;
import Ventanas.Menu;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Paula Alonso y Marielena Ginez
 */
public class Funciones {
    
    
    /**
     * Metodo crear Hash Table
     * @param size Tamaño del Hash Table
     * @return HashTable retorna el HashTable
     */
    public static Lista[] newHashTable(int size) {
        Lista[] hashTable = new Lista[size];
        return hashTable;
    }
   
    
    /**
     * Metodo retorna si el HashTable esta vacio
     * @param hashTable Hash Table
     * @return vacio Toma el valor de true si esta vacio 
     */
    public static Boolean esVacio(Lista[] hashTable) {
        
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
     * @param clave La clave del articulo
     * @param resumen Objeto resumen
     * @return hash El indice del articulo en el Hash Table
     */
    public static int hashFunction(int clave, int modulo) {
        int hash = clave%modulo; // obtiene el modulo de 13 (size del Hash Table) para determinar la posicion en el arreglo
        return hash; 
    }
    
    /**
     * Metodo retornar Clave
     * @param titulo El titulo del articulo
     * @return clave La clave unica del articulo
     */
    
    public static int getClave(String titulo) {
        int clave = 0;
       
        for (int i = 0; i<titulo.length(); i++) {
            char caracter = titulo.charAt(i);
            int caracter_ascii = caracter;
            clave += (caracter_ascii * titulo.indexOf(caracter)); // obtiene la funcion Hash del tipo n∑ codigo ASCII * indice de letra. Ej: (101*0 + 102*1 + 103*2)
        }
        return clave; 
    }
    
    public static Resumen buscarResumen(int clave, int indice, Lista[] hashTable) {
        Nodo<Resumen> aux = hashTable[indice].getFirst();
        Resumen resumen = null;
        if (aux.getpNext()!=null) {
            while (aux.getpNext()!=null) {
                if (aux.getData().getClave() == clave) {
                    break;
                }
                aux = aux.getpNext();
            }
            resumen = aux.getData();
        } else {
            resumen = aux.getData();
        }
        return resumen;
    }
    
    public static PalabraClave buscarPalabra(int clave, int indice, Lista[] hashTable) {
        Nodo<PalabraClave> aux = hashTable[indice].getFirst();
        PalabraClave palabra= null;
        if (aux.getpNext()!=null) {
            while (aux.getpNext()!=null) {
                if (aux.getData().getClave() == clave) {
                    break;
                }
                aux = aux.getpNext();
            }
            palabra = aux.getData();
        } else {
            palabra = aux.getData();
        }
        return palabra;
    }
    
    
    /**
     * Metodo insertar articulo en el HashTable
     * @param resumen Articulo a insertar
     * @param hashTable Hash Table
     * @param combo
     * @param hashTable2
     */
    public static void Insert(Resumen resumen, Lista[] hashTable, Lista[] hashTable2) {
        
        String referencia = resumen.getTitulo();
        int modulo = hashTable.length;
        int clave = getClave(referencia);
        int hash = hashFunction(clave, modulo);
        
        /////////
        String[] claves = resumen.getPalabras_claves().split(",");
        String[] autores = resumen.getAutores().trim().split("\n");
        
        if (hashTable[hash]!=null && hashTable[hash].Buscar(resumen.getTitulo())!=null) {
            JOptionPane.showMessageDialog(null, "El artículo " + resumen.getTitulo()+ " ya se encuentra cargado");
        } else {
            if (hashTable[hash]==null) {
                Lista<Resumen> lista = new Lista<>();
                lista.InsertInFinal(resumen);
                hashTable[hash] = lista;
                //
                
            }else{
                hashTable[hash].InsertInFinal(resumen);
            }
            
            resumen.setPosicion(hash);
            resumen.setClave(clave);
            
            //
            for (int i=0; i<claves.length; i++){
                InsertPalabra(resumen, claves[i],hashTable2);
            }
            
            for (int i=0; i<autores.length; i++){
                
                if (!Buscar.ba.contenido_combobox.contains(autores[i])) {
                    Buscar.ba.autores.addItem(autores[i]);
                    Buscar.ba.contenido_combobox += autores[i] + ",";
                }
            }
        }
    }
    
    private static void InsertPalabra(Resumen resumen, String palabra, Lista[] hashTable) {
        
        int modulo = hashTable.length;
        int clave = getClave(palabra);
        int hash = hashFunction(clave, modulo);
        
        PalabraClave palabra_clave = new PalabraClave(palabra, clave, resumen);
        
        if (!Menu.busqueda_palabras.contenido_combobox.contains(palabra)) {
            
            Menu.busqueda_palabras.combo_box.addItem(palabra);
            Menu.busqueda_palabras.contenido_combobox += palabra + ",";
        
            if (hashTable[hash]==null) {
                Lista<Object> lista = new Lista<>();
                lista.InsertInFinal(palabra_clave);
                hashTable[hash] = lista;
            }else{
               hashTable[hash].InsertInFinal(palabra_clave);
            }
            
            palabra_clave.setClave(clave);
            
            Lista<Resumen> resumenes = new Lista<>();
            resumenes.InsertInFinal(resumen);
            palabra_clave.setResumenes(resumenes);
        
        } else {
            palabra_clave = (PalabraClave) BuscarP(clave, hashTable).getData();
            Lista resumenes = palabra_clave.getResumenes();
            resumenes.InsertInFinal(resumen);
            palabra_clave.setResumenes(resumenes);
        }
    }
    
    public static Nodo Buscar(int clave, Lista[] hashTable){
        int modulo = hashTable.length;
        int indice = hashFunction(clave, modulo);
        Nodo busqueda = hashTable[indice].getFirst();
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
    
    public static Nodo BuscarP(int clave, Lista[] hashTable){
        int modulo = hashTable.length;
        int indice = hashFunction(clave, modulo);
        Nodo busqueda = hashTable[indice].getFirst();
        if (busqueda!= null) {
            if (busqueda.getpNext()!=null) {
                PalabraClave palabra = (PalabraClave) busqueda.getData();
                while (palabra.getClave()!=clave && busqueda.getpNext()!=null) {
                    busqueda = busqueda.getpNext();
                    palabra = (PalabraClave) busqueda.getData();
                }
                if (palabra.getClave()!=clave) {busqueda = null;}
            }
        } else {
            JOptionPane.showMessageDialog(null, "El artículo no existe");
        }
        return busqueda; 
    }
    
    /**
     * Metodo obtener frecuencia de una palabra en el resumen
     * @param resumen Resumen analizado
     * @param palabra Palabra buscada
     * @return frecuencia Cantidad de veces que aparece la palabra en el texto
     */
    
    private static int getFrecuencia(String resumen, String palabra) {
        
        palabra = palabra.toLowerCase();
        resumen = resumen.toLowerCase();
        String[] arr = resumen.split(palabra);
        int frecuencia = arr.length - 1;
            
        return frecuencia;
    }

    /**
     * Metodo contar todas las palabras claves de un resumen
     * @param resumen Resumen analizado
     * @return frecuencia Cantidad de veces que aparece cada palabra clave en el texto
     */
    
    public static String contarPalabras(Resumen resumen) {
        String palabras = resumen.getPalabras_claves();
        String[] palabra = palabras.split(",");
        String conteo = "";
        
        for (int i = 0; i<palabra.length; i++) {
            int frecuencia = Funciones.getFrecuencia(resumen.getCuerpo(), palabra[i]);
            conteo = conteo + palabra[i] + ": " + frecuencia + "\n";
        }
        
        return conteo;
    }
    
    /**
     * Metodo obtener el analisis de un resumen
     * @param resumen Resumen analizado
     * @return analisis Analisis del resumen
     */
    
    public static String getAnalisis(Resumen resumen) {
        String analisis = "\bTítulo: " + resumen.getTitulo() + "\n\n\bAutores: " + resumen.getAutores();
        analisis = analisis + "\n\bPalabras clave:\n" + contarPalabras(resumen);
        return analisis;
    }
    
    /**
     * Metodo obtener una lista de resumenes
     * @param hashTable Hash Table con todos los resumenes cargados
     * @return resumenes Lista de resumenes
     */
    
    public static Lista getResumenes(Lista[] hashTable) {
        
        Lista resumenes = new Lista();
        
        for (int i = 0; i<hashTable.length; i++) {
            
            if (hashTable[i]!= null) {
                Nodo nodo = hashTable[i].getFirst();
                resumenes.InsertInFinal(nodo.getData());
                while (nodo.getpNext()!=null) {
                    nodo = nodo.getpNext();
                    resumenes.InsertInFinal(nodo.getData());
                }
            }
        }
        return resumenes;
    }
    
    /**
     * Metodo asignar titulos de cada resumen a una JList en la interfaz
     * @param titulos String con los titulos
     * @param lista JList en la interfaz
     */
    
    public static void AsignarTitulos(String titulos, JList lista) {
        
        String[] array_titulos = titulos.split("\n");
        lista.setListData(array_titulos);
        
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
     
       public static void GuardarTxt(Lista[] hashTable){
           String cadena = "";
           if(!esVacio(hashTable)){
               for (int i = 0; i<hashTable.length;i++){
                   if(hashTable[i]!=null){
                       Nodo<Resumen> aux = hashTable[i].getFirst();
                           while(aux!=null){
                              cadena += aux.getData().getTitulo()+"黎"+aux.getData().getAutores()+"黎"+aux.getData().getCuerpo()+"黎"+aux.getData().getPalabras_claves()+"|"; 
                              aux = aux.getpNext();
                       }
                   }
               }
               try{
               PrintWriter pw=new PrintWriter("test\\resumen.txt");
               pw.print(cadena);
               pw.close();
               JOptionPane.showMessageDialog(null, "Guardado exitoso");
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Error!!!");
                }
           }else{
               JOptionPane.showMessageDialog(null, "No hay datos para guardar");
           }
           
       }
       
        

}
