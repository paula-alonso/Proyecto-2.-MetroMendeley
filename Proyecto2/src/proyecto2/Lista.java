/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author Paula Alonso y Marielena Ginez
 */
public class Lista<T> {
    private Nodo First;
    private Nodo Last;
    private int size;

    public Lista() {
        this.First = null;
        this.Last = null;
        this.size = 0;
    }
    
    public boolean isEmpty(){
        return getFirst() == null;
    }

    
    /**
     * @return the First
     */
    public Nodo getFirst() {
        return First;
    }

    /**
     * @param First the First to set
     */
    public void setFirst(Nodo First) {
        this.First = First;
    }

    /**
     * @return the Last
     */
    public Nodo getLast() {
        return Last;
    }

    /**
     * @param Last the Last to set
     */
    public void setLast(Nodo Last) {
        this.Last = Last;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
     /**
     * Metodo para insertar un elemento de primero en la lista
     * @param elem Elemento a insertar
     */
    public void InsertAtFirst(T elem){
        Nodo nuevo = new Nodo(elem);
        if(!isEmpty()){
            nuevo.setpNext(First);
           
            First = nuevo;}
        else{
            First = nuevo;
            First.setpNext(Last);
            Last = nuevo;
          
        }
        size++;
    }
    
    
    /**
     * Metodo para insertar un elemento de ultimo en la lista
     * @param elem Elemento a insertar
     */
    
    public void InsertInFinal(T elem){
        Nodo nuevo = new Nodo(elem);
        if (isEmpty()){
            this.InsertAtFirst(elem);
        }else{
            Last.setpNext(nuevo);
            Last= nuevo;
            size++;    
        }
    }
    
    public Resumen Buscar(String busqueda){
        Nodo<Resumen> temp = First;
        if(this.isEmpty()){
        
        }else{
        for (int i = 0; i < this.getSize(); i++) {
            if (temp.getData().getTitulo().equalsIgnoreCase(busqueda)){
                return temp.getData();
            }
            temp = temp.getpNext();
        }
        }
        return null;
    } 
}
        


    

