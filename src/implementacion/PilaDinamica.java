package implementacion;

import tda.PilaTDA;

public class PilaDinamica implements PilaTDA {

    class nodo {
        int valor;
        nodo siguiente;
    }

    private nodo primero;

    @Override
    public void inicializar() {
        primero = null;
    }

    @Override
    public void apilar(int valor) {
        nodo aux = new nodo();
        aux.valor = valor;
        aux.siguiente = primero;
        primero = aux;
    }

    @Override
    public void desapilar() {
        primero = primero.siguiente;
    }

    @Override
    public boolean estaVacia() {
        return primero == null;
    }

    @Override
    public int tope() {
        return primero.valor;
    }
}
