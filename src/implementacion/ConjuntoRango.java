package implementacion;

import tda.ConjuntoRangoTDA;
import tda.ConjuntoTDA;

import java.util.Random;

public class ConjuntoRango implements ConjuntoRangoTDA {


    class nodo {
        int valor;
        nodo siguiente;
    }

    private nodo primero;
    private int cantidad;
    private Random r;


    @Override
    public void inicializarConjunto(int limiteInferior, int limiteSuperior) {


    }

    @Override
    public void agregar(int x) {

    }

    @Override
    public ConjuntoTDA obtenerEnRango() {
        return null;
    }

    @Override
    public ConjuntoTDA obtenerFueraDeRango() {
        return null;
    }
}
