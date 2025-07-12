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
            // 1) Caso hoja: si el nodo a borrar es la raíz y no tiene hijos
            if (raiz.info == x && raiz.hijoIzq.ArbolVacio() && raiz.hijoDer.ArbolVacio()) {
                raiz = null;
            }
            // 2) Caso con subárbol izquierdo no vacío:
            //    reemplazamos por el máximo del subárbol izquierdo (antecesor)
            else if (raiz.info == x && !raiz.hijoIzq.ArbolVacio()) {
                raiz.info = this.mayor(raiz.hijoIzq);
                raiz.hijoIzq.EliminarElem(raiz.info);
            }
            // 3) Caso con subárbol izquierdo vacío (pero quizá derecho no):
            //    reemplazamos por el mínimo del subárbol derecho (sucesor)
            else if (raiz.info == x && raiz.hijoIzq.ArbolVacio()) {
                raiz.info = this.menor(raiz.hijoDer);
                raiz.hijoDer.EliminarElem(raiz.info);
            }
            else if (x < raiz.info) {
                raiz.hijoIzq.EliminarElem(x);
            }
            else {
                raiz.hijoDer.EliminarElem(x);
            }
        }
    }

    public int contarMenores(ABBTDA a, int v){
        if(a.ArbolVacio()) return 0;

        // Si raiz actual es mayor que
        if (a.Raiz() >= v){
            return contarMenores(a.HijoIzq(),v);
        }
        // si llegaste hasta aca es porque raiz< valor
        return 1+ contarMenores(a.HijoIzq(),v) + contarMenores(a.HijoDer(),v);

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
