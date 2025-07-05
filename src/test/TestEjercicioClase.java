package test;
import implementacion.*;
import tda.ABBTDA;
import tda.ConjuntoTDA;
import tda.DiccionarioMultipleTDA;



public class TestDiccionarioArbol {
    public static void main(String[] args) {
        ABBTDA arbol = new ABB();
        arbol.InicializarArbol();
        arbol.AgregarElem(20);
        arbol.AgregarElem(10);
        arbol.AgregarElem(30);
        arbol.AgregarElem(5);
        arbol.AgregarElem(15);

        DiccionarioMultipleTDA dic = new DiccionarioMultipleEstatico();
        dic.inicializar();

        UtilArbol.NodosNoHojaConDescendientes(arbol, dic);

        // Mostrar el diccionario
        ConjuntoTDA claves = dic.clave();
        while (!claves.conjuntoVacio()) {
            int clave = claves.elegir();
            System.out.println("Nodo no hoja: " + clave);
            ConjuntoTDA valores = dic.recuperar(clave);
            System.out.print("Descendientes: ");
            while (!valores.conjuntoVacio()) {
                System.out.print(valores.elegir() + " ");
                valores.sacar(valores.elegir());
            }
            System.out.println();
            claves.sacar(clave);
        }
    }
}
