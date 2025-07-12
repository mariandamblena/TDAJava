package algoritmos;

import implementacion.*;
import tda.ABBTDA;

public class Test {
    public static void main(String[] args) {
        AVL avl = new AVL();


        // 1) Insertamos en orden ascendente (sin balanceo, sería degenerado):
        int[] valores = {10, 20, 30, 40, 50, 25};
        for (int v : valores) {
            avl.AgregarElem(v);
        }

        // 2) Hacemos un in‐order para comprobar que sale ordenado
        System.out.print("Recorrido in‐order: ");
        inorder(avl);
        System.out.println();

        // 3) Comprobamos el valor de la raíz (debe ser 30 en este ejemplo)
        System.out.println("Raíz actual (debe ser 30): " + avl.Raiz());

        // (Opcional) comprobar alturas aproximadas:
        // en este árbol equilibrado mínimo, la altura debería ser 3
        System.out.println("Altura esperada aproximada (3): " + altura(avl));

    }


    // Recorrido in‐order usando la interfaz ABBTDA
    private static void inorder(ABBTDA a) {
        if (a.ArbolVacio()) return;
        inorder(a.HijoIzq());
        System.out.print(a.Raiz() + " ");
        inorder(a.HijoDer());
    }

    // Función auxiliar para medir la altura "real" del ABBTDA
    private static int altura(ABBTDA a) {
        if (a == null) return -1;
        if (a.ArbolVacio()) return 0;
        int hIz = altura(a.HijoIzq());
        int hDer = altura(a.HijoDer());
        return 1 + Math.max(hIz, hDer);
    }
}
