package implementacion;

import tda.ABBTDA;

public class ABB implements ABBTDA {

    class NodoABB {
        int info;
        ABB hijoIzq;
        ABB hijoDer;
    }

    NodoABB raiz;

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
        return raiz.info;
    }

    @Override
    public ABBTDA HijoIzq() {
        return raiz.hijoIzq;
    }

    @Override
    public ABBTDA HijoDer() {
        return raiz.hijoDer;
    }

    @Override
    public void AgregarElem(int x) {
        if (raiz == null) {
            raiz = new NodoABB();
            raiz.info = x;
            raiz.hijoIzq = new ABB();
            raiz.hijoIzq.InicializarArbol();
            raiz.hijoDer = new ABB();
            raiz.hijoDer.InicializarArbol();

        } else if (x < raiz.info) {
            raiz.hijoIzq.AgregarElem(x);
        } else if (x > raiz.info) {
            raiz.hijoDer.AgregarElem(x);
        }
    }

    @Override
    public void EliminarElem(int x) {
        if (raiz != null) {
            if (raiz.info == x && raiz.hijoIzq.ArbolVacio() && raiz.hijoDer.ArbolVacio()) {
                raiz = null;
            } else if (raiz.info == x && !raiz.hijoIzq.ArbolVacio()) {
                raiz.info = this.mayor(raiz.hijoIzq);
                raiz.hijoIzq.EliminarElem(raiz.info);
            } else if (raiz.info == x && raiz.hijoIzq.ArbolVacio()) {
                raiz.info = this.menor(raiz.hijoDer);
                raiz.hijoDer.EliminarElem(raiz.info);
            } else if (x < raiz.info) {
                raiz.hijoIzq.EliminarElem(x);
            } else {
                raiz.hijoDer.EliminarElem(x);
            }
        }
    }

    private int mayor(ABBTDA a) {
        if (a.HijoDer().ArbolVacio()) {
            return a.Raiz();
        } else {
            return mayor(a.HijoDer());
        }
    }

    private int menor(ABBTDA a) {
        if (a.HijoIzq().ArbolVacio()) {
            return a.Raiz();
        } else {
            return menor(a.HijoIzq());
        }
    }
}
