/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

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
  
}
