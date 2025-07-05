package implementacion;

import tda.ColaTDA;

public class ColaDinamica implements ColaTDA {

    class nodo{
        int valor;
        nodo siguiente;
    }
    private nodo primero;
    private nodo ultimo;

    @Override
    public void inicializar() {
        primero = null;
        ultimo = null;
    }

    @Override
    public void acolar(int valor) {
        nodo aux = new nodo();
        aux.valor = valor;
        if(estaVacia()){
            primero = aux;
            ultimo = aux;
        }else {
            ultimo.siguiente = aux;
            ultimo = aux;
        }
    }

    @Override
    public void desacolar() {
        primero = primero.siguiente;
    }

    @Override
    public boolean estaVacia() {
        return primero == null;
    }

    @Override
    public int primero() {
        return primero.valor;
    }
}
