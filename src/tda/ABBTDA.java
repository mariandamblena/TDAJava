package tda;

public interface ABBTDA {
    // siempre que el ´ arbol est´ e inicializado y no est´ e vac´ ıo
    int Raiz();
    // siempre que el ´ arbol est´ e inicializado y no est´ e vac´ ıo
    ABBTDA HijoIzq();
    // siempre que el ´ arbol est´ e inicializado y no est´ e vac´ ıo
    ABBTDA HijoDer();
    // siempre que el ´ arbol est´ e inicializado
    boolean ArbolVacio();
    void InicializarArbol();
    // siempre que el ´ arbol est´ e inicializado
    void AgregarElem(int x);
    // siempre que el ´ arbol est´ e inicializado
    void EliminarElem(int x);
}
