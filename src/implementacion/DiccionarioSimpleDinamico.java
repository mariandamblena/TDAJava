package implementacion;

import tda.ConjuntoTDA;
import tda.DiccionarioSimpleTDA;

public class DiccionarioSimpleDinamico implements DiccionarioSimpleTDA {

    class nodo{
        int clave;
        int valor;
        nodo siguiente;
    }

    nodo origen;

    @Override
    public void inicializar() {
        origen = null;
    }

    @Override
    public void agregar(int clave, int valor) {
         nodo nc =  Clave2NodoClave(clave);
        if(nc == null){
            nc = new nodo();
            nc.clave = clave;
            nc.siguiente = origen;
            origen = nc;

        }
        nc.valor = valor;

    }

    @Override
    public void eliminar(int clave) {
        if(origen != null){
            if (origen.clave == clave){
                origen = origen.siguiente; // si es el primero el q tiene clave paso el puntero al siguiente y se elimina
            }
            else {
                nodo aux =origen;
                while (aux.siguiente != null && aux.siguiente.clave != clave){
                    aux = aux.siguiente;
                }
                if (aux.siguiente !=null){
                    aux.siguiente = aux.siguiente.siguiente;
                }
            }
        }
    }

    @Override
    public int recuperar(int clave) {
        nodo c = Clave2NodoClave(clave);
        return c.valor;
    }


    @Override
    public ConjuntoTDA obtenerClave() {
        ConjuntoTDA c = new ConjuntoDinamico();
        c.inicializar();

        nodo aux = origen;
        while (aux !=null){
            c.agregar(aux.clave);
            aux = aux.siguiente;
        }
        return c;
    }

    private nodo Clave2NodoClave(int clave){
        nodo aux = origen;
        while(aux != null && aux.clave!=clave){
            aux = aux.siguiente;
        }return aux;
    }
}
