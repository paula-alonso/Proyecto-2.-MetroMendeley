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

    /**
     * Metodo constructor
     */
    public Lista() {
        this.First = null;
        this.Last = null;
        this.size = 0;
    }
    
    /**
     * Metodo verifica si la lista esta vacia
     * @return true si la lista esta vacia
     */
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
    
    /**
     * Metodo para buscar un objeto en un elemento del hashtable
     * @param referencia La referencia a partir de la cual se quiere buscar
     * @param tipo Tipo de objeto que se quiere buscar ("r" =  Resumen, "p" = Palabra clave)
     * @return dato encontrado
     */
    
    public Object buscar(String referencia, String tipo){
        Nodo temp = First;
        if(!this.isEmpty()){
            if (temp.getpNext()==null) {
                return temp.getData();
            } else {
                while (temp!=null) {
                    if (tipo.equals("r")){ // si se busca un resumen
                        Resumen resumen = (Resumen) temp.getData();
                        if (resumen.getTitulo().equalsIgnoreCase(referencia)) {
                            return temp.getData();
                        }
                    } if (tipo.equals("p")){ // si se busca una palabra clave
                        PalabraClave palabra = (PalabraClave) temp.getData();
                        if (palabra.getPalabra().equalsIgnoreCase(referencia)) {
                            return temp.getData();
                        }
                    }
                    temp = temp.getpNext();
                }
            }
        }
        return null;
    }
    
    /**
     * Metodo para ordenar la lista alfabeticamente (de forma creciente)
     */
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
    
     /**
     * Metodo para obtener los titulos
     */
    
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
    
    /**
     * Metodo para obtener los titulos segun los autores
     */
    
    
    public String getTitulos2(String autores){
        autores = autores.trim();
        Nodo<Resumen> temp = First;
        String titulos_string = "";
        while(temp != null){
            String object = temp.getData().getTitulo();
            String[] a = temp.getData().getAutores().split("\n");
            for(int i = 0; i<a.length;i++){
                a[i] = a[i].trim();
                if(autores.equals(a[i])){
                    titulos_string += object + "\n";
                    
                }
                
            }temp = temp.getpNext();
        }
        return titulos_string;
    }
}
        


    

