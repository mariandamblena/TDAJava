package tda;

public interface GrafoTDA {
    void InicializarGrafo();

    // siempre que el grafo est´e inicializado y no exista el nodo
    void AgregarVertice(int v);

    // siempre que el grafo est´e inicializado y exista el nodo
    void EliminarVertice(int v);

    // siempre que el grafo est´e inicializado
    ConjuntoTDA Vertices();

    // siempre que el grafo est´e inicializado , no exista larista , pero existan ambos nodos
    void AgregarArista(int v1, int v2, int peso);

    // siempre que el grafo est´e inicializado y exista la arista
    void EliminarArista(int v1, int v2);

    // siempre que el grafo est´e inicializado y existan los nodos
    boolean ExisteArista(int v1, int v2);

    // siempre que el grafo est´e inicializado y exista la arista
    int PesoArista(int v1, int v2);
}