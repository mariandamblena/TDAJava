package algoritmos;
import tda.ABBTDA;
import tda.DiccionarioMultipleTDA;


public class AlgoritmosABB_Y_DIC {
    public static DiccionarioMultipleTDA NodosNoHojaConDescendientes(ABBTDA arbol, DiccionarioMultipleTDA dic) {
        if (!arbol.ArbolVacio()) {

            boolean esNoHoja = !arbol.HijoIzq().ArbolVacio() || !arbol.HijoDer().ArbolVacio();

            if (esNoHoja) {
                int clave = arbol.Raiz();
                // Agregamos todos los descendientes de este nodo como valores
                agregarDescendientes(arbol.HijoIzq(), clave, dic);
                agregarDescendientes(arbol.HijoDer(), clave, dic);
            }

            // Recorrer recursivamente el resto del árbol
            NodosNoHojaConDescendientes(arbol.HijoIzq(), dic);
            NodosNoHojaConDescendientes(arbol.HijoDer(), dic);
        }
        return dic;
    }

    private static void agregarDescendientes(ABBTDA arbol, int clave, DiccionarioMultipleTDA dic) {
        if (!arbol.ArbolVacio()) {
            dic.agregar(clave, arbol.Raiz());
            agregarDescendientes(arbol.HijoIzq(), clave, dic);
            agregarDescendientes(arbol.HijoDer(), clave, dic);
        }
    }
}
