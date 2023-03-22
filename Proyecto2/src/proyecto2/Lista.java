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
    
    public Resumen Buscar(String titulo){
        Nodo<Resumen> temp = First;
        if(this.isEmpty()){
        
        }else{
        for (int i = 0; i < this.getSize(); i++) {
            if (temp.getData().getTitulo().equalsIgnoreCase(titulo)){
                return temp.getData();
            }
            temp = temp.getpNext();
        }
        }
        return null;
    }
    
    public PalabraClave BuscarP(String palabra){
        Nodo<PalabraClave> temp = First;
        if(this.isEmpty()){
        
        }else{
        for (int i = 0; i < this.getSize(); i++) {
            if (temp.getData().getPalabra().equalsIgnoreCase(palabra)){
                return temp.getData();
            }
            temp = temp.getpNext();
        }
        }
        return null;
    }
    
    public void OrdenarCrec(){
        
        if (!isEmpty()){
            Nodo<Resumen> aux = (Nodo<Resumen>) getFirst();
            Nodo<Resumen> index;
            Resumen temp;
            while (aux != null){
                index = (Nodo<Resumen>) aux.getpNext();
                while (index != null){
                    String a = String.valueOf(aux.getData().getTitulo().charAt(0));
                    String b = String.valueOf(index.getData().getTitulo().charAt(0));
                    int comp = a.compareTo(b);
                    if(comp > 0){
                        temp = aux.getData();
                        aux.setData(index.getData());
                        index.setData(temp);
                    }
                    index = (Nodo<Resumen>) index.getpNext();
                }aux = (Nodo<Resumen>) aux.getpNext();
            }
        }
    }
    
    public String getTitulos() {
        
        Nodo<Resumen> temp = First;
        String titulos_string = "";
        if(this.isEmpty()){
            titulos_string = "";
        }
        while(temp != null){
            String object = temp.getData().getTitulo();
            titulos_string = titulos_string + object + "\n";
            temp = temp.getpNext();
        }
        return titulos_string;
    }
}
        


    

