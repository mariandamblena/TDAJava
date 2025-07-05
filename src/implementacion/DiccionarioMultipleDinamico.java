package implementacion;

import tda.ConjuntoTDA;
import tda.DiccionarioMultipleTDA;

public class DiccionarioMultipleDinamico implements DiccionarioMultipleTDA {

    /* ------------ Nodos ------------ */
    class NodoClave {
        int clave;
        NodoValor valores;
        NodoClave sigClave;
    }

    class NodoValor {
        int valor;
        NodoValor sigValor;
    }

    /* ------------ Atributo raíz ------------ */
    private NodoClave origen;

    /* ------------ Interface DiccionarioMultipleTDA ------------ */
    @Override
    public void inicializar() {
        origen = null;
    }

    @Override
    public void agregar(int clave, int valor) {
        NodoClave nc = clave2NodoClave(clave);

        // Si la clave no existe, crearla y encadenarla al inicio
        if (nc == null) {
            nc = new NodoClave();
            nc.clave    = clave;
            nc.valores  = null;
            nc.sigClave = origen;
            origen      = nc;
        }

        // Verificar si el valor ya está presente
        NodoValor aux = nc.valores;
        while (aux != null && aux.valor != valor) {
            aux = aux.sigValor;
        }

        // Si el valor es nuevo, insertarlo al comienzo de la lista de valores
        if (aux == null) {
            NodoValor nv = new NodoValor();
            nv.valor    = valor;
            nv.sigValor = nc.valores;
            nc.valores  = nv;
        }
    }

    @Override
    public void eliminar(int clave) {
        if (origen == null) return;

        if (origen.clave == clave) {            // clave en el primer nodo
            origen = origen.sigClave;
        } else {                                // buscarla más adelante
            NodoClave aux = origen;
            while (aux.sigClave != null && aux.sigClave.clave != clave) {
                aux = aux.sigClave;
            }
            if (aux.sigClave != null)           // encontrada
                aux.sigClave = aux.sigClave.sigClave;
        }
    }

    @Override
    public void eliminarValor(int clave, int valor) {
        if (origen == null) return;

        if (origen.clave == clave) {
            eliminarValorEnNodo(origen, valor);
            if (origen.valores == null)
                origen = origen.sigClave;       // si la lista queda vacía, quitar la clave
        } else {
            NodoClave aux = origen;
            while (aux.sigClave != null && aux.sigClave.clave != clave) {
                aux = aux.sigClave;
            }
            if (aux.sigClave != null) {
                eliminarValorEnNodo(aux.sigClave, valor);
                if (aux.sigClave.valores == null)
                    aux.sigClave = aux.sigClave.sigClave;
            }
        }
    }

    @Override
    public ConjuntoTDA recuperar(int clave) {
        NodoClave n = clave2NodoClave(clave);
        ConjuntoTDA c = new ConjuntoDinamico();
        c.inicializar();

        if (n != null) {
            NodoValor aux = n.valores;
            while (aux != null) {
                c.agregar(aux.valor);
                aux = aux.sigValor;
            }
        }
        return c;
    }

    @Override
    public ConjuntoTDA clave() {
        ConjuntoTDA c = new ConjuntoDinamico();
        c.inicializar();
        NodoClave aux = origen;

        while (aux != null) {
            c.agregar(aux.clave);
            aux = aux.sigClave;
        }
        return c;
    }

    @Override
    public boolean estaVacio() {
        return origen == null;
    }

    /* ------------ Privados auxiliares ------------ */
    private NodoClave clave2NodoClave(int clave) {
        NodoClave aux = origen;
        while (aux != null && aux.clave != clave) {
            aux = aux.sigClave;
        }
        return aux;
    }

    private void eliminarValorEnNodo(NodoClave nodo, int valor) {
        if (nodo.valores == null) return;

        if (nodo.valores.valor == valor) {          // valor en el primer nodo
            nodo.valores = nodo.valores.sigValor;
        } else {
            NodoValor aux = nodo.valores;
            while (aux.sigValor != null && aux.sigValor.valor != valor) {
                aux = aux.sigValor;
            }
            if (aux.sigValor != null)               // encontrado
                aux.sigValor = aux.sigValor.sigValor;
        }
    }
}
