/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;

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
        
        Resumen resumen = new Resumen();
        Nodo[] hashTable = Funciones.newHashTable();
        Funciones.Insert(resumen, hashTable);
        Funciones.Insert(resumen, hashTable);
        Funciones.Insert(resumen, hashTable);
        int i = 0;
    }
    
}
