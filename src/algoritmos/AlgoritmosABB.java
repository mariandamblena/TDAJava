package algoritmos;

import implementacion.ConjuntoDinamico;
import tda.ABBTDA;
import tda.ConjuntoTDA;

public class AlgoritmosABB {
    // Metodo que me de cuanto nodos tiene el arbol
    //hay que recorrer el arbol, es recursividad, no puedo usar raiz == null porque es privado, dentor de la implementacion.
    //La condicion de corte esque el arbol esta vacio, entonces no tengo nada para contar
    //un arbol es la raiz y dos arboles
    public int contar(ABBTDA arbol){
        if(arbol.ArbolVacio())
            return 0;
        else
            return 1 + contar(arbol.HijoIzq()) + contar(arbol.HijoDer());
    }

    // algoritmo que le des un arbol y te devuelva un conjunto

    public ConjuntoTDA devolverHojas(ABBTDA arbol) {
        ConjuntoTDA r = new ConjuntoDinamico();
        r.inicializar();
        devolverHojasRec(arbol, r);
        return r;
    }

    // Función recursiva auxiliar interna
    private void devolverHojasRec(ABBTDA arbol, ConjuntoTDA r) {
        if (!arbol.ArbolVacio()) {
            boolean esHoja = arbol.HijoIzq().ArbolVacio() && arbol.HijoDer().ArbolVacio();

            if (esHoja) {
                r.agregar(arbol.Raiz());
            } else {
                devolverHojasRec(arbol.HijoIzq(), r);
                devolverHojasRec(arbol.HijoDer(), r);
            }
        }
    }


    public ConjuntoTDA nodosPares(ABBTDA a){
        ConjuntoTDA r= new ConjuntoLD();
        r.InicializarConjunto();
        if (!a.ArbolVacio()){
            if (a.raiz() % 2 == 0){
                r.Agregar(a.raiz());
            }
            ConjuntoTDA rI = nodosPares(a.HijoIzq());
            ConjuntoTDA rD = nodosPares(a.HijoDer());
            while (!rI.ConjuntoVacio()){
                int x = rI.Elegir();
                r.Agregar(x);
                rI.Sacar(x);
            }
            while (!rD.ConjuntoVacio()){
                int x = rD.Elegir();
                r.Agregar(x);
                rD.Sacar(x);
            }
        }
        return r;
    }

}
