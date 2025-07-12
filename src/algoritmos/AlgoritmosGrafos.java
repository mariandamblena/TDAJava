package algoritmos;

import implementacion.ABB;
import implementacion.ConjuntoDinamico;
import tda.ABBTDA;
import tda.ConjuntoTDA;
import tda.GrafoTDA;

public class AlgoritmosGrafos {

    public static ConjuntoTDA calcularSegundoVecindario(int x, GrafoTDA grafo) {

        ConjuntoTDA vertices1           = new ConjuntoDinamico();
        ConjuntoTDA vertices2           = new ConjuntoDinamico();
        ConjuntoTDA primerVecindario    = new ConjuntoDinamico();
        ConjuntoTDA segundoVecindario   = new ConjuntoDinamico();

        vertices1.inicializar();
        vertices2.inicializar();
        primerVecindario.inicializar();
        segundoVecindario.inicializar();

        // 1) armar el primer vecindario: todos los v tales que existe arco x→v
        vertices1 = grafo.Vertices();
        vertices1.sacar(x);
        while (!vertices1.estaVacia()) {
            int vertice1 = vertices1.elegir();
            vertices1.sacar(vertice1);
            if (grafo.ExisteArista(x, vertice1)) {
                primerVecindario.agregar(vertice1);
            }
        }

        // 2) para cada u en primerVecindario, recorrer TODOS los vértices y agregar
        //    aquellos a los que u apunta (arco u→v)
        while (!primerVecindario.estaVacia()) {
            int vertice2 = primerVecindario.elegir();
            primerVecindario.sacar(vertice2);

            // "resetear" el conjunto de candidatos en cada iteración
            vertices2 = grafo.Vertices();
            vertices2.sacar(x);

            while (!vertices2.estaVacia()) {
                int vertice3 = vertices2.elegir();
                vertices2.sacar(vertice3);
                if (grafo.ExisteArista(vertice2, vertice3)) {
                    segundoVecindario.agregar(vertice3);
                }
            }
        }

        return segundoVecindario;
    }

    public static ConjuntoTDA numerosPares(ABBTDA a){

        ConjuntoTDA c = new ConjuntoDinamico();
        c.inicializar();
        agregarPares(a,c);
        return c;
    }

    private static void agregarPares(ABBTDA a, ConjuntoTDA c){
        if(a.ArbolVacio())return;

        if(a.Raiz() % 2 == 0){
            c.agregar(a.Raiz());
        }
        agregarPares(a.HijoIzq(),c);
        agregarPares(a.HijoDer(),c);

        return;
    }
}
