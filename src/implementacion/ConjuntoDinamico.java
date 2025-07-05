package implementacion;

import tda.ConjuntoTDA;

import java.util.Random;

public class ConjuntoDinamico implements ConjuntoTDA {

    class nodo {
        int valor;
        nodo siguiente;
    }

    private nodo primero;
    private int cantidad;
    private Random r;


    @Override
    public void inicializar() {
        primero =null;
        cantidad = 0;
        r = new Random();
    }

    @Override
    public void sacar(int valor) {
        nodo actual = primero;
        nodo anterior = null;
        while (actual != null) {
            if (actual.valor == valor) {
                if (anterior == null) {
                    primero = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                cantidad--;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    @Override
    public int elegir() {
        r = new Random();
        int pos = r.nextInt(cantidad);
        nodo actual = primero;
        while(pos > 0){
            actual = actual.siguiente;
            pos --;
        }
        return actual.valor;
    }

    @Override
    public boolean estaVacia() {
        return cantidad == 0;
    }

    @Override
    public void agregar(int valor) {
        nodo nuevo = new nodo();
        if(!pertenece(valor)){
            nuevo.siguiente = primero;
            primero = nuevo;
            nuevo.valor = valor;
            cantidad++;
        }
    }

    @Override
    public boolean pertenece(int valor) {
        nodo actual = primero;
        while(actual != null)
            if(actual.valor == valor){
                return true;
            }actual = actual.siguiente;
        return false;
    }
}
