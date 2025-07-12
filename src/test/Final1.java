package test;

import tda.ColaTDA;
import implementacion.*;
import tda.DiccionarioMultipleTDA;

public class Final1 {
    public static void main(String[] args) {
        // Crear e inicializar el grafo
        GrafoLA grafo = new GrafoLA();
        grafo.InicializarGrafo();


        // Agregar vértices 1 a 5
        for (int v = 1; v <= 5; v++) {
            grafo.AgregarVertice(v);
        }

        // Agregar aristas que forman un ciclo Hamiltoniano: 1->2->3->4->5->1
        grafo.AgregarArista(1, 2, 1);
        grafo.AgregarArista(2, 3, 1);
        grafo.AgregarArista(3, 4, 1);
        grafo.AgregarArista(4, 5, 1);
        grafo.AgregarArista(5, 1, 1);

        // Crear y poblar la cola con la secuencia 1,2,3,4,5
        ColaTDA cola = new ColaDinamica();
        cola.inicializar();
        for (int v = 1; v <= 5; v++) {
            cola.acolar(v);
        }

        // Verificar si la secuencia es un ciclo Hamiltoniano
        boolean esCycle = grafo.esHamiltoneano(cola);
        System.out.println("¿Es Hamiltoniano (1-2-3-4-5)? " + esCycle);

        // Probar una secuencia no Hamiltoniana: 1-3-2-4-5
        cola.inicializar();
        int[] sec = {1, 3, 2, 4, 5};
        for (int v : sec) {
            cola.acolar(v);
        }
        esCycle = grafo.esHamiltoneano(cola);
        System.out.println("¿Es Hamiltoniano (1-3-2-4-5)? " + esCycle);


        ABB arbol = new ABB();
        arbol.InicializarArbol();
        arbol.AgregarElem(8);
        arbol.AgregarElem(12);
        arbol.AgregarElem(15);
        arbol.AgregarElem(10);
        arbol.AgregarElem(9);
        arbol.AgregarElem(11);
        arbol.AgregarElem(4);
        arbol.AgregarElem(2);
        arbol.AgregarElem(5);
        arbol.AgregarElem(1);
        arbol.AgregarElem(3);

        System.out.println(arbol.contarMenores(arbol,1));

    }


}
