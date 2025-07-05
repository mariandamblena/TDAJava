package implementacion;

import tda.ABBTDA;

public class AVL implements ABBTDA {

    class Nodo {
        int valor;
        ABBTDA izquierdo;
        ABBTDA derecho;
        int altura;
    }

    Nodo raiz;

    @Override
    public void InicializarArbol() {
        raiz = null;
    }

    @Override
    public boolean ArbolVacio() {
        return (raiz == null);
    }

    @Override
    public int Raiz() {
        return raiz.valor;
    }

    @Override
    public ABBTDA HijoIzq() {
        return raiz.izquierdo;
    }

    @Override
    public ABBTDA HijoDer() {
        return raiz.derecho;
    }

    @Override
    public void AgregarElem(int x) {
        raiz = insertar(raiz, x);
    }

    private Nodo insertar(Nodo nodo, int x) {
        if (nodo == null) {
            Nodo nuevo = new Nodo();
            nuevo.valor = x;
            nuevo.altura = 1;
            nuevo.izquierdo = new AVL(); nuevo.izquierdo.InicializarArbol();
            nuevo.derecho = new AVL(); nuevo.derecho.InicializarArbol();
            return nuevo;
        }

        if (x < nodo.valor) {
            nodo.izquierdo.AgregarElem(x);
        } else if (x > nodo.valor) {
            nodo.derecho.AgregarElem(x);
        } else {
            return nodo; // No permitimos duplicados
        }

        actualizarAltura(nodo);
        return balancear(nodo);
    }

    @Override
    public void EliminarElem(int x) {
        raiz = eliminar(raiz, x);
    }

    private Nodo eliminar(Nodo nodo, int x) {
        if (nodo == null) return null;

        if (x < nodo.valor) {
            nodo.izquierdo.EliminarElem(x);
        } else if (x > nodo.valor) {
            nodo.derecho.EliminarElem(x);
        } else {
            if (nodo.izquierdo.ArbolVacio() && nodo.derecho.ArbolVacio()) {
                return null;
            } else if (nodo.izquierdo.ArbolVacio()) {
                return ((AVL) nodo.derecho).raiz;
            } else if (nodo.derecho.ArbolVacio()) {
                return ((AVL) nodo.izquierdo).raiz;
            } else {
                int sucesor = ((AVL) nodo.derecho).menor();
                nodo.valor = sucesor;
                nodo.derecho.EliminarElem(sucesor);
            }
        }

        actualizarAltura(nodo);
        return balancear(nodo);
    }

    private int menor() {
        AVL actual = this;
        while (!actual.raiz.izquierdo.ArbolVacio()) {
            actual = (AVL) actual.raiz.izquierdo;
        }
        return actual.raiz.valor;
    }

    private void actualizarAltura(Nodo nodo) {
        int altIzq = (nodo.izquierdo instanceof AVL && !nodo.izquierdo.ArbolVacio()) ? ((AVL) nodo.izquierdo).raiz.altura : 0;
        int altDer = (nodo.derecho instanceof AVL && !nodo.derecho.ArbolVacio()) ? ((AVL) nodo.derecho).raiz.altura : 0;
        nodo.altura = 1 + Math.max(altIzq, altDer);
    }

    private int balanceFactor(Nodo nodo) {
        int altIzq = (nodo.izquierdo instanceof AVL && !nodo.izquierdo.ArbolVacio()) ? ((AVL) nodo.izquierdo).raiz.altura : 0;
        int altDer = (nodo.derecho instanceof AVL && !nodo.derecho.ArbolVacio()) ? ((AVL) nodo.derecho).raiz.altura : 0;
        return altIzq - altDer;
    }

    private Nodo balancear(Nodo nodo) {
        int balance = balanceFactor(nodo);

        if (balance > 1) {
            if (balanceFactor(((AVL) nodo.izquierdo).raiz) < 0) {
                ((AVL) nodo.izquierdo).raiz = rotarIzq(((AVL) nodo.izquierdo).raiz);
            }
            return rotarDer(nodo);
        }

        if (balance < -1) {
            if (balanceFactor(((AVL) nodo.derecho).raiz) > 0) {
                ((AVL) nodo.derecho).raiz = rotarDer(((AVL) nodo.derecho).raiz);
            }
            return rotarIzq(nodo);
        }

        return nodo;
    }

    private Nodo rotarDer(Nodo y) {
        Nodo x = ((AVL) y.izquierdo).raiz;
        Nodo T2 = (x.derecho instanceof AVL) ? ((AVL) x.derecho).raiz : null;

        x.derecho = new AVL(); ((AVL) x.derecho).raiz = y;
        y.izquierdo = new AVL(); ((AVL) y.izquierdo).raiz = T2;

        actualizarAltura(y); actualizarAltura(x);
        return x;
    }

    private Nodo rotarIzq(Nodo x) {
        Nodo y = ((AVL) x.derecho).raiz;
        Nodo T2 = (y.izquierdo instanceof AVL) ? ((AVL) y.izquierdo).raiz : null;

        y.izquierdo = new AVL(); ((AVL) y.izquierdo).raiz = x;
        x.derecho = new AVL(); ((AVL) x.derecho).raiz = T2;

        actualizarAltura(x); actualizarAltura(y);
        return y;
    }

    public AVL() { InicializarArbol(); }
}


//Complejidad Temporal de los Métodos
//Método	Complejidad Temporal
//InicializarArbol	O(1)
//ArbolVacio	O(1)
//Raiz	O(1)
//HijoIzq, HijoDer	O(1)
//AgregarElem(x)	O(log n)
//EliminarElem(x)	O(log n)
//menor()	O(log n)
//actualizarAltura	O(1)
//balancear	O(1)
//rotarIzq/Der	O(1)
//
//Inserción y eliminación son O(log n) porque el AVL garantiza que la altura es logarítmica.
//Las rotaciones y actualizaciones son O(1) porque solo involucran un número constante de nodos.