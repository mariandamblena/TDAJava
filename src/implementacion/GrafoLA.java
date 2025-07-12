package implementacion;
import tda.*;

public class GrafoLA implements GrafoTDA {

    private static class NodoVertice {
        int valor;
        NodoArista arista;
        NodoVertice sig;
    }

    private static class NodoArista {
        int peso;
        NodoArista sig;
        NodoVertice destino;
    }

    private NodoVertice origen;

    @Override
    public void InicializarGrafo() {
        origen = null;
    }

    @Override
    public void AgregarVertice(int v) { // verificado
        NodoVertice nuevo = new NodoVertice();
        nuevo.valor = v;
        nuevo.arista = null;
        nuevo.sig = origen;
        origen = nuevo; // estoy pateando para atras, el ultimo que agrego va al principio
    }

    /*
    * Para agregar una nueva arista al grafo, primero se deben
    * buscar los nodos entre los cuales se va agregar la arista,
    * y luego se inserta sobre la lista de adyacentes del nodo
    * origen (en este caso nombrado como v1)
    */
    @Override
    public void AgregarArista(int v1, int v2, int peso) { // verificado
        NodoVertice n1 = obtenerVertice(v1);
        NodoVertice n2 = obtenerVertice(v2);
        //La nueva arista se inserta al inicio de la lista
        //de nodos adyacentes del nodo origen
        if(n1 != null && n2 != null ){
            NodoArista nueva = new NodoArista();
            nueva.peso = peso;
            nueva.destino = n2;
            nueva.sig = n1.arista;
            n1.arista = nueva;
        }//valor 1 es el inicio, valor 2 es el destino

    }

    @Override
    public void EliminarVertice(int v) { // verificado
        if (origen == null) return;

        // 1) Quitar de todas las listas de adyacencia las aristas a v
        for (NodoVertice aux = origen; aux != null; aux = aux.sig) {
            eliminarAristaNodo(aux, v);
        }

        // 2) Quitar el vértice v de la lista de vértices
        //    (caso cabeza)
        if (origen.valor == v) {
            origen = origen.sig;
            return;
        }
        //    (caso interior o final)
        NodoVertice prev = origen;
        while (prev.sig != null) {
            if (prev.sig.valor == v) {
                prev.sig = prev.sig.sig;
                return;
            }
            prev = prev.sig;
        }
        // Si no se encontraba v, no pasa nada
    }


    @Override
    public void EliminarArista(int v1, int v2) { //verificado
        NodoVertice n1 = obtenerVertice(v1);
        if (n1 != null) {
            eliminarAristaNodo(n1, v2);
        }
    }

    @Override
    public ConjuntoTDA Vertices() { //verificado
        ConjuntoTDA c = new ConjuntoDinamico();
        c.inicializar();
        NodoVertice aux = origen;
        // Recorremos toda la lista hasta que aux sea null
        while (aux != null) {
            c.agregar(aux.valor);
            aux = aux.sig;
        }
        return c;
    }

    @Override
    public boolean ExisteArista(int v1, int v2) { //Verificado
        NodoVertice n1 = obtenerVertice(v1);
        NodoArista aux = n1.arista;
        while(aux != null && aux.destino.valor != v2){
                aux = aux.sig;
        }
        //solo si se encontro la arista buscada, aux no es null
        return aux != null;
    }

    @Override
    public int PesoArista(int v1, int v2) {
        NodoVertice n1 = obtenerVertice(v1);
        NodoArista aux = n1.arista;

        while(aux.destino.valor != v2 ){
            aux = aux.sig;
        }
        //si no encontro ninguna arista entre V1 y V2, el while va a avanzar hasta que aux sea null y ahi aux.destino es null
     return aux.peso;
    }

    public boolean esHamiltoneano(ColaTDA cola){
        if(cola.estaVacia()) return false;

        int primero = cola.primero();
        cola.desacolar();
        cola.acolar(primero);

        int anterior = primero;
        int actual = cola.primero();

        while(actual !=primero){
            cola.desacolar();
            cola.acolar(actual);

            if(!this.ExisteArista(anterior,actual)){
                return false;
            }
            anterior = actual;
            actual = cola.primero();

        }
        if(this.ExisteArista(anterior,primero))
            return true;
        else return false;
    }

    // ----- métodos auxiliares privados -----

    /** Busca el nodo de vértice con valor v (o null si no existe). */ // verificado
    private NodoVertice obtenerVertice(int v) {
        NodoVertice aux = origen;
        while(aux != null && aux.valor != v){
            aux = aux.sig; // voy viendo cada nodo
        }
            return aux;
    }

    /** Elimina de nodo.arista todas las aristas cuyo destino sea valor v. */ // verificado
    private void eliminarAristaNodo(NodoVertice nodo, int v) {

        NodoArista aux = nodo.arista;

        if(aux != null){
            //Si la arista a eliminar es la primera en
            //la lista de nodos adyacentes
            if(aux.destino.valor == v){
                nodo.arista = aux.sig;
            }
            else {
                while (aux.sig !=null && aux.sig.destino.valor !=v){
                    aux = aux.sig;
                }
                if(aux.sig !=null){
                    //Quita la referencia a la arista hacia v
                    aux.sig = aux.sig.sig;
                }
            }
        }
}}
